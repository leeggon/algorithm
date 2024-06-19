## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWzaq5KKk_ADFAVU&categoryId=AWzaq5KKk_ADFAVU&categoryType=CODE&problemTitle=8458&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

## 유형

구현

## 풀이법

입력 크기가 10^9이기 때문에 완전탐색 등의 방법은 불가능하다고 판단.

원점으로 집합시켰을 때 0,1 두가지의 상태를 가지게 된다.

이에 따라 초기 거리가 짝수,홀수인 경우를 나눠서 생각하는 것이 쉽지 않았다.

짝수,홀수가 공존하게 되면 모두 원점으로 집합이 불가능한 상태이므로 -1을 출력함.

원점으로 집합이 가능하다면, 모든 점들이 원점으로 집합되는 시간을 계산.

## 메모리 / 수행시간 / 코드길이

35,172KB / 472ms / 1,632B
