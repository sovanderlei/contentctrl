import React from 'react';

const Sidebar = ({ isOpen, activeMenu, toggleMenu }) => {
    return (
        <aside
            className={`  shadow-sm ${isOpen ? 'sidebar-open' : 'sidebar-closed'}`}
            style={{
                width: isOpen ? '250px' : '0',
                transition: 'width 0.3s ease',
                backgroundColor: '#282d32',
                height: '100hv'
            }}
        >
            <nav className="nav flex-column p-3"   >
                <button
                    className="btn btn-link nav-link text-start"
                    onClick={() => toggleMenu('menu1')}
                >
                    Registration <i className="bi bi-chevron-down float-end"></i>
                </button>
                <div className={`collapse ${activeMenu === 'menu1' ? 'show' : ''}`}>
                    <div className="ps-3">
                        <a href="/home" className="nav-link">Home</a>
                        <a href="/about" className="nav-link">About</a>
                        <a href="/UserGrid" className="nav-link">User</a>
                        <a href="/CompaniesGrid" className="nav-link">Companies</a>
                        <a href="/BranchsGrid" className="nav-link">Branchs</a>
                    </div>
                </div>
                <button
                    className="btn btn-link nav-link text-start"
                    onClick={() => toggleMenu('menu2')}
                >
                    Dynamic Screen <i className="bi bi-chevron-down float-end"></i>
                </button>
                <div className={`collapse ${activeMenu === 'menu2' ? 'show' : ''}`}>
                    <div className="ps-3">
                        <a href="/DsNewFields" className="nav-link">New Fields</a>
                        <a href="/DsEnterData" className="nav-link">Enter Data</a>
                        <a href="/DsViewJson" className="nav-link">View Json</a>
                    </div>
                </div>
            </nav>
        </aside>
    );
};

export default Sidebar;