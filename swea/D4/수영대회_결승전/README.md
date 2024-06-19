## 문제 링크

https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV&categoryId=AWKaG6_6AGQDFARV&categoryType=CODE

## 유형

BFS

## 풀이법

소용돌이가 있는 곳으로 이동할 수 있는 시간은 3초의 배수 시간이므로

3으로 나눈 나머지가 2일때만 이동하게 처리했고, 아닌 경우에는 제자리에서 기다리고 시간만 늘려서 큐에 다시 넣어줌.

처음에는 소용돌이를 만나면 기다리지않고, 3의 배수를 다시 넣어주는 식으로 처리했는데,

이렇게 하니까 소용돌이가 연속으로 있는 경우, 옆으로 돌아서 더 빨리 갈 수 있음에도 3의 배수 초가 연속해서 더해지는 반례가 존재했다. 

## 메모리 / 수행시간 / 코드길이

38,148KB / 267ms / 3,260B
