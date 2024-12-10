## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/147354

## 유형

static, 우선순위 큐

## 풀이법

정렬을 하려고 했으나, col번째 컬럼으로 정렬을 하기 위해서,

Element 클래스와 solution 메서드에서 N 값을 공유할 수 있도록 static으로 설정했다.

compareTo 메서드를 정의함으로써 정렬 조건을 줬고, xor 연산을 순차적으로 진행해줬다.

## 메모리 / 수행시간 / 코드길이