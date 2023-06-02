import React, {useRef, useState} from "react";
import styled from "styled-components";
import Toggle from "./Toggle";

const LoginTag = styled.div`
    display: flex;
    flex-direction: column;
    
    div{
        display: flex;
        flex-direction: column;
        width: 100%:
        padding: 0;
        padding-left: 15px;
        padding-right: 15px;
    }
    
    
    input{
        color: #655DBB;
        border: 1px solid #655DBB;
        border-radius: 5px;
        padding: 15px;
        margin: 10px;
        text-align: center;
    }
    
    input[type=button]{
        color: white;
        font-size: 13px;
        font-weight: bold;
        background-color: #3E54AC;
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
        
        background-color: white;
    }
`;

function Login(props) {
    const [pwd,setPwd] = useState("");
    const pwdSubmitRef = useRef(null);

    const handlePasswordChange = (e)=>{
        setPwd(e.target.value);
    }

    let sendLoginRequest = ()=>{
        console.log(pwd);
        fetch(process.env.REACT_APP_ORIGIN+'/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },

            body: JSON.stringify({
                'pwd': pwd,
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                alert(data.message)
                if(data.success) props.handleChange(true);
            });
    }
    return (
        <LoginTag>
            <div>
                <input value={pwd} onChange={handlePasswordChange} type="password" placeholder="ENTER PASSWORD"/>
                <input ref={pwdSubmitRef} onClick={sendLoginRequest} type="button" value="LOGIN"/>
            </div>
        </LoginTag>
    );
}

export default Login;
