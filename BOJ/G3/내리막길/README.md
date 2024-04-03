## 문제 링크

https://www.acmicpc.net/problem/1520

## 유형

dfs + dp

## 풀이법

BFS : 메모리 초과, DFS : 시간 초과

결국 DFS 과정에서 memoization이 필요했다. 겹치는 경로에 대한 중복 연산을 줄이기 위해

dp[i][j] : (i,j)부터 (M-1,N-1)까지의 경로 개수

dp table이 업데이트 되어있지 않다면, 4방 중 내리막길인 경로 수를 dfs로 구하고 더해서 dp table을 채워줬다.

dp table이 업데이트 되어있다면, table값 return

## 메모리 / 수행시간 / 코드길이

42,664KB / 432ms / 2,020B
