## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/299308

## 풀이법

SELECT절에 작성한 CASE WHEN THEN ELSE END 문을 활용해서 GROUP BY를 수행해주었다.

2024/12/28와 같이 작성된 형식의 문자열도 MONTH()를 사용해서 숫자 형태로 추출이 가능했다.

숫자 형태로 달을 추출했으므로 BETWEEN 1 AND 3 이런 식으로 분기마다 나누어서 그룹화하였다.



