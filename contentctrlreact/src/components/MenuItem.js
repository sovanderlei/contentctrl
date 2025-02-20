import React from 'react';

const MenuItem = ({ title, children, isSubMenuOpen, toggleSubMenu }) => {
    return (
        <li className="nav-item">
            <a className="nav-link" href="#" onClick={toggleSubMenu}>
                {title}
            </a>
            {children}
        </li>
    );
};

export default MenuItem;
