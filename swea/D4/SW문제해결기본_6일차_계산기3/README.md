## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV14tDX6AFgCFAYD&categoryId=AV14tDX6AFgCFAYD&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=30&pageIndex=2

## 유형

스택 (Stack)

## 풀이법

크게 두가지 연산으로 나눴다.
1. 중위표기 -> 후위표기 변환
2. 후위표기 -> 계산

1번 연산만 간략히 적어보자면,
1. ( 는 무조건 스택에 넣어줌. ) 는 ( 이 나올때까지 무조건 모두 pop.
2. 나머지 산술 연산자의 경우에는 자기 자신보다 우선순위가 크거나 같은 애들을 모두 pop 시키고 자기는 스택에 들어간다.
3. 숫자는 바로 문자열에 추가해줌.

## 메모리 / 수행시간 / 코드길이

22,528 KB / 71 ms / 2,447 B
