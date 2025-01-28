## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWMeRLz6kC0DFAXd&categoryId=AWMeRLz6kC0DFAXd&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=30&pageIndex=1

## 유형

구현

## 풀이법

한 자리수만 변경의 가능성이 있었다. set에 2진수의 조합으로 가능한 모든 경우의 수를 넣어놓고,

3진수를 보면서 set에 있는 값이라면 바로 정답으로 출력해주었다.

자리 수가 바뀔때마다 모든 자리수를 계산하는 것이 비효율적이라고 생각해서, 초기 값을 계산해놓고,

자리 수가 바뀔때마다 해당 자리의 증감값 만을 계산해서 증감 계산을 해주었다.

## 메모리 / 수행시간 / 코드길이

25,008 KB / 82 ms / 1,890 B
