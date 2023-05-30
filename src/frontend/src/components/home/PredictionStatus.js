import React, {useEffect, useState} from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";
const PredictionTag = styled.div`
    display: flex;
    flex-direction: column;
`;

const TableTag = styled.table`
    color: #3E54AC;
    text-align: center;
    border-spacing: 10px;
    border-collapse: separate;
    background-color: white;
    border: 1px solid black;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 1px 2px 2px 1px gray;
    
    th,td {
        text-align: center;
    }
`



function PredictionStatus() {
    const [value, setValue] = useState(null);

    useEffect(()=>{
        // relay status 값을 받아온 후 값을 업데이트 한다.
        fetch('/forecast')
            .then(response => response.json())
            .then(data => {
                console.log(data)
                let tmpValue;
                tmpValue = {
                    load : data.load,
                    gen : data.gen,
                }
                setValue(tmpValue);
            });
    }, []);

    if(!value){
        return <div>Loading....</div>
    }

    return (
        <PredictionTag>
            <TableTag>
                <tr>
                    <th>LOAD (단위 : W)</th>
                    <th>GENERATED (단위 : W)</th>
                </tr>
                <tr>
                    <td>{value.load}</td>
                    <td>{value.gen}</td>
                </tr>
            </TableTag>
        </PredictionTag>
    );
}

export default PredictionStatus;