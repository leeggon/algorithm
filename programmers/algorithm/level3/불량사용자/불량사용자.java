package programmers.algorithm.level3.불량사용자;

import java.util.*;

class 불량사용자 {
	static Set<String> resultSet;
	static Map<String, Integer> map;
	static ArrayList<String>[] candidates;
	static String[] permRepResult;
	public static int solution(String[] user_id, String[] banned_id) {
		resultSet = new HashSet<>();

		map = new HashMap<>();
		for (int i=0; i<user_id.length; i++) {
			map.put(user_id[i], i);
		}

		candidates = new ArrayList[banned_id.length];
		for (int i=0; i<banned_id.length; i++) {
			candidates[i] = new ArrayList<>();
		}

		for (int i=0; i<banned_id.length; i++) {
			String template = banned_id[i];
			for (int j=0; j<user_id.length; j++) {
				String str = user_id[j];
				if (checkAvailableString(template, str)) {
					candidates[i].add(str);
				}
			}
		}

		permRepResult = new String[candidates.length];
		permRep(0, candidates.length);

		return resultSet.size();
	}

	private static void permRep(int cnt, int N) {
		if (cnt == N) {
			// 중복 일어나는지 체크
			Set<String> set = new HashSet<>();
			for (int i=0; i<permRepResult.length; i++) {
				if (set.contains(permRepResult[i])) return;
				set.add(permRepResult[i]);
			}

			// map 인덱스대로 pq에 넣고, 전부 poll해서 sb.toString()해서 다시 중복 체크
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i=0; i<permRepResult.length; i++) {
				pq.offer(map.get(permRepResult[i]));
			}

			StringBuilder sb = new StringBuilder();
			while (!pq.isEmpty()) {
				sb.append(String.valueOf(pq.poll()));
			}

			String res = sb.toString();
			if (resultSet.contains(res)) return;
			resultSet.add(res);

			return;
		}

		for (int i=0; i<candidates[cnt].size(); i++) {
			permRepResult[cnt] = candidates[cnt].get(i);
			permRep(cnt+1, N);
		}
	}

	private static boolean checkAvailableString(String template, String str) {
		if (template.length() != str.length()) return false;

		for (int i=0; i<template.length(); i++) {
			if (template.charAt(i) == '*') continue;
			if (template.charAt(i) != str.charAt(i)) return false;
		}

		return true;
	}
}
