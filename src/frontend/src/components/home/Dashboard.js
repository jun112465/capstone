import React, { useState } from "react";
import styled from "styled-components";

import {Line, LineChart, YAxis, XAxis, CartesianGrid, Tooltip, Legend} from "recharts";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";
import Relay from "./RelayStatus";
import SwitchStatus from "./SwitchStatus";
import LoadStatus from "./LoadStatus";
import NgnStatus from "./NgnStatus";
import DRStatus from "./DRStatus";

const HomeTag = styled.div`
    display: flex;
    flex-direction: column;
    margin: 10px;
    justify-content: space-around;
    
`;

const HomeRow = styled.div`
    @media (max-width: 800px) {
        flex-direction: column;
    }
    display: flex;
    flex: 1;
    flex-direction: row;
    justify-content: space-around;
    margin: 10px;
`
const HomeChild = styled.div`
    display: flex;
    flex: 1;
    flex-direction: column;
    margin: 10px;
    
    div {
        color: #3E54AC;
    }
`
const ChildTitle = styled.div`
    margin-bottom: 10px;
`

function Dashboard() {
    return (
        <HomeTag>
            <HomeRow>
                <HomeChild>
                    <ChildTitle>RELAY STATUS</ChildTitle>
                    <Relay/>
                </HomeChild>
                <HomeChild>
                    <ChildTitle>AC-DC SWITCH</ChildTitle>
                    <SwitchStatus/>
                </HomeChild>
            </HomeRow>
            <HomeRow>
                <HomeChild>
                    <ChildTitle>LOAD STATUS</ChildTitle>
                    <LoadStatus/>
                </HomeChild>
                <HomeChild>
                    <ChildTitle>NGN GENERATION STATUS</ChildTitle>
                    <NgnStatus/>
                </HomeChild>
            </HomeRow>
            <HomeRow>
                <HomeChild>
                    <ChildTitle>DR STATUS</ChildTitle>
                    <DRStatus/>
                </HomeChild>
                <HomeChild>
                    <ChildTitle>PREDICTION STATUS</ChildTitle>
                    <NgnStatus/>
                </HomeChild>
            </HomeRow>
        </HomeTag>
    );
}

export default Dashboard;
