## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWrDOdQqRCUDFARG&categoryId=AWrDOdQqRCUDFARG&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=30&pageIndex=1

## 유형

BFS, Flood fill

## 풀이법

N이 최대 100이기 때문에 모든 경우의 수를 탐색하는 풀이가 가능했다.

N^2에 대해서 100일의 케이스를 탐색하므로 O(N^6)의 풀이를 진행했다.

Flood Fill 알고리즘으로 현재 날짜보다 값이 큰 노드들만 연결해서 덩어리로 묶고, 총 덩어리 개수의 최대값을 구했다.

100일 모두 탐색하지 않으면 더 시간을 줄일 수 있을 것 같다. 최대 맛 값을 구해서 그 까지만 계산한다면.

## 메모리 / 수행시간 / 코드길이

98,636 KB / 407 ms / 2,330 B
