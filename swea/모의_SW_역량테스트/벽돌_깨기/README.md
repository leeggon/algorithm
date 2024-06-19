## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo&categoryId=AWXRQm6qfL0DFAUo&categoryType=CODE&problemTitle=5656&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

## 유형

중복순열, BFS

## 풀이법

입력 1<=N<=4, 2<=W<=12, 2<=H<=15이므로 중복순열로 완탐이 가능하다고 판단.

1. 구슬을 N번 떨어트리는 열의 중복순열을 구함. 각 case마다
2. 벽돌이 깨질 수 있다면 bfs로 깰 수 있는 만큼 벽돌을 깸.
3. 벽돌을 깨고 벽돌들을 내려주고 최종 남아있는 벽돌의 수를 구함.

## 메모리 / 수행시간 / 코드길이

116,196KB / 2,046ms / 4,683B
