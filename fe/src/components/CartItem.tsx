import classes from './CartItem.module.css';

export function CartItem() {
  return (
    <li className={classes.item}>
      <button>X</button>
      <div className="name">아메리카노</div>
      <div className="size">L</div>
      <div className="temp">ICE</div>
      <div className="price">4000</div>
      <div className="count">2</div>
    </li>
  );
}
