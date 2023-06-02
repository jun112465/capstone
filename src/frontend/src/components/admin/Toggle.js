import React, {useEffect, useRef, useState} from "react";
import styled from "styled-components";

const ToggleTag = styled.div`
.form-switch {
  display: inline-block;
  cursor: pointer;
  -webkit-tap-highlight-color: transparent;
}
.form-switch i {
  position: relative;
  display: inline-block;
  margin-right: .5rem;
  width: 46px;
  height: 26px;
  background-color: #e6e6e6;
  border-radius: 23px;
  vertical-align: text-bottom;
  transition: all 0.3s linear;
}
.form-switch i::before {
  content: "";
  position: absolute;
  left: 0;
  width: 42px;
  height: 22px;
  background-color: red;
  border-radius: 11px;
  transform: translate3d(2px, 2px, 0) scale3d(1, 1, 1);
  transition: all 0.25s linear;
}
.form-switch i::after {
  content: "";
  position: absolute;
  left: 0;
  width: 22px;
  height: 22px;
  background-color: #fff;
  border-radius: 11px;
  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.24);
  transform: translate3d(2px, 2px, 0);
  transition: all 0.2s ease-in-out;
}
.form-switch:active i::after {
  width: 28px;
  transform: translate3d(2px, 2px, 0);
}
.form-switch:active input:checked + i::after { transform: translate3d(16px, 2px, 0); }
.form-switch input { display: none; }
.form-switch input:checked + i { background-color: #4BD763; }
.form-switch input:checked + i::before { transform: translate3d(18px, 2px, 0) scale3d(0, 0, 0); }
.form-switch input:checked + i::after { transform: translate3d(22px, 2px, 0); }
`


function Toggle(props) {
    const [isChecked, setIsChecked] = useState(null);

    let handleCheckboxChange = (e)=>{
        fetch(process.env.REACT_APP_ORIGIN+'/relay-control', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                floor: props.relayId,
                before: isChecked,
                after: !isChecked
            })
        })
            .then(resp => resp.json())
            .then(data => setIsChecked(data[0]))
    }

    useEffect(()=> {
        fetch(process.env.REACT_APP_ORIGIN+'/relay?relay-id=' + props.relayId)
            .then(response => response.json())
            .then(data => setIsChecked(data[0]));
    }, []);

    // console.log(props.relayId, props.status)

    return (
        <ToggleTag>
            <label className="form-switch">
                <input type="checkbox" checked={isChecked} onChange={handleCheckboxChange}/>
                <i></i>
            </label>
        </ToggleTag>
    );
}

export default Toggle;
