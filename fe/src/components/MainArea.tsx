import { Menu } from '../utils/types';
import { Card } from './Card';

import classes from './MainArea.module.css';

export function MainArea({
  menuList,
  activeTab,
  setActiveTab,
}: {
  menuList: Menu[];
  activeTab: number;
  setActiveTab: (idx: number) => void;
}) {
  return (
    <main className={classes.main}>
      {menuList[activeTab].products.map((product, index) => (
        <Card key={index} name={product.name} price={product.price} img={product.imgURL} />
      ))}
    </main>
  );
}
