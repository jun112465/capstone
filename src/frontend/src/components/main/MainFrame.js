import React, { useState } from "react";
import styled from "styled-components";
import {Routes, Route} from "react-router";

import Dashboard from "../home/Dashboard";
import Floor from "../floor/Floor";
import DR from "../dr/DR";
import Solar from "../solar/Solar";
import Admin from "../admin/Admin";
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
    // padding-left: 60px;
    padding-bottom: 15px;
    font-size: 30px;
    font-weight: bold;
    color: #3E54AC;
    border-bottom: 2px solid #BFACE2;
    // margin: 30px;
    // margin-left: 0px;
    // width: 100%;
    
    // 모바일 화면 분기점
    @media (max-width: 480px) {
        padding: 0px;
        text-align: center;
    }
`


function MainFrame() {
    const location = useLocation();
    const pathname = location.pathname;
    let title = "DASHBOARD";

    if (pathname == '/1') title="FIRST FLOOR";
    else if(pathname == '/2') title="SECOND FLOOR";
    else if(pathname == '/3') title="THIRD FLOOR";
    else if(pathname == '/dr') title="DR DIAGRAM";
    else if(pathname == '/admin') title="ADMIN";
    else if(pathname == '/solar') title="SOLAR";



    return (
        <Frame>
            <LocationDiv>{title}</LocationDiv>
            <Routes>
                <Route path="/" element={<Dashboard/>}></Route>
                <Route key="floor-1" path="/1" element={<Floor floor={1}/>}></Route>
                <Route key="floor-2" path="/2" element={<Floor floor={2}/>}></Route>
                <Route key="floor-3" path="/3" element={<Floor floor={3}/>}></Route>
                <Route path="/4" element={<Floor/>}></Route>
                <Route path="/dr" element={<DR/>}></Route>
                <Route path="/admin" element={<Admin/>}></Route>
                <Route path="/solar" element={<Solar/>}></Route>

            </Routes>
        </Frame>
    );
}

export default MainFrame;
