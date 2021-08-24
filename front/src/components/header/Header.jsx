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
                <FontAwesomeIcon className="header__logo" icon={faJedi} spin={true}></FontAwesomeIcon>
                <h2 className="header__title">Concurrency</h2>
            </span>

            <span className="menus" onClick={clickHandler}>
                <li className="li"><Link className="menus__item" to="/">메인</Link></li>
                <li className="li"><Link className="menus__item" to="/items">상품목록</Link></li>
                <li className="li"><Link className="menus__item" to="/mypage">마이페이지</Link></li>
                <li className="li">
                    <Link className="menus__item" to="/mypage">
                        <FontAwesomeIcon className="header__logo" icon={faShoppingCart}></FontAwesomeIcon>
                        <span className="cart__item_num">0</span>
                    </Link>
                </li>

            </span>
        </div>
    );
}

export default Header;