import React, {useEffect, useState} from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";
const RelayTag = styled.div`
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
        padding: 10px;
        text-align: center;
    }
`



function Relay() {
    const [status, setStatus] = useState([]);
    let greenTag = <div style={{ display: 'inline-block', width: '10px', textAlign:'center', height: '10px', backgroundColor: 'green', borderRadius: '50%' }}></div>;
    let redTag = <div style={{ display: 'inline-block', width: '10px', textAlign:'center', height: '10px', backgroundColor: 'red', borderRadius: '50%' }}></div>;

    useEffect(()=>{
        // relay status 값을 받아온 후 값을 업데이트 한다.
        fetch(process.env.REACT_APP_ORIGIN+'/relay-status')
            .then(response => response.json())
            .then(data => {
                console.log(data)
                let tmpStatus = [];
                for(let i in data)
                    tmpStatus[data[i].floor-1] = data[i].status;
                setStatus(tmpStatus);
            });
    }, []);

    return (
        <RelayTag>
            <TableTag>
                <tr>
                    <th>floor</th>
                    <th>status</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>
                        {
                            status[0] ? greenTag : redTag
                        }
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>
                        {
                            status[1] ? greenTag : redTag
                        }
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>
                        {
                            status[2] ? greenTag : redTag
                        }
                    </td>
                </tr>
            </TableTag>
        </RelayTag>
    );
}

export default Relay;