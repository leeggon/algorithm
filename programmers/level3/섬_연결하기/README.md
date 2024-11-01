`## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/42861

## 유형

최소 신장 트리 MST(Minimum Spanning Tree)

## 풀이법

전형적인 MST 문제였다.

Kruskal vs Prim 중 Kruskal 풀이법을 사용했다.

간선 리스트들을 비용순으로 정렬하고, 가장 비용이 적은 간선부터 뽑으며 사이클이 발생하지 않는다면 union find 알고리즘을 통해 union하면서 신장 트리를 완성해나간다.


## 메모리 / 수행시간 / 코드길이