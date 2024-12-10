## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/142085

## 유형

우선순위 큐(Priority Queue)

## 풀이법

우선 순위 큐를 사용하여 과거를 되돌리는 풀이를 하였다.

매 enemy[i] 마다 maxHeap에다가 넣어줬고,

만약에 현재 n값에서 enemy[i] 를 뺐을 때, 0보다 작은 값이 나온다면

k(가능한 롤백 횟수)를 차감시키며 maxHeap 에서 poll을 하여 다시 더해주는 방식으로 진행.

## 메모리 / 수행시간 / 코드길이

