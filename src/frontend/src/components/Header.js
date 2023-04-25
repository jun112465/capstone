import React, { useState } from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faSolarPanel} from "@fortawesome/free-solid-svg-icons";
// import { faApple } from "@fortawesome/free-brands-svg-icons";
import { faBars, faXmark} from "@fortawesome/free-solid-svg-icons";
import DropdownNav from "./DropdownMenu";

const HeadDiv = styled.div`
    display: flex;
    padding-left: 60px;
    align-items: center;
    width: 100%;
    height: 60px;
    font-size: 20px;
    box-shadow: 2px 2px 2px 1px grey;
    font-weight: bold;
    background-color: #3E54AC;
    color: white;
    
    
    // 태블릿 화면 분기점
    @media (max-width: 800px) {
        justify-content: center;
        padding-left: 0px;
    }
    
    // 모바일 화면 분기점
    @media (max-width: 480px) {
        height: 48px;
        display: none;
    }
    
    
`

const MobileHeadDiv = styled.div`
    background-color: #3E54AC;
    color: white;
    height: 60px;
    display: none;
    
    
    
    // 모바일 화면 분기점
    @media (max-width: 480px) {
        display: flex;
        align-items: center;
        justify-content: center;
    }
`



const Header = ()=>{
    const [clicked, setClicked] = useState(false)

    return (
        <div>
            <HeadDiv>
                <FontAwesomeIcon
                    icon={faSolarPanel}
                    style={{
                        marginRight:'5px',
                    }}
                />
                <div>
                    Building Energy Management System
                </div>
            </HeadDiv>
            <MobileHeadDiv>
                <FontAwesomeIcon
                    icon={clicked ? faXmark : faBars}
                    style={{
                        position: 'absolute',
                        left: '20px',
                        fontSize: '35px'
                    }}
                    onClick={()=>setClicked(!clicked)}
                />
                <FontAwesomeIcon
                    icon={faSolarPanel}
                    style={{
                        fontSize: '20px'
                    }}
                />
            </MobileHeadDiv>
            {clicked ? <DropdownNav/> : null}
        </div>
    );
}

export default Header;
