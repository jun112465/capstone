import React, { useState } from "react";
import styled from "styled-components";

import {Line, LineChart, YAxis, XAxis, CartesianGrid, Tooltip, Legend} from "recharts";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";

const HomeTag = styled.div`
    display: flex;
    flex-direction: column;
    margin: 10px;
    justify-content: space-around;
`;

const HomeRow = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    margin: 10px;
`
const HomeChild = styled.div`
    display: flex;
    flex-direction: column;
    
    div {
        color : #655DBB;
    }
`

const data = [
    {
        "name": "Page A",
        "uv": 4000,
        "pv": 2400,
        "amt": 2400
    },
    {
        "name": "Page B",
        "uv": 3000,
        "pv": 1398,
        "amt": 2210
    },
    {
        "name": "Page C",
        "uv": 2000,
        "pv": 13000,
        "amt": 2290
    },
    {
        "name": "Page D",
        "uv": 2780,
        "pv": 3908,
        "amt": 2000
    },
    {
        "name": "Page E",
        "uv": 1890,
        "pv": 4800,
        "amt": 2181
    },
    {
        "name": "Page F",
        "uv": 2390,
        "pv": 3800,
        "amt": 2500
    },
    {
        "name": "Page G",
        "uv": 3490,
        "pv": 4300,
        "amt": 2100
    }
]


function Home() {
    return (
        <HomeTag>
            <HomeRow>
                <HomeChild>
                    <div>Floor Power</div>
                    <LineChart width={400} height={250} data={data}
                               margin={{ top: 50, right: 30, left: 20, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="name" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Line type="monotone" dataKey="pv" stroke="#8884d8" />
                        <Line type="monotone" dataKey="uv" stroke="#82ca9d" />
                        <Line type="monotone" dataKey="amt" stroke="#c3861d" />
                    </LineChart>
                </HomeChild>
                <HomeChild>
                    <div>Solar Panel</div>
                    <LineChart width={400} height={250} data={data}
                               margin={{ top: 50, right: 30, left: 20, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="name" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Line type="monotone" dataKey="pv" stroke="#8884d8" />
                        <Line type="monotone" dataKey="uv" stroke="#82ca9d" />
                        <Line type="monotone" dataKey="amt" stroke="#c3861d" />
                    </LineChart>
                </HomeChild>
            </HomeRow>
            <HomeRow>
                <HomeChild>
                    <div>Battery</div>
                    <LineChart width={400} height={250} data={data}
                               margin={{ top: 50, right: 30, left: 20, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="name" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Line type="monotone" dataKey="pv" stroke="#8884d8" />
                        <Line type="monotone" dataKey="uv" stroke="#82ca9d" />
                        <Line type="monotone" dataKey="amt" stroke="#c3861d" />
                    </LineChart>
                </HomeChild>
                <HomeChild>
                    <div>Relay</div>
                    <LineChart width={400} height={250} data={data}
                               margin={{ top: 50, right: 30, left: 20, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="name" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Line type="monotone" dataKey="pv" stroke="#8884d8" />
                        <Line type="monotone" dataKey="uv" stroke="#82ca9d" />
                        <Line type="monotone" dataKey="amt" stroke="#c3861d" />
                    </LineChart>
                </HomeChild>
            </HomeRow>

        </HomeTag>
    );
}

export default Home;
