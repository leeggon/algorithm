## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu

## 유형

중복순열, dfs 완전탐색, 백트래킹

## 풀이법

중복순열 결과 배열 permResult에 행마다 0,1,2를 넣어줌(0:색칠 안함 1:A로 색칠 2:B로 색칠)

dfs 깊이 D에 도달했을 시, permResult에 적힌 값대로 tempMap을 색칠함.

tempMap이 문제 조건에 맞을 시에 result를 업데이트함.

백트래킹: dfs마다 색칠한 약의 개수를 세서 업데이트된 result값보다 작을 시에만 다음 dfs 진행.

## 메모리 / 수행시간 / 코드길이

120,480KB / 2,783ms / 3,271B
