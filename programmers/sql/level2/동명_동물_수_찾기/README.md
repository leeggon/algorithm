## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/59041

## 풀이법

GROUP BY NAME을 하고 나서,

1. COUNT (NAME) 을 하면, <br> NAME이 NULL인 애들의 그룹이 만들어질 순 있다. 하지만 COUNT(NAME)의 값이 0이 되어서, HAVING 조건을 만족하지 못하고 결과에서 제외됨.

2. 반면 COUNT(*) 을 하면, <br> NAME이 NULL이더라도 COUNT(*)에서 세어지고, HAVING 절을 만족하게 된다면 그대로 출력이 된다.

이 중요한 차이점을 알게 되었다. 나중에 NULL값을 포함시켜야 하는 문제에서도 써먹어야겠다.



