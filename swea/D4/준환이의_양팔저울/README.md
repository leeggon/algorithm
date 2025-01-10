## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWAe7XSKfUUDFAUw&categoryId=AWAe7XSKfUUDFAUw&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=10&pageIndex=2

## 유형

순열, 백트래킹

## 풀이법

N이 최대 9이다. 원래의 시간복잡도는 O(N! * 2^N)로 대략 10^9 이상이 측정되어서 백트래킹을 진행해주었다.

순열로 뽑는 순서 먼저 구하지 않고, DFS 하면서 백트래킹을 같은 조건으로 하였는데 시간 초과의 늪에서 승리하지 못했다.

따라서 순열을 먼저뽑고, 뽑힌 순열에서 left < right인 경우에 백트래킹하는 풀이법을 적용했다.

## 메모리 / 수행시간 / 코드길이

28,800 KB / 1,101 ms / 1,640 B
