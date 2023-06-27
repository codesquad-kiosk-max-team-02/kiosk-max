import { useState, useEffect } from 'react';

import classes from './App.module.css';
import { MainArea } from './components/Main/MainArea';
import { Cart } from './components/Cart/Cart';
import { TabMenu } from './components/Tab/TabMenu';

function App() {
  const [activeTab, setActiveTab] = useState(0);
  const [menuList, setMenuList] = useState([]);
  const [productList, setProductList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [orderList, setOrderList] = useState([]);

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
      <TabMenu menuList={menuList} activeTab={activeTab} setActiveTab={setActiveTab} />
      <MainArea productList={productList} setOrderList={setOrderList} />
      <Cart orderList={orderList} />
    </div>
  );
}

export default App;
