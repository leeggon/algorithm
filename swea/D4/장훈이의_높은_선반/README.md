## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw&categoryId=AV2b7Yf6ABcBBASw&categoryType=CODE&problemTitle=%EC%9E%A5%ED%9B%88%EC%9D%B4&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

## 유형

완전탐색. 백트래킹.

## 풀이법

N이 20이라서 사람마다의 키를 각각 더하며 완전탐색.

B와의 차이가 result보다 클 경우, 탐색을 그만하도록 백트래킹 해줌.

result를 Integer.MAX_VALUE로 설정해서 대소비교할 때 overflow가 안 나게 주의.

## 메모리 / 수행시간 / 코드길이

37,524KB / 196ms / 1,200B
