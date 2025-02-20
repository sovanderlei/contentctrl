import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Table, Pagination } from 'react-bootstrap';
import { companyService } from '../../services/companyService'; // Service para empresas
import { FiEdit, FiTrash2 } from 'react-icons/fi';
import './Company.css';

const CompanyGrid = () => {
    const [companies, setCompanies] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const navigate = useNavigate();
    const itemsPerPage = 10;

    useEffect(() => {
        const loadCompanies = async () => {
            try {
                const data = await companyService.getAll();  // Chama o service para pegar as empresas
                setCompanies(data);
            } catch (error) {
                console.error('Erro ao carregar empresas:', error);
            }
        };
        loadCompanies();
    }, []);

    // Paginação
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = companies.slice(indexOfFirstItem, indexOfLastItem);
    const totalPages = Math.ceil(companies.length / itemsPerPage);

    const handleEdit = (companyId) => {
        navigate(`/companies/edit/${companyId}`);
    };

    const handleDelete = async (companyId) => {
        if (window.confirm('Deseja realmente excluir esta empresa?')) {
            await companyService.delete(companyId);
            setCompanies(companies.filter(company => company.id !== companyId));
        }
    };

    return (
        <div className="card company-grid-card">
            <div className="card-header bg-primary text-white">
                <h3 className="mb-0">Listagem de Empresas</h3>
            </div>

            <div className="card-body">
                <Table striped hover responsive>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Endereço</th>
                            <th>Telefone</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {currentItems.map(company => (
                            <tr key={company.id}>
                                <td>{company.id}</td>
                                <td>{company.name}</td>
                                <td>{company.address}</td>
                                <td>{company.phoneNumber}</td>
                                <td>
                                    <button
                                        className="btn btn-sm btn-link"
                                        onClick={() => handleEdit(company.id)}
                                    >
                                        <FiEdit size={18} />
                                    </button>
                                    <button
                                        className="btn btn-sm btn-link text-danger"
                                        onClick={() => handleDelete(company.id)}
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
                    onClick={() => navigate('/companies/new')}
                >
                    Nova Empresa
                </button>
            </div>
        </div>
    );
};

export default CompanyGrid;
