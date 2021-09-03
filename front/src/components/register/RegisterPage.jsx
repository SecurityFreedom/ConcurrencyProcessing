import React, {useState} from 'react';
import "./registerpage.scss";
import axios from 'axios';

const RegisterPage = () => {
    const [name, setName] = useState("");
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");
    const [passwordCheck, setPasswordCheck] = useState("");
    const [email, setEmail] = useState("");
    const [passwordError, setPasswordError] = useState(false);
    const onSubmit = e => {
        e.preventDefault();
        if (password !== passwordCheck) {
            console.log("패스워드/패스워드 체크 불일치");
            return setPasswordError(true);
        }
        axios.post("localhost:8080/register",{
            name:name,
            id:id,
            password:password,
            email:email,
        })
            .then(function(response){
                console.log("success");
            })
            .catch(function(error){
                console.log("fail");
            });

    };
    const onChangeName = e => {
        setName(e.target.value);
        console.log(e.target.value);
    };
    const onChangeId = e => {
        setId(e.target.value);
        console.log(e.target.value);
    };

    const onChangePassword = e => {
        setPassword(e.target.value);
    };

    const onChangePasswordCheck = e => {
        setPasswordError(e.target.value !== password);
        setPasswordCheck(e.target.value);
    };
    const onChangeEmail = e => {
        setEmail(e.target.value);
    };
    return (
        <div className="register">
                <h2>Register</h2>
                <div className="login_sns">
                    <li><a href=""><i className="fab fa-instagram"></i></a></li>
                    <li><a href=""><i className="fab fa-facebook-f"></i></a></li>
                    <li><a href=""><i className="fab fa-twitter"></i></a></li>
                </div>
            <div className="reg_name">
                <h4>Name</h4>
                <input type="name" name="" id="" value={name} onChange={onChangeName} placeholder="Name"/>
            </div>
            <div className="reg_id">
                <h4>ID</h4>
                <input type="id" name="" id="" value={id} onChange={onChangeId} placeholder="id"/>
            </div>
            <div className="reg_pw">
                <h4>Password</h4>
                <input type="password" name="" id="" value={password} onChange={onChangePassword} placeholder="Password"/>
            </div>
            <div className="reg_pw">
                <h4>Password Check</h4>
                <input type="password" name="" id="" value={passwordCheck} onChange={onChangePasswordCheck} placeholder="Password"/>
            </div>
            <div className="reg_email">
                <h4>E-Mail</h4>
                <input type="email" name="" id="" value={email} onChange={onChangeEmail} placeholder="E-Mail"/>
            </div>
            <div className="submit">
                <button onClick={onSubmit}>Register</button>
            </div>
        </div>
    );
};

export default RegisterPage;