## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV141176AIwCFAYD&categoryId=AV141176AIwCFAYD&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=30&pageIndex=1

## 유형

구현

## 풀이법

트리가 나와서 조금 생소하다고 생각했지만, 리프 노드인지만 판단하면 쉽게 풀이할 수 있었다.

리프 노드인지는 StringTokenizer의 hasMoreTokens() 메소드로 판별했다.

1. 리프 노드라면, 숫자말고 연산자나 다른 문자가 온다면 answer = 0으로 변경
2. 리프 노드가 아니라면, 숫자의 경우 answer = 0으로 변경

## 메모리 / 수행시간 / 코드길이

26,752 KB / 96 ms / 1,111 B
