`## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/64064

## 유형

완전탐색

## 풀이법

문제 조건 : 중복되는 user_id는 없음. 따라서 user_id : index map을 한 개 만들어 두었음.

시간복잡도를 계산했을 때 중복 순열 완전탐색을 하더라도 8^6(약 10^7)이라서 가능하다고 판단함.

각 밴 패턴마다 가능한 candidate들을 선정하고 중복 순열을 구한 다음

중복 순열 간에 중복되는 user_id가 있는지 판단.

없다면, 각 user_id들의 index를 map에서 찾고, 작은 순서대로 붙여본다.

예를 들어 frodo(0), crodo(1), abc1234(2)와 crodo(1), frodo(0), abc1234(2) 둘다 "012"로 붙여지게 된다.

이것으로 총 result를 중복 없이 셀 수 있다.


## 메모리 / 수행시간 / 코드길이