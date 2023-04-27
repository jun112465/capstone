import React, { useState } from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";

const AdminTag = styled.div`
    display: flex;
    flex-direction: column;
    
    div{
        display: flex;
        flex-direction: column;
        width: 100%:
        padding: 0;
        padding-left: 15px;
        padding-right: 15px;
        
    }
    
    
    input{
        color: #655DBB;
        border: 1px solid #655DBB;
        border-radius: 5px;
        padding: 15px;
        margin: 10px;
        text-align: center;
    }
    
    input[type=button]{
        color: white;
        font-size: 13px;
        font-weight: bold;
        background-color: #3E54AC;
    }
    
    
    
`;


function Admin() {
    return (
        <AdminTag>
            <div>
                <input type="password" placeholder="ENTER PASSWORD"/>
                <input type="button" value="LOGIN"/>
            </div>
        </AdminTag>
    );
}

export default Admin;
