## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/276036

## 풀이법

CASE WHEN THEN END문로 SELECT 절에서 GRADE를 작성해주었다.

A, B, C 조건에 해당하지 않는 개발자들은 NULL로 표기가 되었다.

NULL이 아닌 개발자들만을 대상으로 추출하기 위해 WHERE절을 사용하려고 했으나

SQL 실행 순서에 따라 WHERE절에서 GRADE ALIAS를 사용할 수 없었다.

따라서 GROUP BY로 묶어준 다음에 HAVING으로 IS NOT NULL 조건을 붙여줬다.



