import classes from './Cart.module.css';
import { Button } from './Button';

export function Cart() {
  return (
    <div className={classes.cart}>
      <div className={classes.left}>
        <ul className={classes.itemList}>
          <li className={classes.item}>order1</li>
          <li className={classes.item}>order2</li>
          <li className={classes.item}>order3</li>
          <li className={classes.item}>order4</li>
          <li className={classes.item}>order5</li>
        </ul>
      </div>
      <div className={classes.right}>
        <div className={classes.timer}>
          <span>120초 후 주문이 취소됩니다.</span>
        </div>
        <div className={classes.info}>
          <div className={classes.left}>
            <div className={classes.total}>총합: 0원</div>
            <button className={classes.cancelBtn}>전체취소</button>
          </div>
          <button className={classes.payBtn}>결제하기</button>
        </div>
      </div>
    </div>
  );
}
