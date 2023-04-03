import React, { useState } from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes, faHouse, faCube, faDiagramProject } from "@fortawesome/free-solid-svg-icons";
import {Link, useLocation} from "react-router-dom";



let SideNav = styled.div`
    padding-top: 30px;
    flex: 2;
    display: flex;
    justify-content: end; 
    align-items: center;
`;

const MenuList = styled.div`
    display: flex;
    flex-direction: column;
`
const MenuListChild = styled(Link)`
    margin: 15px;
    font-size: 21px;
    display: block;
    text-decoration: none;
    color: black;
    font-weight: bold;
`

const iconStyle = {
    fontsize: '6px',
    marginRight: '3px',
}


function SideNavbar() {
    const location = useLocation();
    const pathname = location.pathname;
    const menuChildColor = "#3E54AC";

    return (
        <SideNav>
            <MenuList>
                <MenuListChild to="/"  style={{color:pathname=='/' ? menuChildColor : 'black'}}>
                    <FontAwesomeIcon size="xs" style={iconStyle} icon={faHouse}/>
                    Home
                </MenuListChild>
                <MenuListChild to="/1" style={{color:pathname=='/1' ? menuChildColor : 'black'}}>
                    <FontAwesomeIcon size="xs" style={iconStyle} icon={faCube}/>
                    1F
                </MenuListChild>
                <MenuListChild to="/2" style={{color:pathname=='/2' ? menuChildColor : 'black'}}>
                    <FontAwesomeIcon size="xs" style={iconStyle} icon={faCube}/>
                    2F
                </MenuListChild>
                <MenuListChild to="/3" style={{color:pathname=='/3' ? menuChildColor : 'black'}}>
                    <FontAwesomeIcon size="xs" style={iconStyle} icon={faCube}/>
                    3F
                </MenuListChild>
                <MenuListChild to="/dr" style={{color:pathname=='/dr' ? menuChildColor : 'black'}}>
                    <FontAwesomeIcon size="xs" style={iconStyle} icon={faDiagramProject}/>
                    DR
                </MenuListChild>
            </MenuList>
        </SideNav>
    );
}

export default SideNavbar;
