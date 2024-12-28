## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/293261

## 풀이법

HAVING LENGTH = MAX(LENGTH)를 활용한 서브쿼리문 작성이 핵심이었다.

단, 물고기 종류별 가장 큰 물고기는 1마리만 있으며

라는 문제의 조건이 있었기에 이렇게 서브쿼리 문을 작성하여 그룹별로 가장 큰 물고기에 해당하는 FISH_TYPE을 추출할 수 있었고,

이름 테이블과 JOIN한 테이블에서 각 물고기에 대한 정보를 얻어올 수 있었다.
