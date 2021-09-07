import React, {useEffect, useState} from 'react';
import axios from 'axios';

const ItemPage = () => {
    const [items, setItems] = useState([]);
    useEffect(()=>{
        async function getItems() {
            const gettingItem = await axios.get('http://localhost:8080/items');
            console.log(gettingItem);
        }
        getItems();
    });

    

    return (
        <div>
            <h1>Item!</h1>
        </div>
    );
};

export default ItemPage;