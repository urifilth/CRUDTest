import React, { useEffect, useState } from 'react';
import api from '../Services/api';
import ShoppingListItem from './ShoppingListItem';

const ShoppingList = () => {
  const [items, setItems] = useState([]);
  const [newItem, setNewItem] = useState('');

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    try {
      const response = await api.get('/shopping-lists');
      setItems(response.data);
    } catch (error) {
      console.error('Error consultando:', error);
    }
  };

  const addItem = async (event) => {
    event.preventDefault();
    try {
      const response = await api.post('/shopping-lists', { name: newItem });
      setItems([...items, response.data]);
      setNewItem('');
    } catch (error) {
      console.error('Error añadiendo:', error);
    }
  };

  const deleteItem = (id) => {
    setItems(items.filter(item => item.id !== id));
  };

  return (
    <div>
      <h1>¡Lista de compras!</h1>
      <form onSubmit={addItem}>
        <input
          type="text"
          value={newItem}
          onChange={(e) => setNewItem(e.target.value)}
          placeholder="Añadir nuevo producto"
        />
        <button type="submit">Añadir</button>
      </form>
      <ul>
        {items.map((item) => (
          <ShoppingListItem key={item.id} item={item} onDelete={deleteItem} />
        ))}
      </ul>
    </div>
  );
};

export default ShoppingList;
