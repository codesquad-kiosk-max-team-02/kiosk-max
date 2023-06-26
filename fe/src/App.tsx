import { useState, useEffect } from 'react';

import classes from './App.module.css';
import { MainArea } from './components/MainArea';
import { Cart } from './components/Cart';
import { TabContainer } from './components/TabContainer';

function App() {
  const [activeTab, setActiveTab] = useState(0);
  const [menuList, setMenuList] = useState([]);
  const [productList, setProductList] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch('/api/menus')
      .then((res) => res.json())
      .then((data) => {
        setMenuList(data);
        setLoading(false);
      });
  }, []);

  useEffect(() => {
    setLoading(true);
    fetch(`api/products/${activeTab}`)
      .then((res) => res.json())
      .then((data) => {
        setProductList(data);
        setLoading(false);
      });
  }, [activeTab]);

  return (
    <div className={classes.kiosk}>
      <TabContainer menuList={menuList} activeTab={activeTab} setActiveTab={setActiveTab} />
      <MainArea productList={productList} activeTab={activeTab} setActiveTab={setActiveTab} />
      <Cart />
    </div>
  );
}

export default App;
