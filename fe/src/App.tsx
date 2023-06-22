import classes from './App.module.css';
import { TabMenu } from './components/TabMenu';
import { MainArea } from './components/MainArea';

function App() {
  return (
    <div className={classes.kiosk}>
      <TabMenu />
      <MainArea />
    </div>
  );
}

export default App;
