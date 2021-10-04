import React, { createContext, ReactNode, useState } from "react";
import { useHistory } from "react-router";

type AuthContextType = {
    usernameAuth: string;
    handleJoin: (username: string, password: string) => void;
}


type AuthContextProviderProps = {
    children: ReactNode;
}

export const AuthContext = createContext({} as AuthContextType);

export function AuthContextProvider(props: AuthContextProviderProps){
    const [usernameAuth, setusernameAuth] = useState('');

    const history = useHistory();


    async function handleJoin(username: string, password: string) {

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
            if(resp.status == 202){
                setusernameAuth(username);
                handleCars();
            }
        });
    }

    function handleCars() {
        history.push("/cars");
    }




    return(
        <AuthContext.Provider value={{  usernameAuth, handleJoin  }}>
            {props.children}
        </AuthContext.Provider>
    );
}