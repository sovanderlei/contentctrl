import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Table, Pagination } from 'react-bootstrap';
import { userService } from '../../services/userService';
import { FiEdit, FiTrash2 } from 'react-icons/fi';
import './User.css';

const UserGrid = () => {
    const [users, setUsers] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const navigate = useNavigate();
    const itemsPerPage = 10;

    useEffect(() => {
        const loadUsers = async () => {
            try {
                const data = await userService.getAll();
                setUsers(data);
            } catch (error) {
                console.error('Erro ao carregar usuários:', error);
            }
        };
        loadUsers();
    }, []);

    // Paginação
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = users.slice(indexOfFirstItem, indexOfLastItem);
    const totalPages = Math.ceil(users.length / itemsPerPage);

    const handleEdit = (userId) => {
        navigate(`/users/edit/${userId}`);
    };

    const handleDelete = async (userId) => {
        if (window.confirm('Deseja realmente excluir este usuário?')) {
            await userService.delete(userId);
            setUsers(users.filter(user => user.id !== userId));
        }
    };

    return (
        <div className="card user-grid-card">
            <div className="card-header bg-primary text-white">
                <h3 className="mb-0">Listagem de Usuários</h3>
            </div>

            <div className="card-body">
                <Table striped hover responsive>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Filial</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {currentItems.map(user => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.username}</td>
                                <td>{user.email}</td>
                                <td>{user.idbranch}</td>
                                <td>
                                    <button
                                        className="btn btn-sm btn-link"
                                        onClick={() => handleEdit(user.id)}
                                    >
                                        <FiEdit size={18} />
                                    </button>
                                    <button
                                        className="btn btn-sm btn-link text-danger"
                                        onClick={() => handleDelete(user.id)}
                                    >
                                        <FiTrash2 size={18} />
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>

                <Pagination className="justify-content-center">
                    {[...Array(totalPages).keys()].map(number => (
                        <Pagination.Item
                            key={number + 1}
                            active={number + 1 === currentPage}
                            onClick={() => setCurrentPage(number + 1)}
                        >
                            {number + 1}
                        </Pagination.Item>
                    ))}
                </Pagination>
            </div>

            <div className="card-footer bg-light">
                <button
                    className="btn btn-primary float-right"
                    onClick={() => navigate('/users/new')}
                >
                    Novo Usuário
                </button>
            </div>
        </div>
    );
};

export default UserGrid;