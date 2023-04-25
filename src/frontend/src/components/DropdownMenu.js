import React, { useState } from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes, faHouse, faCube, faDiagramProject } from "@fortawesome/free-solid-svg-icons";
import {Link, useLocation} from "react-router-dom";



let Nav = styled.div`
    flex: 2;
    display: flex;
    flex-direction: column;
    justify-content: start; 
    background-color: #3E54AC;
`;

const MenuList = styled.div`
    display: flex;
    flex-direction: column;
`
const MenuListChild = styled(Link)`
    font-size: 17px;
    display: block;
    text-decoration: none;
    color: white;
    font-weight: bold;
    text-align: center;
    border-top: 1px solid #655DBB;
    padding: 5px;
`

const iconStyle = {
    fontsize: '6px',
    marginRight: '3px',
}


function DropdownNav() {
    const location = useLocation();
    const pathname = location.pathname;
    const menuChildColor = "#3E54AC";

    return (
        <Nav>
            <MenuList>
                <MenuListChild to="/">
                    DASH
                </MenuListChild>
                <MenuListChild to="/solar">
                   SOLAR
                </MenuListChild>
                <MenuListChild to="/1">
                    1F
                </MenuListChild>
                <MenuListChild to="/2">
                    2F
                </MenuListChild>
                <MenuListChild to="/3">
                    3F
                </MenuListChild>
                <MenuListChild to="/dr">
                    DR
                </MenuListChild>
                <MenuListChild to="/admin">
                    ADMIN
                </MenuListChild>
            </MenuList>
        </Nav>
    );
}

export default DropdownNav;
