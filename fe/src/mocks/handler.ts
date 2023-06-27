import { rest } from 'msw';

const menus = [{ name: '커피' }, { name: '라떼' }, { name: '쥬스' }, { name: '티' }, { name: '디카페인' }];

const coffee = [
  { name: '아메리카노', price: '4000', id: '1', img: '/assets/americano.jpeg' },
  { name: '콜드브루', price: '4500', id: '2', img: '/assets/coldbrew.jpeg' },
  { name: '에스프레소', price: '3000', id: '3', img: '/assets/espresso.jpeg' },
  { name: '카페모카', price: '4500', id: '4', img: '/assets/caffe-mocha.jpeg' },
];

const latte = [
  { name: '라떼1', price: '4000', id: '5', img: '/assets/latte.jpeg' },
  { name: '라떼2', price: '4500', id: '6', img: '/assets/latte.jpeg' },
  { name: '라떼3', price: '3000', id: '7', img: '/assets/latte.jpeg' },
  { name: '라떼4', price: '4500', id: '8', img: '/assets/latte.jpeg' },
];

const juice = [
  { name: '쥬스1', price: '4000', id: '9', img: '/assets/juice.jpeg' },
  { name: '쥬스2', price: '4500', id: '10', img: '/assets/juice.jpeg' },
  { name: '쥬스3', price: '3000', id: '11', img: '/assets/juice.jpeg' },
  { name: '쥬스4', price: '4500', id: '12', img: '/assets/juice.jpeg' },
];

const tea = [
  { name: '티1', price: '4000', id: '13', img: '/assets/tea.jpeg' },
  { name: '티2', price: '4500', id: '14', img: '/assets/tea.jpeg' },
  { name: '티3', price: '3000', id: '15', img: '/assets/tea.jpeg' },
  { name: '티4', price: '4500', id: '16', img: '/assets/tea.jpeg' },
];

const decaf = [
  { name: '디카페인1', price: '4000', id: '17', img: '/assets/decaf.jpeg' },
  { name: '디카페인2', price: '4500', id: '18', img: '/assets/decaf.jpeg' },
  { name: '디카페인3', price: '3000', id: '19', img: '/assets/decaf.jpeg' },
  { name: '디카페인4', price: '4500', id: '20', img: '/assets/decaf.jpeg' },
];

const menu1 = {
  name: '아메리카노',
  price: 4000,
  img: '/assets/americano.jpeg',
  option: { size: ['big', 'small'], temperature: ['hot', 'ice'] },
};

const menu2 = {
  name: '콜드브루',
  price: 4500,
  img: '/assets/coldbrew.jpeg',
  option: { size: ['big', 'small'], temperature: ['ice'] },
};

const menu3 = {
  name: '에스프레소',
  price: 3000,
  img: '/assets/espresso.jpeg',
  option: { size: ['small'], temperature: ['hot'] },
};

const menu4 = {
  name: '카페라떼',
  price: 4500,
  img: '/assets/caffe-mocha.jpeg',
  option: { size: ['big', 'small'], temperature: ['hot', 'ice'] },
};

const handlers = [
  rest.get('/api/categories', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(menus));
  }),
  rest.get('api/menus/0', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(coffee));
  }),
  rest.get('api/menus/1', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(latte));
  }),
  rest.get('api/menus/2', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(juice));
  }),
  rest.get('api/menus/3', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(tea));
  }),
  rest.get('api/menus/4', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(decaf));
  }),
  // 1번부터
  rest.get('/api/carts/1', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(menu1));
  }),
  rest.get('/api/carts/2', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(menu2));
  }),
  rest.get('/api/carts/3', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(menu3));
  }),
  rest.get('/api/carts/4', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(menu4));
  }),

  rest.post('/api/payments', (req, res, ctx) => {
    return res(ctx.json({}));
  }),
];

export default handlers;
