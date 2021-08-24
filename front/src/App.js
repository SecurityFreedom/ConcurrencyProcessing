import React, {useEffect} from "react";
import './App.css';
import { BrowserRouter, Route, Switch, Link } from "react-router-dom";
import axios from "axios";
import Header from './components/header/Header.jsx';
import MainPage from "./components/mainpage/MainPage";
import ItemPage from "./components/itempage/ItemPage";
import MyPage from "./components/mypage/MyPage";

function App() {
    const baseUrl = "http://localhost:8080";

    // useEffect(()=>{
    //     getItems();
    // }, []);

    // async function getItems() {
    //     await axios
    //         .get(baseUrl)
    //         .then((res) => {
    //             console.log(res.data);
    //         })
    //         .catch((err) => {
    //             console.error(err);
    //         })
    // }


    return (
        <div className="container">
            <BrowserRouter>
                <Header className="header"/>
                <section className="contents">
                    <Switch>
                        <Route path="/" exact={true}>
                            <MainPage/>
                        </Route>
                        <Route path="/items">
                            <ItemPage/>
                        </Route>
                        <Route path="/mypage">
                            <MyPage/>
                        </Route>
                    </Switch>
                </section>
            </BrowserRouter>
        </div>
    );
}

export default App;
