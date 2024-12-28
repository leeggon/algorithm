## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/284531

## 풀이법

ROUND로 반올림을 해주고, CONCAT으로 'km'를 붙여주었다.

ORDER BY 할 때 주의할 점이 CONCAT으로 이어붙인 문자열로 정렬하게 되면 올바르게 정렬이 되지 않는다고 한다.

그래서 CONCAT을 제외한 숫자값으로 정렬을 해주었다.



