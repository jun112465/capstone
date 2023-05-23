import React, {useRef, useState} from "react";
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

    return (
        <ControlBlockTag>
            <div className="title">CONTROL BLOCK</div>
            <div className="line-block">
                <div className="line top">
                    AC-DC SWITCH
                    <Toggle/>
                </div>
                <div className="line">
                    Relay 1
                    <Toggle/>
                </div>
                <div className="line">
                    Relay 2
                    <Toggle/>
                </div>
                <div className="line">
                    Relay 3
                    <Toggle/>
                </div>
                <div className="line">
                    DR 1-2
                    <Toggle/>
                </div>
                <div className="line">
                    DR 2-3
                    <Toggle/>
                </div>
                <div className="line bottom">
                    DR 3-1
                    <Toggle/>
                </div>
            </div>
        </ControlBlockTag>
    );
}

export default ControlBlock;
