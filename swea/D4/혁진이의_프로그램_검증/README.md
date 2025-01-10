## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV4yLUiKDUoDFAUx&categoryId=AV4yLUiKDUoDFAUx&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=10&pageIndex=2

## 유형

BFS, 빡구현

## 풀이법

처음 BFS나 DFS로 풀이하지 않고, 빡구현으로만 풀이했는데 오답이 나왔다.

? 분기점에서 착각한 점이 있는데

내 생각: ?에서 4가지로 분기할 수 있으니깐 아직 방문하지 않은 방향이 남아있다면 그 방향으로 진행하자.

정답: ?에서 4가지로 분기할 수 있으니깐 각 상황에 대해 모두 고려해줘야하므로 BFS/DFS 풀이가 필요하다.

당연한 얘기인 것 같지만, 막상 문제를 풀 때는 헷갈렸다.

BFS 풀이를 사용했고, 프로그램 종료 여부는 4차원 visited 배열을 사용했다.

그 이유는 curI, curJ: 현재 좌표, curD: 현재 방향, memory: 현재 메모리의 값 4가지 모두가 같은 상황이어야 완벽하게 같은 상황이라고 판단할 수 있기 때문이다.

3차원 배열까지는 잘 생각했는데, memory까지 생각하는 풀이가 쉽지 않았다.

## 메모리 / 수행시간 / 코드길이

33,192 KB / 160 ms / 4,803 B
