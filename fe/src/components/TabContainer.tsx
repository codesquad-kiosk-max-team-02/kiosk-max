import React, { useRef, useState } from 'react';
import { Tab } from './Tab';
import classes from './TabContainer.module.css';

export function TabContainer({ tabs }: { tabs: string[] }) {
  const containerRef = useRef<HTMLDivElement>(null);
  const [startX, setStartX] = useState(0);
  const [isDragging, setIsDragging] = useState(false);
  const [activeTabIdx, setActiveTabIdx] = useState(0);

  const handleClick = (index: number) => {
    setActiveTabIdx(index);
  };

  const handleMouseDown: React.MouseEventHandler = (event) => {
    setStartX(event.pageX - (containerRef.current?.offsetLeft ?? 0));
    setIsDragging(true);
  };

  const handleMouseMove: React.MouseEventHandler = (event) => {
    if (!isDragging) return;
    const x = event.pageX - (containerRef.current?.offsetLeft ?? 0);
    const walk = x - startX;
    const scrollLeft = containerRef.current?.scrollLeft;

    if (containerRef.current) {
      containerRef.current.scrollLeft = scrollLeft! - walk;
      setStartX(x);
    }
  };

  const handleMouseUp = () => {
    setIsDragging(false);
    setStartX(0);
  };

  const handleMouseLeave = () => {
    setIsDragging(false);
    setStartX(0);
  };

  return (
    <div
      className={classes.container}
      ref={containerRef}
      onMouseDown={handleMouseDown}
      onMouseMove={handleMouseMove}
      onMouseUp={handleMouseUp}
      onMouseLeave={handleMouseLeave}
    >
      {tabs.map((tab, index) => (
        <Tab key={index} id={index} tab={tab} activeIdx={activeTabIdx} onClick={() => handleClick(index)} />
      ))}
    </div>
  );
}
