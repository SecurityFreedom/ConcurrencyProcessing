import React, {useState} from 'react';
import "./loginpage.scss";
import axios from "axios";
import { Redirect } from "react-router-dom";
const LoginPage = (userPK) => {
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");
    const [LoginSuccess, setLoginSuccess] = useState(false);
    const onSubmit = e => {
        console.log(password);
        e.preventDefault();
        axios.post("http://localhost:8080/login",{
            id:id,
            password:password,
        })
            .then(function(response){
                console.log(response);
                if(response.data=="ok") {
                    setLoginSuccess(true);
                    alert("환영합니다");
                }
                else{
                    alert("아이디 또는 비밀번호를 확인하세요")
                }
            })
            .catch(function(error){
                console.log("fail");
                alert("잠시 후 다시 시도하세요");
            });

    };
    const onChangeId = e => {
        setId(e.target.value);
        console.log(e.target.value);
    };

    const onChangePassword = e => {
        setPassword(e.target.value);
    };
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
                <input type="id" name="" id="" value={id} onChange={onChangeId} placeholder="id"/>
            </div>
            <div className="login_pw">
                <h4>Password</h4>
                <input type="password" name="" id="" value={password} onChange={onChangePassword} placeholder="Password"/>
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
                <button onClick={onSubmit}>Login</button>
                {LoginSuccess && <Redirect to={"/home"}/>}
            </div>
        </div>
    );
};

export default LoginPage;