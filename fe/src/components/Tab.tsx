import classes from './Tab.module.css';

export function Tab({
  id,
  tab,
  activeIdx,
  onClick,
}: {
  key: number;
  id: number;
  tab: string;
  activeIdx: number;
  onClick: () => void;
}) {
  if (activeIdx === id) {
    return (
      <button className={`${classes.tab} ${classes.active}`} onClick={onClick}>
        {tab}
      </button>
    );
  }
  return (
    <button className={classes.tab} onClick={onClick}>
      {tab}
    </button>
  );
}
