import React from 'react';
import { BrowserRouter, Route, Switch, Link } from "react-router-dom";
import "./header.scss";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faTruckLoading, faJedi } from '@fortawesome/free-solid-svg-icons';


const Header = () => {




    return (
        <div className="header">
            <span className="logoName">
                <FontAwesomeIcon className="header__logo" icon={faJedi} spin={true}></FontAwesomeIcon>
                <h2 className="header__title">Concurrency</h2>
            </span>

            <span className="menus">
                <li className="li"><Link className="menus__item" to="/">메인</Link></li>
                <li className="li"><Link className="menus__item" to="/items">상품목록</Link></li>
                <li className="li"><Link className="menus__item" to="/mypage">마이페이지</Link></li>
            </span>
        </div>
    );
}

export default Header;