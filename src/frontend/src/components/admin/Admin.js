import React, {useRef, useState} from "react";
import styled from "styled-components";

import Login from "./Login";
import ControlBlock from "./ControlBlock";
import CreateNewPw from "./CreateNewPw";

const AdminTag = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
`;

function Admin() {
    return (
        <AdminTag>
            {/*<Login/>*/}
            <ControlBlock/>
            <CreateNewPw/>
        </AdminTag>
    );
}

export default Admin;
