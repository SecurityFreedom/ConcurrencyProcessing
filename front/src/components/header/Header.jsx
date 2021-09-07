import React from 'react';
import { BrowserRouter, Route, Switch, Link } from "react-router-dom";
import "./header.scss";

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faTruckLoading, faJedi, faShoppingCart } from '@fortawesome/free-solid-svg-icons';


const Header = () => {
    const clickHandler = (e) => {
        e.target.className === 'li' ? console.log(e.target.childNodes[0].href) : console.log(e.target.href);
    }



    return (
        <div className="header">
            <span className="logoName">
                <h2 className="header__title">Project Concurrency</h2>
            </span>

            <span className="menus" onClick={clickHandler}>
                <li className="li"><Link className="menus__item" to="/">메인</Link></li>
                <li className="li"><Link className="menus__item" to="/login">로그인</Link></li>
                <li className="li"><Link className="menus__item" to="/register">회원가입</Link></li>
                <li className="li"><Link className="menus__item" to="/cart">장바구니</Link></li>
                <li className="li"><Link className="menus__item" to="/orderlist">주문조회</Link></li>
                <li className="li"><Link className="menus__item" to="/items">상품목록</Link></li>
                <li className="li"><Link className="menus__item" to="/mypage">마이페이지</Link></li>
            </span>
        </div>

    );
}

export default Header;