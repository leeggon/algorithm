## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/151139

## 풀이법

서브쿼리문으로 2022년 8월부터 2022년 10월까지 대여 횟수가 5 이상인 CAR_ID를 추출했다.

그리고 나서 조심해야할 부분이 이렇게 추출한 CAR_ID에 대해서만 WHERE문을 작성하면 다른 달에 대여한 차량 정보까지 나오게 되므로,

AND절을 추가로 작성한 다음, 조건에 맞는 차량들을 COUNT 해주었다.



