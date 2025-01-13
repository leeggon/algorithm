## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AXWXMZta-PsDFAST&categoryId=AXWXMZta-PsDFAST&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=ALL&select-1=4&pageSize=10&pageIndex=3

## 유형

BFS

## 풀이법

N, M이 최대 10^3이였다.

땅을 시작점으로 물까지의 최소 거리를 계산하게 된다면, O(N^4)의 시간복잡도가 발생하고 시간초과가 날 것이라 판단했다.

이에 물을 시작점으로 거꾸로 생각했다. 물에 해당하는 지점을 모두 큐에 넣은 다음에 visited 배열을 활용해서 BFS를 해주게 되면

알아서 각 최단거리가 구해지게 된다. 이에 BFS를 활용하여 시간복잡도 O(N^2)의 풀이를 할 수 있었다.

## 메모리 / 수행시간 / 코드길이

100,720 KB / 904 ms / 2,353 B
