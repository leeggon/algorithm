package programmers.algorithm.level3.연속_펄스_부분_수열의_합;

import java.util.*;

class 연속_펄스_부분_수열의_합 {
    int N;

    public long solution(int[] sequence) {
        N = sequence.length;
        int[] evenPulseSequence = new int[N];
        int[] oddPulseSequence = new int[N];

        for (int i=0; i<N; i++) {
            if (i%2 == 0) {
                evenPulseSequence[i] = sequence[i];
                oddPulseSequence[i] = (-1) * sequence[i];
            } else {
                evenPulseSequence[i] = (-1) * sequence[i];
                oddPulseSequence[i] = sequence[i];
            }
        }

        long evenAnswer = evenPulseSequence[0];
        long oddAnswer = oddPulseSequence[0];

        long evenSum = evenPulseSequence[0];
        long oddSum = oddPulseSequence[0];
        for (int i=1; i<N; i++) {
            evenSum = Math.max(evenSum + evenPulseSequence[i], evenPulseSequence[i]);
            oddSum = Math.max(oddSum + oddPulseSequence[i], oddPulseSequence[i]);

            evenAnswer = Math.max(evenAnswer, evenSum);
            oddAnswer = Math.max(oddAnswer, oddSum);
        }

        return Math.max(evenAnswer, oddAnswer);
    }
}
