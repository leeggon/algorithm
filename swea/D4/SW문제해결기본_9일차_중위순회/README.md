## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV140YnqAIECFAYD&categoryId=AV140YnqAIECFAYD&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=30&pageIndex=2

## 유형

중위순회, DFS

## 풀이법

트리라서 DFS 과정에서 visited 배열 관리는 따로 필요없었다.

graph를 통해서 자식 노드들을 저장하게 했고, 중위순회인만큼 첫번째 원소(왼쪽 자식)를 쭉 탐색하고 나서 자기 자신의 문자를 StringBuilder에 출력하고,

이후에 두번째 원소(오른쪽 자식)을 쭉 탐색하도록 구현했다.

## 메모리 / 수행시간 / 코드길이

25,984 KB / 90 ms / 1,594 B
