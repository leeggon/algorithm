## 문제 링크

https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWqU0zh6rssDFARG&categoryId=AWqU0zh6rssDFARG&categoryType=CODE&problemTitle=&orderBy=SUBMIT_COUNT&selectCodeLang=ALL&select-1=4&pageSize=30&pageIndex=1

## 유형

정렬, Comparator

## 풀이법

1순위: 문자열 길이 순
2순위: 알파벳 순

으로 정렬을 수행하는 문제였다. Comparator의 활용법에 대해 알게 되었고 익명 클래스를 활용해서 compare 메서드를 오버라이드하는 방식으로 구현했다.

람다식을 사용하는 더 간단한 방법도 있었다.

// 정렬 로직
strings.sort(Comparator
.comparingInt(String::length) // 1순위: 문자열 길이로 정렬
.thenComparing(Comparator.naturalOrder())); // 2순위: 알파벳 순 정렬

## 메모리 / 수행시간 / 코드길이

160,340 KB / 932 ms / 1,565 B
