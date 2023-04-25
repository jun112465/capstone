import React, {useEffect, useState} from "react";
import styled from "styled-components";

import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";

const SolarTag = styled.div`
    margin: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
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

function Solar() {
    const [first, setFirst] = useState(true);
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);

    useEffect(() => {
        // 1초 그래프를 위한 코드
        const intervalId = setInterval(() => {
            fetch('/graph', {
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

        // 1일 그래프를 위한 코드
        if(first){
            setFirst(false);
            fetch('/graph', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    floor: 1,
                    interval: 1
                })
            })
                .then(response => response.json())
                .then(json => {
                    console.log(json);
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

        //
        return () => {
            clearInterval(intervalId);
        };

    }, [data]);

    return (
        <SolarTag>
            {/*1초 단위 차트*/}
            <LineChart width={500} height={350} data={data}
                       margin={{ top: 50, right: 30, left: 20, bottom: 5 }}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" style={{fontSize:'11'}}/>
                <YAxis style={{fontSize:'11'}}/>
                <Tooltip />
                <Legend />
                <Line type="monotone" dataKey="W" stroke="#8884d8" />
                <Line type="monotone" dataKey="A" stroke="#82ca9d" />
                <Line type="monotone" dataKey="V" stroke="#c3861d" />
            </LineChart>

            <LineChart width={500} height={350} data={data2}
                       margin={{ top: 50, right: 30, left: 20, bottom: 5 }}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" style={{fontSize:'11'}}/>
                <YAxis style={{fontSize:'11'}}/>
                <Tooltip />
                <Legend />
                <Line type="monotone" dataKey="W" stroke="#8884d8" />
                <Line type="monotone" dataKey="A" stroke="#82ca9d" />
                <Line type="monotone" dataKey="V" stroke="#c3861d" />
            </LineChart>
        </SolarTag>
    );
}

export default Solar;