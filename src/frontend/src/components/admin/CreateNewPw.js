import React, {useRef, useState} from "react";
import styled from "styled-components";
import Toggle from "./Toggle";

const CreateNewPwTag = styled.div`

    margin-bottom: 20px;

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
        justify-content: center;
        padding: 10px;
        margin: 0px;
        
        font-size: 16px;
        background-color: white;
        border-bottom: 1px solid silver;
        text-align: center;
    }
    .enter{
        background-color: #3E54AC;
        color: white;
    }
    .top{
        border-radius: 5px 5px 0px 0px;
    }
    .bottom{
        border-radius: 0px 0px 5px 5px; 
    }

`;

function CreateNewPw() {
    const [beforePwd,setBeforePwd] = useState("");
    const [afterPwd,setAfterPwd] = useState("");

    const handleBeforePwdChange = (e)=>{
        setBeforePwd(e.target.value);
    }
    const handleAfterPwdChange = (e)=>{
        setAfterPwd(e.target.value);
    }

    let changePwdRequest = ()=>{
        console.log(beforePwd, afterPwd);
        fetch('/change-password', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                'before': beforePwd,
                'after': afterPwd
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                alert(data.message)
            });
    }

    return (
        <CreateNewPwTag>
            <div className="title">PASSWORD</div>
            <div className="line-block">
                <input id="beforePwd" className="line top" placeholder="ENTER CURRENT PASSWORD" onChange={handleBeforePwdChange}>
                </input>

                <input id="afterPwd" className="line" placeholder="ENTER NEW PASSWORD" onChange={handleAfterPwdChange}>
                </input>

                <input className="line bottom enter" type="button" value="CHANGE PASSWORD" onClick={changePwdRequest}/>
            </div>
        </CreateNewPwTag>
    );
}

export default CreateNewPw;
