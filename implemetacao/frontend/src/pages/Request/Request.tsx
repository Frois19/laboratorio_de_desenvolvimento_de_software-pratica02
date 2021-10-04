import React, { useEffect, useState } from "react";
import { useHistory } from "react-router";
import { useAuth } from "../../hooks/useAuth";
import { api } from "../../services/api";
import "../../styles/list.scss"

type Cars = {
    id: number,
    brand: string;
    model: string;
    plate: string;
    year: string;
}


export function Request() {
    const { usernameAuth } = useAuth();

    const history = useHistory();
    const [datas, setData] = useState<Cars[]>([]);
    const [id, setId] = useState<number>();
    const [username, setUsername] = useState('');

    const listas = [...datas]

    useEffect(() => {
        handleCars();
    }, [])

    async function handleCars() {
        const { data } = await api.get('cars');
        console.log(data);
        setData(data);
    }

    async function handleRegister(id: number){
        setId(id);
        setUsername(usernameAuth)
        const obj = {
            client_id: username,
            cars_id: id
        };
        const url = 'http://localhost:8080/request';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            handlePedidos();
        });
    }

    function handlePedidos(){
        history.push("/listrequest");
    }

    return (
        <div className="main-content">
            <h1>Cars</h1>
            <table className="customers">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Brand</td>
                        <td>Model</td>
                        <td>Plate</td>
                        <td>Year</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <tbody>
                    {
                        listas.map(lista => {
                            return (
                                <tr key={lista.id}>
                                    <td>{lista.id}</td>
                                    <td>{lista.brand}</td>
                                    <td>{lista.model}</td>
                                    <td>{lista.plate}</td>
                                    <td>{lista.year}</td>
                                    <td><button onClick={() => handleRegister(lista.id)}>Fazer Pedido</button></td>
                                </tr>
                            );
                        })
                        }
                </tbody>
            </table>

        </div>
    )
}