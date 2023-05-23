import React, {useEffect, useState} from "react";
import styled from "styled-components";

const DRTag = styled.div`
    display: flex;
    flex-direction: column;
`;

const TableTag = styled.table`
    color: #3E54AC;
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

function DRStatus(){
    const [switchStat, setSwitchStat] = useState(null)
    let greenTag = <div style={{ display: 'inline-block', width: '10px', textAlign:'center', height: '10px', backgroundColor: 'green', borderRadius: '50%' }}></div>;
    let redTag = <div style={{ display: 'inline-block', width: '10px', textAlign:'center', height: '10px', backgroundColor: 'red', borderRadius: '50%' }}></div>;

    useEffect(()=>{
        // relay status 값을 받아온 후 값을 업데이트 한다.
        fetch('/dr-status')
            .then(response => response.json())
            .then(data => {
                setSwitchStat(data);
            });
    }, []);
    return (
        <DRTag>
            <TableTag>
                <tr>
                    <th>contribute</th>
                    <th>status</th>
                </tr>
                <tr>
                    <td>1 - 2</td>
                    <td>{switchStat==1 ? greenTag : redTag}</td>
                </tr>
                <tr>
                    <td>2 - 3</td>
                    <td>{switchStat==1 ? redTag : greenTag}</td>
                </tr>
                <tr>
                    <td>3 - 1</td>
                    <td>{switchStat==1 ? greenTag : redTag}</td>
                </tr>
            </TableTag>
        </DRTag>
    )
}

export default DRStatus;
