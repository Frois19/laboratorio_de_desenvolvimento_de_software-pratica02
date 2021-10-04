import React, { useEffect, useState } from "react";
import { api } from "../../services/api";
import "../../styles/list.scss"

export function ListRequest(){

    type Request = {
        id: number,
        login: string;
        cars_id: number;
    }
    

    const [datas, setData] = useState<Request[]>([]);

    const listas = [...datas]

    useEffect(() => {
        handleRequest();
    }, [])

    async function handleRequest() {
        const { data } = await api.get('request');
        console.log(data);
        setData(data);
    }

    async function handledelete(id: number) {
        const { data } = await api.delete(`request/${id}`);
        alert("Please release page")
    }

    async function handlechange(id: number){
        const obj = {
            cars_id: id,
        };
        const url = `http://localhost:8080/users/client/${id}`;
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
        });
    }



    return(
        <div className="main-content">
            <h1>Request</h1>
            <table className="customers">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Login</td>
                        <td>Cars Id</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <tbody>
                    {
                        listas.map(lista => {
                            return (
                                <tr key={lista.id}>
                                    <td>{lista.id}</td>
                                    <td>{lista.login}</td>
                                    <td>{lista.cars_id}</td>
                                    <td>
                                        <button type= "button" onClick={() => handledelete(lista.id)}>Deletar</button>
                                    </td>
                                </tr>
                            );
                        })
                        }
                </tbody>
            </table>

        </div>
    );
}