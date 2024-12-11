## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/12946

## 유형

재귀

## 풀이법

하노이의 탑 알고리즘을 오랜만에 풀어보니, 헷갈리는 부분이 있었다.

하노이 함수 (몇개 이동, 출발지, 경유지, 목적지) 형식으로 함수를 작성하였고, 1개 원반을 옮기는 경우에 대해 result에 추가해줬다. (재귀 탈출 조건)

hanoi(x, s, m, a) = hanoi(x-1, s, a, m) , 큰 원판 1개 옮기기, hanoi(x-1, m, s, a)

## 메모리 / 수행시간 / 코드길이