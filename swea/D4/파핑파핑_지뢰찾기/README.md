## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV5LwsHaD1MDFAXc&categoryId=AV5LwsHaD1MDFAXc&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=ALL&select-1=4&pageSize=10&pageIndex=3

## 유형

flood fill 알고리즘(BFS)

## 풀이법

1. 근처 지뢰 개수를 센다. 해당 자리가 지뢰라면 -1로 표시
2. 0을 기준으로 flood fill 알고리즘 사용. BFS 한 자리가 0이라면 다시 큐에 넣고 계속 영역 확장
3. 지뢰 개수 > 0 && !visited 인 자리를 카운팅해서 더해준다.

## 메모리 / 수행시간 / 코드길이

33,716 KB / 337 ms / 3,304 B
