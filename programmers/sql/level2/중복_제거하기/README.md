## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/59408

## 풀이법

COUNT(DISTINCT NAME)은 고유한 이름이 몇 개 있는지 세는 것이고,

GROUP BY NAME 후에 COUNT(*)는 같은 이름의 그룹별로 몇 개의 수가 있는지 세는 것이다.

DISTINCT는 자동으로 NULL 값을 제외하고 세어준다는 것도 알게 되었다.



