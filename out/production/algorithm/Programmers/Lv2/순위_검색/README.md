## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/72412

## 유형

구현, map, 이분탐색

## 풀이법

map으로 구현하더라도, map 1개에 최대 50,000개 점수가 들어갈 수 있으므로

query 1개(query는 100,000개)마다 50,000개 탐색 불가 → 이분 탐색 사용해서 최대 50,000개 한 번에 정렬해놓고, logN으로 찾음.

= 이까지 생각했지만, map을 잘못 만들어서 시간초과.

모든 경우의 수를 map의 key로 만들어서 O(1)로 찾을 수 있게 하니 시간초과 안남.(답지 봄)

예를 들어 java backend junior pizza이면

java—-, backend—-, junior—-, pizza—-, javabackend—, java-junior- , … —— 전부 key값으로 만들어줘야함.

## 메모리 / 수행시간 / 코드길이

