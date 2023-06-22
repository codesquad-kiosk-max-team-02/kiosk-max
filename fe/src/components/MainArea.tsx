import { Card } from './Card';

import classes from './MainArea.module.css';

export function MainArea() {
  return (
    <main className={classes.main}>
      <Card />
      <Card />
      <Card />
      <Card />
    </main>
  );
}
