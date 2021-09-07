import React, {useState} from 'react';
import "./loginpage.scss";
import axios from "axios";
const LoginPage = () => {
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");
    const onSubmit = e => {
        console.log(password);
        e.preventDefault();
        axios.post("http://localhost:8080/login",{
            id:id,
            password:password,
        })
            .then(function(response){
                if(response.data=="ok") {
                    alert("success");
                }
            })
            .catch(function(error){
                console.log("fail");
                alert("fail");
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
            </div>
        </div>
    );
};

export default LoginPage;