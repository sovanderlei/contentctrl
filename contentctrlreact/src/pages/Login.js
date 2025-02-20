import React from 'react';

const Login = () => {
    return (
        <div className="container">
            <h1 className="mt-3">Tela de Login</h1>
            <form>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">Usuário</label>
                    <input type="text" className="form-control" id="username" />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Senha</label>
                    <input type="password" className="form-control" id="password" />
                </div>
                <button type="submit" className="btn btn-primary">Entrar</button>
            </form>
        </div>
    );
};

export default Login;
