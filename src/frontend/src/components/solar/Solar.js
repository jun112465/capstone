import React, {useEffect, useState} from "react";
import styled from "styled-components";

import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";
import SecChart from "./SecChart";
import DayChart from "./DayChart";
import MonthChart from "./MonthChart";

const SolarTag = styled.div`

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-item: center;
    
    color: #3E54AC;
    .title{
        padding-left: 70px;
    }
    
    
     @media (max-width: 500px) {
    }
`;

function Solar() {
    const [first, setFirst] = useState(true);
    const [data, setData] = useState([]);
    const [data2, setData2] = useState([]);
    const [selected, setSelected] = useState("SECOND CHART");

    let handleChange = (e)=>{
        setSelected(e.target.value);
    }

    let renderComponent = () => {
        if (selected === 'SECOND CHART') {
            return <SecChart/>;
        } else if (selected === 'DAY CHART') {
            return <DayChart/>;
        } else if (selected === 'MONTH CHART') {
            return <MonthChart />;
        }

        // 선택한 옵션에 따라 다른 컴포넌트를 반환
    }


    return (
        <SolarTag>

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
        </SolarTag>
    );
}

export default Solar;