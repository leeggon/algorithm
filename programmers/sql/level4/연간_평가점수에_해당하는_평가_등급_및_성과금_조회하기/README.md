## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/284528

## 풀이법

각 사원별 기준(평균) 점수를 구하기 위해서

1. HR_EMPLOYEES와 HR_GRADE 테이블 JOIN
2. EMP_NO로 GROUP BY
3. AVG(G.SCORE) 집계 함수로 평균을 구하고, CASE WHEN THEN문을 사용하여 GRADE와 BONUS를 구해주었다.



