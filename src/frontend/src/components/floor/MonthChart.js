import React, {useEffect, useState} from "react";
import styled from "styled-components";

import {Area, AreaChart, CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";

const MonthChartTag = styled.div`
    display: flex;
    // flex-direction: column;
    justify-content: center;
    align-item: center;
    
    color: #3E54AC;
    .title{
        padding-left: 70px;
    }
`;

function MonthChart(props) {
    const [windowWidth, setWindowWidth] = useState(window.innerWidth);
    const [data2, setData2] = useState([]);

    useEffect(() => {

        const handleResize = () => {
            setWindowWidth(window.innerWidth);
            console.log(windowWidth);
        };

        window.addEventListener('resize', handleResize);

        // 1일 그래프를 위한 코드
        fetch(process.env.REACT_APP_ORIGIN+'/graph', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                floor: props.floor,
                interval: 2
            })
        })
            .then(response => response.json())
            .then(json => {
                console.log(json);
                // json = JSON.stringify(json);
                const newData = []
                json.forEach(e => {
                    newData.push({
                        "name": e.time.substring(5,10),
                        "W": e.p,
                        "A": e.i,
                        "V": e.v,
                    })
                })
                setData2(newData);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }, []);

    let renderLargeGraph = ()=>{
        return (
            <AreaChart width={700} height={350} data={data2}>
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
            <AreaChart width={300} height={350} data={data2}>
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
        <MonthChartTag>
            {windowWidth > 900 ? renderLargeGraph() : renderSmallGraph()}
            {/*<AreaChart width={700} height={350} data={data2}>*/}
            {/*    <CartesianGrid strokeDasharray="3 3" />*/}
            {/*    <XAxis dataKey="name" style={{fontSize:'11'}}/>*/}
            {/*    <YAxis style={{fontSize:'11'}}/>*/}
            {/*    <Tooltip />*/}
            {/*    <Area stackId="1" type="monotone" dataKey="W" stroke="#8884d8" fill="#8884d8" />*/}
            {/*    <Area stackId="1" type="monotone" dataKey="A" stroke="#82ca9d" fill="#82ca9d"/>*/}
            {/*    <Area stackId="1" type="monotone" dataKey="V" stroke="#c3861d" fill="#c3861d"/>*/}

            {/*</AreaChart>*/}
        </MonthChartTag>
    );
}

export default MonthChart;