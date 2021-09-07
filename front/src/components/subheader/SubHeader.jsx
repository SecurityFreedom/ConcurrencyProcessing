import React from 'react';
import { BrowserRouter, Route, Switch, Link } from "react-router-dom";
import "./subheader.scss";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faTruckLoading, faJedi, faShoppingCart } from '@fortawesome/free-solid-svg-icons';


const SubHeader = () => {
    const clickHandler = (e) => {
        e.target.className === 'li' ? console.log(e.target.childNodes[0].href) : console.log(e.target.href);
    }



    return (
        <div className="header1">
            <ul class="menu">
                <li class="menu-item">
                    <a class="tag" href="">Category</a>
                    <ul class="menu-sub">
                        <li><a class="tag2" href="">Category1</a></li>
                        <li><a class="tag2" href="">Category2</a></li>
                        <li><a class="tag2" href="">Category3</a></li>
                    </ul>
                </li>
                <li className="menu-item">
                    <a class="tag" href="">Category</a>
                    <ul className="menu-sub">
                        <li><a class="tag2" href="">Category1</a></li>
                        <li><a class="tag2" href="">Category2</a></li>
                        <li><a class="tag2" href="">Category3</a></li>
                    </ul>
                </li>
                <li className="menu-item">
                    <a class="tag" href="">Category</a>
                    <ul className="menu-sub">
                        <li><a class="tag2" href="">Category1</a></li>
                        <li><a class="tag2" href="">Category2</a></li>
                        <li><a class="tag2" href="">Category3</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    );
}

export default SubHeader;