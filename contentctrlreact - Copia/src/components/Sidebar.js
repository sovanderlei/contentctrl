import React from 'react';
import { NavLink } from 'react-router-dom';

const Sidebar = ({ isOpen, activeMenu, toggleMenu }) => {
    const menuItems = [
        {
            title: 'Menu 1',
            submenu: [
                { title: 'Subitem 1', path: '/home' },
                { title: 'Subitem 2', path: '/about' }
            ]
        },
        {
            title: 'Menu 2',
            submenu: [
                { title: 'Subitem 3', path: '/sub3' },
                { title: 'Subitem 4', path: '/sub4' }
            ]
        }
    ];

    return (
        <aside
            className={`bg-light shadow-sm ${isOpen ? 'sidebar-open' : 'sidebar-closed'}`}
            style={{
                width: isOpen ? '250px' : '0',
                transition: 'width 0.3s ease'
            }}
        >
            <nav className="nav flex-column p-3">
                {menuItems.map((menu, index) => (
                    <div key={index}>
                        <button
                            className="btn btn-link nav-link text-start w-100"
                            onClick={() => toggleMenu(`menu${index + 1}`)}
                        >
                            {menu.title}
                            <i className={`bi bi-chevron-${activeMenu === `menu${index + 1}` ? 'up' : 'down'} float-end`}></i>
                        </button>

                        <div className={`collapse ${activeMenu === `menu${index + 1}` ? 'show' : ''}`}>
                            <div className="ps-3">
                                {menu.submenu.map((subitem, subIndex) => (
                                    <NavLink
                                        key={subIndex}
                                        to={subitem.path}
                                        className={({ isActive }) =>
                                            `nav-link ${isActive ? 'active fw-bold' : ''}`
                                        }
                                    >
                                        {subitem.title}
                                    </NavLink>
                                ))}
                            </div>
                        </div>
                    </div>
                ))}
            </nav>
        </aside>
    );
};

export default Sidebar;