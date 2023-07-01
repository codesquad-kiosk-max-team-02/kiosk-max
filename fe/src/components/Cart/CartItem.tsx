import { useState, useEffect } from 'react';
import classes from './CartItem.module.css';
import { OrderData } from '../../utils/types';
import { getMenuName } from '../../utils/getMenuName';

export function CartItem({
  orderData,
  setOrderList,
}: {
  orderData: OrderData;
  setOrderList: React.Dispatch<React.SetStateAction<OrderData[]>>;
}) {
  function handleClick() {
    setOrderList((prevOrderList) => {
      return prevOrderList.filter(
        (order) =>
          order.menuId !== orderData.menuId ||
          order.option.size !== orderData.option.size ||
          order.option.temperature !== orderData.option.temperature,
      );
    });
  }

  return (
    <li className={`${classes.item} ${classes.fadeIn}`}>
      <button onClick={handleClick}>X</button>
      <div className="name">{getMenuName(orderData.menuId)}</div>
      <div className="size">{orderData.option.size === 1 ? 'S' : 'L'}</div>
      <div className="temp">{orderData.option.temperature === 1 ? 'HOT' : 'ICE'}</div>
      <div className={classes.count}>
        <span>{orderData.quantity}</span>
      </div>
    </li>
  );
}
