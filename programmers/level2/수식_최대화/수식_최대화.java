package programmers.level2.수식_최대화;

import java.util.*;

class 수식_최대화 {
	static long result;
	static List<String> list;
	static List<String> posts;
	static int[] permResult;
	static boolean[] isSelected;
	public static long solution(String expression) {
		list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<expression.length(); i++) {
			if (expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-') {
				list.add(sb.toString());
				list.add(String.valueOf(expression.charAt(i)));

				sb = new StringBuilder();
				continue;
			}

			sb.append(expression.charAt(i));
			if (i == expression.length() - 1) {
				list.add(sb.toString());
			}
		}

		result = 0;
		permResult = new int[3];
		isSelected = new boolean[3];
		perm(0);

		return result;
	}

	private static void perm(int cnt) {
		if (cnt == 3) {
			posts = new ArrayList<>();

			Stack<String> stack = new Stack<>();
			for (int i=0; i<list.size(); i++) {
				String str = list.get(i);
				if (str.length() == 1 && !Character.isDigit(str.charAt(0))) { // 연산자라면
					while (!stack.isEmpty()) {
						String top = stack.peek();
						if (permResult[operatorIndex(top)] < permResult[operatorIndex(str)]) {
							break;
						}

						posts.add(stack.pop());
					}

					stack.push(str);
				} else { // 연산자 아니고 숫자라면
					posts.add(str); // posts에 바로 붙이기
				}
			}

			while (!stack.isEmpty()) {
				posts.add(stack.pop());
			}

			stack = new Stack();

			for (int i=0; i<posts.size(); i++) {
				String str = posts.get(i);
				if (str.length() == 1 && !Character.isDigit(str.charAt(0))) {
					long b = Long.parseLong(stack.pop());
					long a = Long.parseLong(stack.pop());
					long calced = calc(a, b, str);
					stack.push(String.valueOf(calced));
				} else {
					stack.push(str);
				}

			}

			long res = Long.parseLong(stack.pop());
			result = Math.max(result, Math.abs(res));

			return;
		}

		for (int i=0; i<3; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			permResult[cnt] = i;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static int operatorIndex(String s) {
		switch(s) {
			case "*" :
				return 0;
			case "+" :
				return 1;
			default :
				return 2;
		}

	}

	private static long calc(long a, long b, String s) {
		switch(s) {
			case "*" :
				return a * b;
			case "+" :
				return a + b;
			default :
				return a - b;
		}
	}
}
