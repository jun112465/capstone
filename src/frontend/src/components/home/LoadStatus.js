import React, {useEffect, useState} from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";
const LoadTag = styled.div`
    display: flex;
    flex-direction: column;
`;

const TableTag = styled.table`
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



function Load() {
    const [value, setValue] = useState(null);

    useEffect(()=>{
        // relay status 값을 받아온 후 값을 업데이트 한다.
        fetch('/load-status')
            .then(response => response.json())
            .then(data => {
                console.log(data)
                let tmpValue = [];
                for(let i in data)
                    tmpValue[i] = {
                        v : data[i].v,
                        i : data[i].i,
                    }
                setValue(tmpValue);
                console.log(tmpValue[0].v)
                console.log("tmpValue", tmpValue);
            });
    }, []);

    if(!value){
        return <div>Loading....</div>
    }

    return (
        <LoadTag>
            <TableTag>
                <tr>
                    <th>floor</th>
                    <th>V</th>
                    <th>A</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>{value[0].v}</td>
                    <td>{value[0].i}</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>{value[1].v}</td>
                    <td>{value[1].i}</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>{value[2].v}</td>
                    <td>{value[2].i}</td>
                </tr>
            </TableTag>
        </LoadTag>
    );
}

export default Load;