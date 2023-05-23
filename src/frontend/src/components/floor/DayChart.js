import React, {useEffect, useState} from "react";
import styled from "styled-components";

import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";

const DayChartTag = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-item: center;
    
    color: #3E54AC;
    .title{
        padding-left: 70px;
    }
`;

function DayChart(props) {
    const [first, setFirst] = useState(true);
    const [data2, setData2] = useState([]);

    useEffect(() => {
        // 1일 그래프를 위한 코드
        if(first){
            setFirst(false);
            fetch('/graph', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    floor: props.floor,
                    interval: 1
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
        }

    }, [data2]);

    return (
        <DayChartTag>
            <div className="title">Day Chart</div>
            <LineChart width={900} height={350} data={data2}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" style={{fontSize:'11'}}/>
                <YAxis style={{fontSize:'11'}}/>
                <Tooltip />
                <Legend />
                <Line type="monotone" dataKey="W" stroke="#8884d8" />
                <Line type="monotone" dataKey="A" stroke="#82ca9d" />
                <Line type="monotone" dataKey="V" stroke="#c3861d" />
            </LineChart>
        </DayChartTag>
    );
}

export default DayChart;