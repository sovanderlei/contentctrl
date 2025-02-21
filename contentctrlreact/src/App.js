import React, { useState } from 'react';
import { useNavigate, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import './App2.css';
import Header from './components/Header';
import Footer from './components/Footer';
import Sidebar from './components/Sidebar';
import Login from './pages/Login/Login';
import * as importsPages from './importsPages';

const App = () => {
  const navigate = useNavigate();
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);
  const [activeMenu, setActiveMenu] = useState(null);
  const location = useLocation();

  const isLoginenticated = !!localStorage.getItem('token'); // Simples verificação de autenticação
  const isLoginPage = location.pathname === '/login';

  const user = {
    name: 'Vanderlei S. Oliveira',
    photo: '../images/logo.png',
    handleLogout: () => {
      localStorage.removeItem('token'); // Remove o token ao deslogar
      console.log('Usuário deslogado');
      navigate('/login');
    }
  };

  if (isLoginPage) {
    return <Login />;
  }
  // <Route path="/" element={<importsPages.Dashboard />} />
  return isLoginenticated ? (
    <div className="container-fluid p-0 min-vh-100 d-flex flex-column">
      <Header onToggleSidebar={() => setIsSidebarOpen(!isSidebarOpen)} user={user} />
      <div className="d-flex flex-grow-1" style={{ color: 'white', backgroundColor: '#343a40' }}>
        <Sidebar isOpen={isSidebarOpen} activeMenu={activeMenu} toggleMenu={setActiveMenu} />
        <main className="flex-grow-1" style={{ marginLeft: isSidebarOpen ? '5px' : '0', transition: 'margin-left 0.3s ease', margin: '5px' }}>
          <Routes>
            <Route path="/home" element={<importsPages.Home />} />
            <Route path="/about" element={<importsPages.About />} />

            <Route path="/UserGrid" element={<importsPages.UserGrid />} />
            <Route path="/users" element={<importsPages.UserGrid />} />
            <Route path="/users/new" element={<importsPages.UserForm />} />
            <Route path="/users/edit/:id" element={<importsPages.UserForm />} />

            <Route path="/CompaniesGrid" element={<importsPages.CompaniesGrid />} />
            <Route path="/companies" element={<importsPages.CompaniesGrid />} />
            <Route path="/companies/new" element={<importsPages.CompaniesForm />} />
            <Route path="/companies/edit/:id" element={<importsPages.CompaniesForm />} />

            <Route path="/BranchsGrid" element={<importsPages.BranchsGrid />} />
            <Route path="/branches" element={<importsPages.BranchsGrid />} />
            <Route path="/branches/new" element={<importsPages.BranchsForm />} />
            <Route path="/branches/edit/:id" element={<importsPages.BranchsForm />} />

            <Route path="/DsNewFields" element={<importsPages.DsNewFields />} />
            <Route path="/DsEnterData" element={<importsPages.DsEnterData />} />
            <Route path="/DsViewJson" element={<importsPages.DsViewJson />} />
            <Route path="*" element={<Navigate to="/" />} />
          </Routes>
        </main>
      </div>
      <Footer />
    </div>
  ) : (
    <Navigate to="/login" />
  );
};

export default App;
