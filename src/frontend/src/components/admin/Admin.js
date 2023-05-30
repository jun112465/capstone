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
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleChange = (newValue) => {
        setIsLoggedIn(newValue);
    }

    let renderLoggedIn = ()=>{
        return (
            <div>
                <ControlBlock/>
                <CreateNewPw/>
            </div>
        )
    }
    return (
        <AdminTag>
            {isLoggedIn ? renderLoggedIn() : <Login handleChange={handleChange}/> }
        </AdminTag>
    );
}

export default Admin;
