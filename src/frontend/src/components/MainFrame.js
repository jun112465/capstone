import React, { useState } from "react";
import styled from "styled-components";
import {Routes, Route} from "react-router";

import Home from "./Home";
import Floor from "./Floor";
import DR from "./DR";
import {useLocation} from "react-router-dom";


const Frame = styled.div`
  flex: 6;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  // justify-content: left;
`;

const LocationDiv = styled.div`
    padding-top: 40px;
    padding-left: 60px;
    padding-bottom: 15px;
    font-size: 30px;
    font-weight: bold;
    color: #3E54AC;
    border-bottom: 2px solid #BFACE2;
    margin: 30px;
`


function MainFrame() {
    const location = useLocation();
    const pathname = location.pathname;
    let title = "HOME";

    if (pathname == '/1') title="FIRST FLOOR";
    else if(pathname == '/2') title="SECOND FLOOR";
    else if(pathname == '/3') title="THIRD FLOOR";
    else if(pathname == '/dr') title="DR DIAGRAM";


    return (
        <Frame>
            <LocationDiv>{title}</LocationDiv>
            <Routes>
                <Route path="/" element={<Home/>}></Route>
                <Route path="/1" element={<Floor/>}></Route>
                <Route path="/2" element={<Floor/>}></Route>
                <Route path="/3" element={<Floor/>}></Route>
                <Route path="/4" element={<Floor/>}></Route>
                <Route path="/dr" element={<DR/>}></Route>
            </Routes>
        </Frame>
    );
}

export default MainFrame;
