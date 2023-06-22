export interface Menu {
  category: string;
  products: Product[];
}

interface Product {
  imgURL: string;
  name: string;
  price: number;
}
