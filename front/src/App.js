import React, {useEffect} from "react";
import './App.css';
import axios from "axios";

function App() {
    const baseUrl = "http://localhost:8080";

    useEffect(()=>{
        getItems();
    }, []);

    async function getItems() {
        await axios
            .get(baseUrl)
            .then((res) => {
                console.log(res.data);
            })
            .catch((err) => {
                console.error(err);
            })
    }


    return (
        <div className="App">
            hello
        </div>
    );
}

export default App;
