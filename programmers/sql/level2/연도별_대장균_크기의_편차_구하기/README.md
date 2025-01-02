## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/299310

## 풀이법

JOIN으로 각 대장균의 연도에 해당하는 연도별 최대 대장균 크기를 JOIN 해주었다.

하지만 JOIN할 때 주의할 것이 있었는데 내부 쿼리에 작성한 열 이름에는 반드시 별칭을 작성해주어야 외부에서 사용이 가능하다는 것이었다.

그래서 YEAR, MAX_SIZE 별칭을 지정해주었고, 이것으로 차를 구하고 정렬함으로써 정답을 도출했다.


