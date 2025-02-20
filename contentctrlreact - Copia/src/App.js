import React, { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import './App2.css'
import Header from './components/Header';
import Footer from './components/Footer';
import Sidebar from './components/Sidebar';
import Home from './pages/Home';
import About from './pages/About';
import Dashboard from './pages/Dashboard';

const App = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);
  const [activeMenu, setActiveMenu] = useState(null);

  const user = {
    name: 'João da Silva',
    photo: 'user.jpg',
    handleLogout: () => console.log('Usuário deslogado')
  };

  const toggleMenu = (menu) => {
    setActiveMenu(activeMenu === menu ? null : menu);
  };

  return (
    <div className="container-fluid p-0 min-vh-100 d-flex flex-column">
      <Header
        onToggleSidebar={() => setIsSidebarOpen(!isSidebarOpen)}
        user={user}
      />

      <div className="d-flex flex-grow-1">
        <Sidebar
          isOpen={isSidebarOpen}
          activeMenu={activeMenu}
          toggleMenu={toggleMenu}
        />

        <main className="flex-grow-1 p-4" style={{
          marginLeft: isSidebarOpen ? '250px' : '0',
          transition: 'margin-left 0.3s ease'
        }}>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/home" element={<Home />} />
            <Route path="/about" element={<About />} />
          </Routes>
        </main>
      </div>

      <Footer />
    </div>
  );
};

export default App;