## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/164671

## 풀이법

조회수가 가장 많은 게시글을 찾기 위해 USED_GOODS_BOARD 테이블에서 최대 VIEWS의 게시글을 찾는 서브쿼리문을 작성하였다.

이후, CONCAT을 사용하여 필요한 파일 경로를 찾고, 결과값들을 최종적으로 FILE_ID 기준으로 내림차순 정렬하여 표시해주었다.


