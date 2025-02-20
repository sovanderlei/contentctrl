import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';
import { Formik } from 'formik';
import * as Yup from 'yup';
import { companyService } from '../../services/companyService'; // Service para empresas
import './Company.css';

const CompanyForm = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const isEdit = !!id;

    const [initialValues, setInitialValues] = useState({
        name: '',
        address: '',
        phoneNumber: ''
    });

    const validationSchema = Yup.object().shape({
        name: Yup.string().required('Campo obrigatório'),
        address: Yup.string().required('Campo obrigatório'),
        phoneNumber: Yup.string().required('Campo obrigatório')
    });

    // 🔹 Buscar os dados da empresa ao editar
    useEffect(() => {
        if (isEdit) {
            companyService.getById(id).then((company) => {
                setInitialValues({
                    name: company.name,
                    address: company.address,
                    phoneNumber: company.phoneNumber
                });
            }).catch((error) => {
                console.error('Erro ao buscar empresa:', error);
            });
        }
    }, [id, isEdit]);

    const handleSubmit = async (values) => {
        try {
            if (isEdit) {
                await companyService.update(id, values);
            } else {
                await companyService.create(values);
            }
            navigate('/companies');
        } catch (error) {
            console.error('Erro ao salvar empresa:', error);
        }
    };

    return (
        <div className="card company-form-card">
            <div className="card-header bg-primary text-white">
                <div className="d-flex justify-content-between align-items-center">
                    <h3 className="mb-0">{isEdit ? 'Editar' : 'Nova'} Empresa</h3>
                    <Button variant="light" onClick={() => navigate('/companies')}>
                        Voltar
                    </Button>
                </div>
            </div>

            <div className="card-body">
                <Formik
                    enableReinitialize
                    initialValues={initialValues}
                    validationSchema={validationSchema}
                    onSubmit={handleSubmit}
                >
                    {({ handleSubmit, handleChange, values, errors }) => (
                        <Form onSubmit={handleSubmit}>
                            <Form.Group className="mb-3">
                                <Form.Label>Nome da Empresa</Form.Label>
                                <Form.Control
                                    name="name"
                                    value={values.name}
                                    onChange={handleChange}
                                    isInvalid={!!errors.name}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.name}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Endereço</Form.Label>
                                <Form.Control
                                    name="address"
                                    value={values.address}
                                    onChange={handleChange}
                                    isInvalid={!!errors.address}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.address}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Telefone</Form.Label>
                                <Form.Control
                                    name="phoneNumber"
                                    value={values.phoneNumber}
                                    onChange={handleChange}
                                    isInvalid={!!errors.phoneNumber}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.phoneNumber}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <div className="d-flex justify-content-end gap-2">
                                <Button
                                    variant="secondary"
                                    onClick={() => navigate('/companies')}
                                >
                                    Cancelar
                                </Button>
                                <Button variant="primary" type="submit">
                                    Salvar
                                </Button>
                            </div>
                        </Form>
                    )}
                </Formik>
            </div>
        </div>
    );
};

export default CompanyForm;
