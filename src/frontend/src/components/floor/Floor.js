// import React, {useEffect, useState} from "react";
// import styled from "styled-components";
//
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faApple } from "@fortawesome/free-brands-svg-icons";
// import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";
// import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";
// import DayChart from "./DayChart";
// import SecChart from "./SecChart";
//
// const FloorTag = styled.div`
//     margin: 10px;
//     display: flex;
//     flex-direction: column;
//     align-items: center;
//     justify-content: center;
// `;
//
// function Floor(props) {
//     return (
//         <FloorTag>
//             {/*1초 단위 차트*/}
//             <SecChart floor={props.floor}/>
//             <DayChart floor={props.floor}/>
//         </FloorTag>
//     );
// }
//
// export default Floor;


import React, {useEffect, useState} from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";
import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";
import SecChart from "./SecChart";
import DayChart from "./DayChart";
import MonthChart from "./MonthChart";

const FloorTag = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-item: center;
    
    color: #3E54AC;
    .title{
        // padding-left: 70px;
    }
    
     @media (max-width: 500px) {
        .chart{
            width: 400px;
        }
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

function Floor(props) {
    const [floor, setFloor] = useState(props.floor);
    const [first, setFirst] = useState(true);
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);
    const [selected, setSelected] = useState("SECOND CHART");

    let handleChange = (e)=>{
        setSelected(e.target.value);
    }

    let renderComponent = () => {
        if (selected === 'SECOND CHART') {
            return <SecChart floor={props.floor}/>;
        } else if (selected === 'DAY CHART') {
            return <DayChart floor={props.floor}/>;
        } else if (selected === 'MONTH CHART') {
            return <MonthChart floor={props.floor}/>;
        }

        // 선택한 옵션에 따라 다른 컴포넌트를 반환
    }

    useEffect(() => {
        console.log(floor, props.floor)
        if(floor != props.floor){
            setFloor(props.floor);
        }
        // console.log("selected floor : ",props.floor);
        // // 1초 그래프를 위한 코드
        // const intervalId = setInterval(() => {
        //     fetch('/graph', {
        //         method: 'POST',
        //         headers: { 'Content-Type': 'application/json' },
        //         body: JSON.stringify({
        //             floor: props.floor,
        //             interval: 0
        //         })
        //     })
        //         .then(response => response.json())
        //         .then(json => {
        //             const newData = [...data, {
        //                 "name" : getCurrentTime(),
        //                 "W": json[0].p,
        //                 "A": json[0].i,
        //                 "V": json[0].v,
        //             }];
        //
        //             if(newData.length > 5){
        //                 newData.shift();
        //             }
        //             setData(newData);
        //         })
        //         .catch(error => {
        //             console.error('Error:', error);
        //         });
        // }, 1000);

        // // 1일 그래프를 위한 코드
        // if(first){
        //     setFirst(false);
        //     fetch('/graph', {
        //         method: 'POST',
        //         headers: { 'Content-Type': 'application/json' },
        //         body: JSON.stringify({
        //             floor: props.floor,
        //             interval: 1
        //         })
        //     })
        //         .then(response => response.json())
        //         .then(json => {
        //             console.log(json);
        //             const newData = []
        //             json.forEach(e => {
        //                 newData.push({
        //                     "name": e.time.substring(5,10),
        //                     "W": e.p,
        //                     "A": e.i,
        //                     "V": e.v,
        //                 })
        //             })
        //             setData2(newData);
        //         })
        //         .catch(error => {
        //             console.error('Error:', error);
        //         });
        // }
        //
        // //
        // return () => {
        //     clearInterval(intervalId);
        // };

    });

    return (
        <FloorTag>
            <div>
                <select id="interval-select"  onChange={handleChange}>
                    <option value="">--Please choose an option--</option>
                    <option value="SECOND CHART">Second</option>
                    <option value="DAY CHART">Day</option>
                    <option value="MONTH CHART">Month</option>
                </select>
                <p>{selected}</p>
            </div>
            <div>
                {renderComponent()}
            </div>
            {/*<div>*/}
            {/*    <LineChart className="chart" width={700} height={350} data={data}>*/}
            {/*        <CartesianGrid strokeDasharray="3 3" />*/}
            {/*        <XAxis dataKey="name" style={{fontSize:'11'}}/>*/}
            {/*        <YAxis style={{fontSize:'11'}}/>*/}
            {/*        <Tooltip />*/}
            {/*        <Legend />*/}
            {/*        <Line type="monotone" dataKey="W" stroke="#8884d8" />*/}
            {/*        <Line type="monotone" dataKey="A" stroke="#82ca9d" />*/}
            {/*        <Line type="monotone" dataKey="V" stroke="#c3861d" />*/}
            {/*    </LineChart>*/}
            {/*</div>*/}

            {/*/!*1초 단위 차트*!/*/}
            {/*<LineChart className="chart" width={700} height={350} data={data}>*/}
            {/*    <CartesianGrid strokeDasharray="3 3" />*/}
            {/*    <XAxis dataKey="name" style={{fontSize:'11'}}/>*/}
            {/*    <YAxis style={{fontSize:'11'}}/>*/}
            {/*    <Tooltip />*/}
            {/*    <Legend />*/}
            {/*    <Line type="monotone" dataKey="W" stroke="#8884d8" />*/}
            {/*    <Line type="monotone" dataKey="A" stroke="#82ca9d" />*/}
            {/*    <Line type="monotone" dataKey="V" stroke="#c3861d" />*/}
            {/*</LineChart>*/}

            {/*<div className="title">Day Chart</div>*/}
            {/*<LineChart className="chart" width={700} height={350} data={data2}>*/}
            {/*    <CartesianGrid strokeDasharray="3 3" />*/}
            {/*    <XAxis dataKey="name" style={{fontSize:'11'}}/>*/}
            {/*    <YAxis style={{fontSize:'11'}}/>*/}
            {/*    <Tooltip />*/}
            {/*    <Legend />*/}
            {/*    <Line type="monotone" dataKey="W" stroke="#8884d8" />*/}
            {/*    <Line type="monotone" dataKey="A" stroke="#82ca9d" />*/}
            {/*    <Line type="monotone" dataKey="V" stroke="#c3861d" />*/}
            {/*</LineChart>*/}
        </FloorTag>
    );
}

export default Floor;