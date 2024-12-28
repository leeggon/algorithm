## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/273712

## 풀이법

IN의 경우 or 연산으로 하나라도 참(= 연산이 true)인 결과가 있다면 해당 데이터가 조회되는 것인데,

NOT IN의 경우 이것의 반대이므로 and 연산이 된다고 한다.

즉 서브쿼리에 있는 모든 데이터에 대해서 !=의 결과가 true인 데이터가 조회된다.

이 문제의 parent_item_id엔 null 값이 포함되어있다. 이 null값과 !=연산을 하는 경우 UNKNOWN(false) 값이 반환되어서 모두 조회되지 않는 문제점이 발생한다.

그래서 NOT IN 서브쿼리에서 null값을 제외시켜주는 조건문을 작성해주었다.



