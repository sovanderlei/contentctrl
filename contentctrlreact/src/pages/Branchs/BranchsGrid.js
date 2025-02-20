import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Table, Pagination } from 'react-bootstrap';
import { branchService } from '../../services/branchService'; // Service para filiais
import { FiEdit, FiTrash2 } from 'react-icons/fi';
import './Branch.css';

const BranchGrid = () => {
    const [branches, setBranches] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const navigate = useNavigate();
    const itemsPerPage = 10;

    useEffect(() => {
        const loadBranches = async () => {
            try {
                const data = await branchService.getAll();  // Chama o service para pegar as filiais
                setBranches(data);
            } catch (error) {
                console.error('Erro ao carregar filiais:', error);
            }
        };
        loadBranches();
    }, []);

    // Paginação
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = branches.slice(indexOfFirstItem, indexOfLastItem);
    const totalPages = Math.ceil(branches.length / itemsPerPage);

    const handleEdit = (branchId) => {
        navigate(`/branches/edit/${branchId}`);
    };

    const handleDelete = async (branchId) => {
        if (window.confirm('Deseja realmente excluir esta filial?')) {
            await branchService.delete(branchId);
            setBranches(branches.filter(branch => branch.id !== branchId));
        }
    };

    return (
        <div className="card branch-grid-card">
            <div className="card-header bg-primary text-white">
                <h3 className="mb-0">Listagem de Filiais</h3>
            </div>

            <div className="card-body">
                <Table striped hover responsive>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Endereço</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {currentItems.map(branch => (
                            <tr key={branch.id}>
                                <td>{branch.id}</td>
                                <td>{branch.name}</td>
                                <td>{branch.address}</td>
                                <td>
                                    <button
                                        className="btn btn-sm btn-link"
                                        onClick={() => handleEdit(branch.id)}
                                    >
                                        <FiEdit size={18} />
                                    </button>
                                    <button
                                        className="btn btn-sm btn-link text-danger"
                                        onClick={() => handleDelete(branch.id)}
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
                    onClick={() => navigate('/branches/new')}
                >
                    Nova Filial
                </button>
            </div>
        </div>
    );
};

export default BranchGrid;
