package programmers.algorithm.level2.방금그곡;

import java.util.*;

class 방금그곡 {
	static class Song implements Comparable<Song> {
		int index;
		int duration;
		String title;
		String base;

		public Song (int index, int duration, String title, String base) {
			this.index = index;
			this.duration = duration;
			this.title = title;
			this.base = base;
		}

		@Override
		public int compareTo (Song o) {
			if (this.duration != o.duration) {
				return Integer.compare(o.duration, this.duration);
			}

			if (this.index != o.index) {
				return Integer.compare(this.index, o.index);
			}

			return 0;
		}

		@Override
		public String toString() {
			return "index : " + index + " duration : " + duration + " title : " + title + " base : " + base;
		}
	}

	public static String solution(String m, String[] musicinfos) {
		PriorityQueue<Song> pq = new PriorityQueue<>();

		for (int i=0; i<musicinfos.length; i++) {
			String[] infos = musicinfos[i].split(",");
			String[] startTimes = infos[0].split(":");
			String[] endTimes = infos[1].split(":");
			int duration = 60 * (Integer.parseInt(endTimes[0]) - Integer.parseInt(startTimes[0])) + (Integer.parseInt(endTimes[1]) - Integer.parseInt(startTimes[1]));
			String title = infos[2];
			String base = infos[3];
			base = convert(base);

			pq.offer(new Song(i, duration, title, base));

		}

		System.out.println(pq);
		String convertedM = convert(m);
		System.out.print("convertedM : ");
		System.out.println(convertedM);
		while (!pq.isEmpty()) {
			Song song = pq.poll();
			String realBase = song.base;
			if (song.duration < song.base.length()) {
				realBase = song.base.substring(0,song.duration);
				if (realBase.contains(convertedM)) return song.title;
				continue;
			}

			System.out.print("realBase : ");
			System.out.println(realBase);
			int mTemp = (m.length()==1) ? 0 : m.length() - 2;
			int repeat = (mTemp / realBase.length()) + 1;

			StringBuilder sb = new StringBuilder(realBase);
			for (int i=0; i<repeat; i++) {
				for (int j=0; j<realBase.length(); j++) {
					sb.append(realBase.charAt(j));
				}
			}
			String str = sb.toString();
			System.out.print("str : ");
			System.out.println(str);

			if (str.contains(convertedM)) return song.title;

		}


		return "(None)";
	}

	private static String convert (String str) {
		str = str.replaceAll("A#", "a");
		str = str.replaceAll("C#", "c");
		str = str.replaceAll("D#", "d");
		str = str.replaceAll("F#", "f");
		str = str.replaceAll("G#", "g");

		return str;
	}
}
