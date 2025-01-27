## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV14BgD6AEECFAYh&categoryId=AV14BgD6AEECFAYh&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=30&pageIndex=1

## 유형

DFS

## 풀이법

100*100 배열이고, 0행을 탐색하면서 1인 노드들에 대해서만 사다리를 타면 되므로 O(10^6)의 풀이가 가능했다.

0행을 탐색하며, 1이 나오게 되면 DFS를 통해서 사다리 끝까지 가고, minDist 변수를 관리하며 최소 거리에 대해 출발점의 j 좌표를 저장했다.

DFS 과정에서 visited 배열을 잘 관리하는 것이 중요했는데, 한 번 탐색을 마친 경로에 대해서는 visited를 false로 복구해주어야 다음 경로에서의 탐색이 가능했다.

## 메모리 / 수행시간 / 코드길이

42,816 KB / 190 ms / 1,947 B
