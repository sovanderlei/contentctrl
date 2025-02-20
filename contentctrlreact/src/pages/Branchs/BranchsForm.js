import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';
import { Formik } from 'formik';
import * as Yup from 'yup';
import { branchService } from '../../services/branchService';  // Service para filiais
import { companyService } from '../../services/companyService'; // Service para empresas
import './Branch.css';

const BranchForm = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const isEdit = !!id;

    const [initialValues, setInitialValues] = useState({
        name: '',
        address: '',
        companyId: ''
    });
    const [companies, setCompanies] = useState([]);

    const validationSchema = Yup.object().shape({
        name: Yup.string().required('Campo obrigatório'),
        address: Yup.string().required('Campo obrigatório'),
        companyId: Yup.string().required('Campo obrigatório')
    });

    // Buscar as empresas disponíveis
    useEffect(() => {
        companyService.getAll().then((data) => {
            setCompanies(data);
        });

        if (isEdit) {
            branchService.getById(id).then((branch) => {
                setInitialValues({
                    name: branch.name,
                    address: branch.address,
                    companyId: branch.companyId
                });
            });
        }
    }, [id, isEdit]);

    const handleSubmit = async (values) => {
        try {
            if (isEdit) {
                await branchService.update(id, values);
            } else {
                await branchService.create(values);
            }
            navigate('/branches');
        } catch (error) {
            console.error('Erro ao salvar filial:', error);
        }
    };

    return (
        <div className="card branch-form-card">
            <div className="card-header bg-primary text-white">
                <h3 className="mb-0">{isEdit ? 'Editar' : 'Nova'} Filial</h3>
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
                                <Form.Label>Nome da Filial</Form.Label>
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


                            <div className="d-flex justify-content-end gap-2">
                                <Button
                                    variant="secondary"
                                    onClick={() => navigate('/branches')}
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

export default BranchForm;
