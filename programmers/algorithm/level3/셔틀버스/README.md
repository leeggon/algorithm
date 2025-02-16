## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/17678?language=java

## 유형

구현

## 풀이법

totalP는 남은 버스에 대해 최대 수용 가능한 인원이고, curP는 현재 대기열에 쌓여 있는 인원으로 정했다.

00:00 ~ 23:59 까지 1440 길이의 배열을 만들었고, 각각 시간에 몇 명이 대기열에 참가하는지 peopleByTime 배열에 저장했다.

또한, 버스의 도착 시간은 busArrivalSet에 저장했다.

00:00시부터 i를 순차적으로 순회하면서, curP + peopleByTime[i] >= totalP 이면 그 시각 1분 전으로 바로 정답을 return 했다.

중요한 포인트는, 현재 버스 도착 시각이라면 curP는 Math.min(m, curP) 만큼 감소해주고, totalP는 무조건 버스 인원 수 m만큼 감소시켜주는 것이었다.


## 메모리 / 수행시간 / 코드길이