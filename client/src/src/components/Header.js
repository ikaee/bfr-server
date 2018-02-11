import React from "react";

const Header = ({title, date}) =>

    <header className="header white-bg">
        <div className="sidebar-toggle-box">
            <i className="fa fa-bars"></i>
        </div>
        <a href="/" className="logo">{title}<span style={{paddingLeft: "50px"}}>{date}</span></a>
    </header>
export default Header;