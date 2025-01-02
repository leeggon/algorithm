## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/77486

## 유형

해시, 구현

## 풀이법

각 사람들의 부모님을 저장하는 HashMap과 각 사람들의 인덱스를 저장하는 index HashMap을 작성하였다.

seller 배열이 10^5이다. 또한 enroll이 10^4이고, 조직이 최악의 경우 일렬로 구성되어 있는 경우 해당 풀이는 O(10^9)의 시간 복잡도를 가질 수 있다.

그래서 if ((int) Math.floor(money * 0.1) == 0) break; 이 코드 한 줄이 없을 때 시간초과가 났다.

하지만 이 코드로 money를 10% 취하고 절사했을 때 0이 되어 더 이상 연산이 필요하지 않을 때 바로 break해줌으로써 시간초과를 해결할 수 있었다.

## 메모리 / 수행시간 / 코드길이
