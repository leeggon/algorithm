## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/132266

## 유형

BFS

## 풀이법

처음 풀이에서, sources에 있는 모든 노드들에서 destination 노드까지의 BFS를 진행하는 식으로 풀이했다.

하지만 해당 풀이는 10^5(전체 노드) * 500(sources 길이) 의 시간복잡도를 가지게 되어 시간 초과가 발생하였다.

생각을 전환해서 종점부터 BFS를 한 번만 진행하고, 각 거리를 배열에 저장하는 식으로 10^5 풀이로 해결할 수 있었다.

## 메모리 / 수행시간 / 코드길이