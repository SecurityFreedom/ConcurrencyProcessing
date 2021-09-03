import React, {useState} from 'react';
import "./loginpage.scss";
const LoginPage = () => {


    return (
        <div className="login">
                <h2>Log-in</h2>
                <div className="login_sns">
                    <li><a href=""><i className="fab fa-instagram"></i></a></li>
                    <li><a href=""><i className="fab fa-facebook-f"></i></a></li>
                    <li><a href=""><i className="fab fa-twitter"></i></a></li>
                </div>
            <div className="login_id">
                <h4>ID</h4>
                <input type="id" name="" id="" placeholder="id"/>
            </div>
            <div className="login_pw">
                <h4>Password</h4>
                <input type="password" name="" id="" placeholder="Password"/>
            </div>
            <div className="login_etc">
                <div className="checkbox">
                    <input type="checkbox" name="" id=""/> Remember Me?
                </div>
                <div className="forgot_pw">
                    <a href="">Forgot Password?</a>
                </div>
            </div>
            <div className="submit">
                <input type="submit" value="submit"/>
            </div>
        </div>
    );
};

export default LoginPage;