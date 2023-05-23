import React, { useState } from "react";
import Header from "./components/main/Header";
import MainFrame from "./components/main/MainFrame";
import SideMenu from "./components/main/SideMenu";
import styled from "styled-components";



const Main = styled.div`
    margin-top: 5px;
    display: flex;
    flex-direction: row;
    font-size: 21px;
`;


const TrashSide = styled.div`

    @media (max-width: 480px){
        display: none;
    } 
    flex: 2
`

function App() {
    return (
        <div>
            <Header/>
            <Main>
                <SideMenu/>
                <MainFrame/>
                <TrashSide/>
            </Main>
        </div>
        // <div>
        //     hello
        // </div>
    );
}

export default App;
