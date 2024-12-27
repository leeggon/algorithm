## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/299305

## 풀이법

부모는 자식이 없더라고 모든 정보가 나와야하므로, 부모 기준으로 left outer join을 해주었다.

그리고 나서, count를 셀 때 자식이 없더라도 0이 나오게 하기 위해서 count() 안에 C.ID를 명시해줌으로써 0을 포함한 자식의 개수를 셀 수 있었다.



