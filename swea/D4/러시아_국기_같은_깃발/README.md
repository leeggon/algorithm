## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWQl9TIK8qoDFAXj&categoryId=AWQl9TIK8qoDFAXj&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=30&pageIndex=1

## 유형

완전탐색, 조합

## 풀이법

N이 50이기 때문에, 완전 탐색이 가능할 것이라고 판단했다.

W, B, R을 어디까지 칠할지 우선 50C2로 경우의 수를 찾는다.

그 다음 각 조합마다 색이 일치하지 않는 노드의 수를 세어, 색칠해야 하는 노드 개수의 최소값을 구했다.

시간 복잡도 대략 O(10^6)의 풀이가 가능했다.

## 메모리 / 수행시간 / 코드길이

27,840 KB / 137 ms / 1,839 B
