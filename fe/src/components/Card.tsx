import classes from './Card.module.css';
import americano from '../assets/americano.jpeg';

export function Card() {
  return (
    <div className={classes.box}>
      <img className={classes.img} src={americano} alt="americano" />
      <p className={classes.text}>
        <span>아메리카노</span>
        <span>4000</span>
      </p>
    </div>
  );
}
