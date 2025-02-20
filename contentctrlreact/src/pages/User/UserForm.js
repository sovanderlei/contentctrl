import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';
import { Formik } from 'formik';
import * as Yup from 'yup';
import { userService } from '../../services/userService';
import './User.css';

const UserForm = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const isEdit = !!id;

    const [initialValues, setInitialValues] = useState({
        username: '',
        email: '',
        password: '',
        idbranch: ''
    });

    const validationSchema = Yup.object().shape({
        username: Yup.string().required('Campo obrigatório'),
        email: Yup.string().email('Email inválido').required('Campo obrigatório'),
        password: Yup.string().min(6, 'Mínimo 6 caracteres').required('Campo obrigatório'),
        idbranch: Yup.number().required('Campo obrigatório')
    });

    // 🔹 Buscar os dados do usuário ao editar
    useEffect(() => {
        if (isEdit) {
            userService.getById(id).then((user) => {
                setInitialValues({
                    username: user.username,
                    email: user.email,
                    password: '',  // Opcional: Deixe a senha vazia para segurança
                    idbranch: user.idbranch
                });
            }).catch((error) => {
                console.error('Erro ao buscar usuário:', error);
            });
        }
    }, [id, isEdit]);

    const handleSubmit = async (values) => {
        try {
            if (isEdit) {
                await userService.update(id, values);
            } else {
                await userService.register(values);
            }
            navigate('/users');
        } catch (error) {
            console.error('Erro ao salvar usuário:', error);
        }
    };

    return (
        <div className="card user-form-card">
            <div className="card-header bg-primary text-white">
                <div className="d-flex justify-content-between align-items-center">
                    <h3 className="mb-0">{isEdit ? 'Editar' : 'Novo'} Usuário</h3>
                    <Button variant="light" onClick={() => navigate('/users')}>
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
                                <Form.Label>Nome de Usuário</Form.Label>
                                <Form.Control
                                    name="username"
                                    value={values.username}
                                    onChange={handleChange}
                                    isInvalid={!!errors.username}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.username}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Email</Form.Label>
                                <Form.Control
                                    type="email"
                                    name="email"
                                    value={values.email}
                                    onChange={handleChange}
                                    isInvalid={!!errors.email}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.email}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Senha</Form.Label>
                                <Form.Control
                                    type="password"
                                    name="password"
                                    value={values.password}
                                    onChange={handleChange}
                                    isInvalid={!!errors.password}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.password}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <Form.Group className="mb-4">
                                <Form.Label>Filial</Form.Label>
                                <Form.Control
                                    type="number"
                                    name="idbranch"
                                    value={values.idbranch}
                                    onChange={handleChange}
                                    isInvalid={!!errors.idbranch}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.idbranch}
                                </Form.Control.Feedback>
                            </Form.Group>

                            <div className="d-flex justify-content-end gap-2">
                                <Button
                                    variant="secondary"
                                    onClick={() => navigate('/users')}
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

export default UserForm;
