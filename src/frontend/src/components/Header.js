import React, { useState } from "react";
import styled from "styled-components";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faSolarPanel} from "@fortawesome/free-solid-svg-icons";
// import { faApple } from "@fortawesome/free-brands-svg-icons";
// import { faBars, faUser, faTimes } from "@fortawesome/free-solid-svg-icons";

import {Link} from "react-router-dom";

const StyledLink = styled(Link)`
    color: white;
    text-decoration: none;
    &:focus, &:hover, &:visited, &:link, &:active {
        text-decoration: none;
    }
`

const HeaderTag = styled.div`
  <!-- max-width: 1280px; -->
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
  background-color: black;

  .logo {
    margin: 0 1rem;
    font-size: 2rem;
  }

  .header__menulist {
    list-style: none;
    display: flex;
  }

  .header__left {
    display: flex;
  }

  .header__right {
    list-style: none;
    display: flex;
  }

  .header__right div {
    margin: 0 1rem;
  }

  li {
    padding: 0 1rem;
    text-decoration: none;
  }

  .toggle {
    display: none;
    font-size: 1.5rem;
    padding: 1rem 1rem;
  }

  .user {
    display: none;
    font-size: 1.5rem;
    padding: 1rem 1rem;
  }

  @media screen and (min-width: 100px) {
    flex-wrap: wrap;

    .header__right {
      display: ${(props) => (props.userToggled ? "flex" : "none")};
      flex-direction: column;
      width: 100%;
      background-color: black;
    }

    .header__menulist {
      display: ${(props) => (props.isToggled ? "flex" : "none")};
      flex-direction: column;
      width: 100%;
      background-color: black;
    }

    .header__menulist li,
    .header__right li {
      margin: 1rem 0;
      padding: 0;
    }

    .toggle {
      display: block;
    }

    .user {
      display: block;
    }
  }
`;

const HeadDiv = styled.div`
    display: flex;
    align-items: center;
    height: 60px;
    font-size: 20px;
    box-shadow: 2px 2px 2px 1px grey;
    padding-left: 50px;
    font-weight: bold;
    background-color: #3E54AC;
    color: white;
`

const Header = ()=>{
    return (
        <HeadDiv>
            <FontAwesomeIcon
                icon={faSolarPanel}
                style={{
                    marginRight:'5px',
                }}
            />
            Building Energy Management System
        </HeadDiv>
    );
}

export default Header;
