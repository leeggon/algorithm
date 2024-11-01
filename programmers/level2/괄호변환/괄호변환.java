package programmers.level2.괄호변환;

import java.util.*;

class 괄호변환 {
	public String solution(String p) {
		// 1. 빈 문자열인 경우, 빈 문자열 반환
		if (p.equals("")) return "";

		// 이미 올바른거
		if (checkCorrectBrackets(p)) return p;

		// 2. 두 균형잡힌 괄호 문자열 u,v로 분리
		int left = 0;
		int right = 0;
		String u = "";
		String v = "";
		for (int i=0; i<p.length(); i++) {
			if (i == p.length() - 1) { // 마지막 문자열까지 왔다면
				u = p;
				break;
			}

			if (p.charAt(i) == '(') left++;
			else right++;

			if (left == right) {
				u = p.substring(0, i+1);
				v = p.substring(i+1);
				break;
			}
		}

		// 3. u가 "올바른 괄호 문자열"인지 판단 후, 처리
		if (checkCorrectBrackets(u)) {
			StringBuilder sb = new StringBuilder();
			sb.append(u);
			sb.append(solution(v));

			return sb.toString();
		} else {
			StringBuilder sb = new StringBuilder("(");
			sb.append(solution(v));
			sb.append(")");
			sb.append(convertU(u));

			return sb.toString();
		}
	}

	private String convertU(String u) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<u.length(); i++) {
			if (i == 0 || i == u.length() - 1) {
				continue;
			}

			if (u.charAt(i) == '(') {
				sb.append(")");
			} else {
				sb.append("(");
			}
		}

		return sb.toString();
	}

	private boolean checkCorrectBrackets(String str) {
		Stack<Character> stack = new Stack<>();

		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i) == '(') { // 왼쪽 괄호라면
				stack.push(str.charAt(i));
			} else {
				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
				}
			}
		}

		if (stack.isEmpty()) return true;
		return false;
	}
}
