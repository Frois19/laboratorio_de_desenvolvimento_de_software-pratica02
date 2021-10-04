import React, { useState } from "react";
import { useHistory } from "react-router";
import "../../styles/form.scss"
import carImg from "../../assets/car.png";

export function Cars() {
    const [year, setYear] = useState('');
    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');
    const [plate, setPlate] = useState('');

    const history = useHistory();

    async function handleCars() {
        const obj = {
            year: year,
            brand: brand,
            model: model,
            plate: plate
        };
        const url = 'http://localhost:8080/cars';
        await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            body: JSON.stringify(obj),
        }).then((resp) => {
            console.log(resp.status);
            handleRequest();
        });

        function handleRequest() {
            history.push("/request");
        }
    }


    return (
        <main>
            <div className="main-content">
                <img src={carImg} alt="" />
                <form>
                    <input type="text" placeholder="Year..." onChange={event => setYear(event.target.value)} value={year} />
                    <input type="text" placeholder="Brand..." onChange={event => setBrand(event.target.value)} value={brand} />
                    <input type="text" placeholder="Model..." onChange={event => setModel(event.target.value)} value={model} />
                    <input type="text" placeholder="Plate..." onChange={event => setPlate(event.target.value)} value={plate} />
                    <button type="button" onClick={handleCars}>Cadastrar</button>
                </form>
            </div>
        </main>
    )
}