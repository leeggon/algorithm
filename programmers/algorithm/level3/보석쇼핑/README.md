## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/67258

## 유형

투 포인터

## 풀이법

10^5 이므로 일반 탐색을 돌리면 시간 초과.

투 포인터로 시간복잡도 O(N) 풀이.

right 포인터 이동 중 map의 size 가 고유 보석 개수 (set의 size)와 같아지면 stop.

left 포인터 이동 시 Map에서 수량을 줄이고,

수량이 0이 된 경우 map에서 삭제하고 최소 구간 result 업데이트 침.

이 과정을 반복하면서 모든 보석을 포함하는 최소 구간을 찾는다.

## 메모리 / 수행시간 / 코드길이
