import React, {useEffect, useState} from "react";
import styled from "styled-components";

import {Area, AreaChart, CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";

const SecChartTag = styled.div`
    display: flex;
    // flex-direction: column;
    justify-content: center;
    align-item: center;
    
    color: #3E54AC;
    .title{
        padding-left: 70px;
    }
`;

const getCurrentTime = ()=>{
    // 현재 시간을 나타내는 Date 객체 생성
    const now = new Date();

    // Date 객체에서 시간, 분, 초 값을 가져오기
    const hours = now.getHours().toString().padStart(2, "0");
    const minutes = now.getMinutes().toString().padStart(2, "0");
    const seconds = now.getSeconds().toString().padStart(2, "0");

    // 시간, 분, 초 값을 조합하여 HH:MM:SS 포맷으로 변환
    const currentTime = `${hours}:${minutes}:${seconds}`;

    return currentTime;
}

function SecChart() {
    const [data, setData] = useState([]);
    const [windowWidth, setWindowWidth] = useState(window.innerWidth);

    const handleResize = () => {
        setWindowWidth(window.innerWidth);
        console.log(windowWidth);
    };

    window.addEventListener('resize', handleResize);


    useEffect(() => {
        // 1초 그래프를 위한 코드
        const intervalId = setInterval(() => {
            fetch(process.env.REACT_APP_ORIGIN+'/solar-graph', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    floor: 1,
                    interval: 0
                })
            })
                .then(response => response.json())
                .then(json => {
                    const newData = [...data, {
                        "name" : getCurrentTime(),
                        "W": json[0].p,
                        "A": json[0].i,
                        "V": json[0].v,
                    }];

                    if(newData.length > 5){
                        newData.shift();
                    }
                    setData(newData);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }, 1000);

        //
        return () => {
            clearInterval(intervalId);
        };

    }, [data]);


    let renderLargeGraph = ()=>{
        return (
            <AreaChart width={700} height={350} data={data}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" style={{fontSize:'11'}}/>
                <YAxis style={{fontSize:'11'}}/>
                <Tooltip />
                <Area stackId="1" type="monotone" dataKey="W" stroke="#8884d8" fill="#8884d8" />
                <Area stackId="1" type="monotone" dataKey="A" stroke="#82ca9d" fill="#82ca9d"/>
                <Area stackId="1" type="monotone" dataKey="V" stroke="#c3861d" fill="#c3861d"/>

            </AreaChart>
        )
    }

    let renderSmallGraph = ()=>{
        return (
            <AreaChart width={300} height={350} data={data}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" style={{fontSize:'11'}}/>
                <YAxis style={{fontSize:'11'}}/>
                <Tooltip />
                <Area stackId="1" type="monotone" dataKey="W" stroke="#8884d8" fill="#8884d8" />
                <Area stackId="1" type="monotone" dataKey="A" stroke="#82ca9d" fill="#82ca9d"/>
                <Area stackId="1" type="monotone" dataKey="V" stroke="#c3861d" fill="#c3861d"/>

            </AreaChart>
        )
    }

    return (
        <SecChartTag>
            {/*1초 단위 차트*/}

            {windowWidth > 900 ? renderLargeGraph() : renderSmallGraph()}
            {/*<AreaChart width={700} height={350} data={data}>*/}
            {/*    <CartesianGrid strokeDasharray="3 3" />*/}
            {/*    <XAxis dataKey="name" style={{fontSize:'11'}}/>*/}
            {/*    <YAxis style={{fontSize:'11'}}/>*/}
            {/*    <Tooltip />*/}
            {/*    <Area stackId="1" type="monotone" dataKey="W" stroke="#8884d8" fill="#8884d8" />*/}
            {/*    <Area stackId="1" type="monotone" dataKey="A" stroke="#82ca9d" fill="#82ca9d"/>*/}
            {/*    <Area stackId="1" type="monotone" dataKey="V" stroke="#c3861d" fill="#c3861d"/>*/}
            {/*</AreaChart>*/}

        </SecChartTag>
    );
}

export default SecChart;