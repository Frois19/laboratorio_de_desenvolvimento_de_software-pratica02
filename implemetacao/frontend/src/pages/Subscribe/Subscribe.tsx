import React, { useState } from "react";
import { useHistory } from "react-router";
import logoImg from "../../assets/logo.png";
import "../../styles/form.scss"

export function Subscribe() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const history = useHistory();

    async function handleLogin() {
        const obj = {
            login: username,
            password: password
        };
        const url = 'http://localhost:8080/users/subscribe';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            handleHome();
        });
    }

    function handleHome(){
        history.push("/");
    }


    return (
        <main>
            <div className="main-content">
                <img src={logoImg} alt="logo" />
                <form>
                    <input type="text" placeholder="Username..." onChange={event => setUsername(event.target.value)} value={username} />
                    <input type="password" placeholder="Password..." onChange={event => setPassword(event.target.value)} value={password} />
                    <button type="button" onClick={handleLogin}>Cadastrar</button>
                </form>
            </div>
        </main>
    )
}