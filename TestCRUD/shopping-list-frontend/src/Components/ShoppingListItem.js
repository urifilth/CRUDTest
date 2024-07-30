import React from 'react';

const ShoppingListItem = ({ item }) => {
  return (
    <li className="shopping-list-item">
      {item.name}
    </li>
  );
};

export default ShoppingListItem;
