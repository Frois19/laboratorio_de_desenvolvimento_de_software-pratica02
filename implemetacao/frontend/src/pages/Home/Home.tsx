import React, { useState } from "react";
import { useHistory } from "react-router";
import ilustrationImg from "../../assets/illustration.png";
import logoImg from "../../assets/logo.png";
import { useAuth } from "../../hooks/useAuth";
import './styles.scss';

export function Home() {
    const { handleJoin } = useAuth()

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const history = useHistory();

    function handleSubscribe() {
        history.push("/subscribe");
    }

    return (
        <div id="page-auth">
            <aside>
                <img src={ilustrationImg} alt="" />
            </aside>
            <main>
                <div className="main-content">
                    <img src={logoImg} alt="logo" />
                    <form>
                        <input type="text" placeholder="Username..." onChange={event => setUsername(event.target.value)} value={username} />
                        <input type="password" placeholder="Password..." onChange={event => setPassword(event.target.value)} value={password}/>
                        <button type="button" onClick={() => handleJoin(username, password)}>Login</button>
                    </form>
                    <div className="separator">or create an account</div>
                    <button type="button" onClick={handleSubscribe}>Subscribe</button>
                </div>
            </main>
        </div>
    )
}