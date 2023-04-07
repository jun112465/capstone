import React, {useEffect, useState} from "react";
import styled from "styled-components";

const SwitchTag = styled.div`
    display: flex;
    flex-direction: column;
`;

const TableTag = styled.table`
    text-align: center;
    background-color: white;
    
    border-radius: 10px;
    border-spacing: 10px;
    border: 1px solid black;
    box-shadow: 1px 2px 2px 1px gray;

    
    overflow: hidden;
    
    th,td {
        padding: 10px;
        text-align: center;
    }
`;

function SwitchStatus(){
    const [switchStat, setSwitchStat] = useState(null)
    let greenTag = <div style={{ display: 'inline-block', width: '10px', textAlign:'center', height: '10px', backgroundColor: 'green', borderRadius: '50%' }}></div>;
    let redTag = <div style={{ display: 'inline-block', width: '10px', textAlign:'center', height: '10px', backgroundColor: 'red', borderRadius: '50%' }}></div>;

    useEffect(()=>{
        // relay status 값을 받아온 후 값을 업데이트 한다.
        fetch('/switch-status')
            .then(response => response.json())
            .then(data => {
                setSwitchStat(data);
            });
    }, []);
    return (
        <SwitchTag>
            <TableTag>
                <tr>
                    <th>contribute</th>
                    <th>status</th>
                </tr>
                <tr>
                    <td>relay</td>
                    <td>{switchStat==1 ? greenTag : redTag}</td>
                </tr>
                <tr>
                    <td>DC</td>
                    <td>{switchStat==1 ? redTag : greenTag}</td>
                </tr>
                <tr>
                    <td>AC</td>
                    <td>{switchStat==1 ? greenTag : redTag}</td>
                </tr>
            </TableTag>
        </SwitchTag>
    )
}

export default SwitchStatus;
