import React from 'react';

const Header = ({ onToggleSidebar, user }) => {
    return (
        <header className="navbar navbar-expand navbar-dark bg-dark shadow-sm">
            <div className="container-fluid">
                <div className="d-flex align-items-center">
                    <button
                        className="btn btn-dark me-2"
                        onClick={onToggleSidebar}
                    >
                        <i className="bi bi-list"></i>
                    </button>
                    <img
                        src="logo.png"
                        alt="Logo"
                        style={{ height: '40px' }}
                        className="me-2"
                    />
                    <span className="navbar-brand mb-0 h1">Empresa XYZ</span>
                </div>

                <div className="d-flex align-items-center">
                    <div className="text-white me-3">{user.name}</div>
                    <img
                        src={user.photo}
                        alt="Usuário"
                        className="rounded-circle me-2"
                        style={{ width: '40px', height: '40px', objectFit: 'cover' }}
                    />
                    <button
                        className="btn btn-outline-light"
                        onClick={user.handleLogout}
                    >
                        Sair
                    </button>
                </div>
            </div>
        </header>
    );
};

export default Header;