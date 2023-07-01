export function getMenuName(menuId: number) {
  switch (menuId) {
    case 1:
      return '아메리카노';
    case 2:
      return '콜드브루';
    case 3:
      return '에스프레소';
    case 4:
      return '카페라떼';
    case 5:
      return '카푸치노';
    case 6:
      return '카라멜 마키아또';
    case 7:
      return '바닐라 라떼';
    case 8:
      return '카페 모카';
    case 9:
      return '디카페인 아메리카노';
    case 10:
      return '디카페인 돌체 라떼';
    case 11:
      return '디카페인 카페 라떼';
    case 12:
      return '자몽 허니 블랙 티';
    case 13:
      return '얼 그레이 티';
    case 14:
      return '말차 라떼';
    case 15:
      return '차이 티 라떼';
    case 16:
      return '돌체 블랙 밀크 티 ';
    case 17:
      return '플럼 선셋 유스베리 티';
    case 18:
      return '딸기주스';
    case 19:
      return '망고주스';
    case 20:
      return '오렌지주스';
    case 21:
      return '사과주스';
    default:
      return '메뉴 이름';
  }
}
