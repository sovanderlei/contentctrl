import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { userService } from '../../services/userService';
import { useNavigate } from 'react-router-dom';
import { Container, Row, Col, Button, Card, Image } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import backgroundImage from '../../images/fundo02.png'; // Imagem de fundo
import logo from '../../images/logo.png'; // Logo

const Login = () => {
    const navigate = useNavigate();

    const validationSchema = Yup.object().shape({
        username: Yup.string().required('Campo obrigatório'),
        password: Yup.string().min(6, 'Mínimo 6 caracteres').required('Campo obrigatório')
    });

    const handleSubmit = async (values, { setSubmitting, setErrors }) => {
        try {
            console.log(" values ", values);
            const response = await userService.login(values);
            localStorage.setItem('token', response.token);
            navigate('/home');
        } catch (error) {
            setErrors({ general: error.message });
        }
        setSubmitting(false);
    };

    return (
        <div
            className="vh-100 d-flex align-items-center justify-content-center"
            style={{
                backgroundImage: `url(${backgroundImage})`,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
                backgroundRepeat: 'no-repeat'
            }}
        >
            <Card className="shadow" style={{ maxWidth: '400px', width: '100%', padding: '20px', backgroundColor: 'rgba(255, 255, 255, 0.9)', borderRadius: '10px' }}>
                <div className="text-center mb-3">
                    <Image src={logo} alt="Logo" fluid style={{ maxWidth: '150px' }} />
                </div>
                <h3 className="mb-3 text-center">Login</h3>
                <Formik
                    initialValues={{ username: 'vanderlei', password: 'vanderlei123' }}
                    validationSchema={validationSchema}
                    onSubmit={handleSubmit}
                >
                    {({ isSubmitting, errors }) => (
                        <Form>
                            <div className="mb-3">
                                <label>Usuário</label>
                                <Field name="username" className="form-control" />
                                <ErrorMessage name="username" component="div" className="text-danger" />
                            </div>
                            <div className="mb-3">
                                <label>Senha</label>
                                <Field name="password" type="password" className="form-control" />
                                <ErrorMessage name="password" component="div" className="text-danger" />
                            </div>
                            {errors.general && <div className="text-danger mb-3">{errors.general}</div>}
                            <Button type="submit" variant="primary" disabled={isSubmitting} className="w-100">
                                Entrar
                            </Button>
                        </Form>
                    )}
                </Formik>
            </Card>
        </div>
    );
};

export default Login;
