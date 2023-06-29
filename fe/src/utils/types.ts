export interface Menu {
  name: string;
}

export interface Product {
  name: string;
  price: number;
  menuId: number;
  img: string;
  isPopular: boolean;
}

export type ModaInfo = {
  name: string;
  price: number;
  img: string;
  option: Option[];
};

type Option = {
  optionCategoryType: string;
  optionId: number;
  optionName: string;
  optionPrice: number;
};

export type OrderData = {
  menuId: number;
  option: { size: number; temperature: number };
  quantity: number;
  price: number;
};
