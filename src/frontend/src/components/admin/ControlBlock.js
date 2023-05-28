import React, {useEffect, useRef, useState} from "react";
import styled from "styled-components";
import Toggle from "./Toggle";

const ControlBlockTag = styled.div`

    .title{
        padding: 2px;
        margin: 0px;
        margin-top: 15px;
        margin-right: 15px;
        margin-left: 15px;
        
        color: #3E54AC        
    }
    
    .line-block{
        display: flex;
        flex-direction: column;
        
        
        border: 2px solid #3E54AC;
        border-radius: 10px;
        padding: 2px;
        margin: 0px;
        margin-right: 15px;
        margin-left: 15px;
    }
    .line{
        display: flex;
        flex-direction: row;       
        align-items: center;
        justify-content: space-between;
        padding: 10px;
        margin: 0px;
        
        font-size: 16px;
        background-color: white;
        border-bottom: 1px solid silver;
    }
    
    .top{
        border-radius: 5px 5px 0px 0px;
    }
    .bottom{
        border-radius: 0px 0px 5px 5px; 
    }
`;

function ControlBlock() {
    const [switchStatus, setSwitchStatus] = useState(false);
    const [relayStatus, setRelayStatus] = useState([false, false, false]);
    const [DrStatus, setDrStatus] = useState([false, false, false]);

    useEffect(()=>{
        fetch('/switch-status')
            .then(response => response.json())
            .then(data => {
                console.log("switch", data)
                setSwitchStatus(data);
            });

        fetch('/relay-status')
            .then(response => response.json())
            .then(data => {
                console.log("relay", data)
                let tmpStatus = [];
                for(let i in data)
                    tmpStatus[data[i].floor-1] = data[i].status;
                setRelayStatus(tmpStatus);
            });

        fetch('/dr-status')
            .then(response => response.json())
            .then(data => {
                console.log("dr", data)
                let tmpStatus = [];
                for(let i in data)
                    tmpStatus[i] = data[i].status;
                setDrStatus(tmpStatus);
            })

    }, [])
    return (
        <ControlBlockTag>
            <div className="title">CONTROL BLOCK</div>
            <div className="line-block">
                <div className="line top">
                    AC-DC SWITCH
                    <Toggle relayId={1} status={switchStatus}/>
                </div>
                <div className="line">
                    Relay 1
                    <Toggle relayId={2} status={relayStatus[0]}/>
                </div>
                <div className="line">
                    Relay 2
                    <Toggle relayId={3} status={relayStatus[1]}/>
                </div>
                <div className="line">
                    Relay 3
                    <Toggle relayId={4} status={relayStatus[2]}/>
                </div>
                <div className="line">
                    DR 1-2
                    <Toggle relayId={5} status={DrStatus[0]}/>
                </div>
                <div className="line">
                    DR 2-3
                    <Toggle relayId={6} status={DrStatus[1]}/>
                </div>
                <div className="line bottom">
                    DR 3-1
                    <Toggle relayId={7} status={DrStatus[2]}/>
                </div>
            </div>
        </ControlBlockTag>
    );
}

export default ControlBlock;
