package onboarding;

import java.util.List;

class Problem1 {
    public static final int pobiWinRes = 1;
    public static final int crongWinRes = 2;
    public static final int drawRes = 0;
    public static final int exceptionRes = -1;
    public static int pobiMaxVal;
    public static int crongMaxVal;


    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;
        if (!isException(pobi, crong)) {
            answer = exceptionRes;
        } else {
            findMaxVal(pobi, crong);
            answer = compareMaxVal(pobiMaxVal, crongMaxVal);
        }
        return answer;
    }

    private static void findMaxVal(List<Integer> pobi, List<Integer> crong) {
        pobiMaxVal = Math.max(Math.max(sum(pobi.get(0)), mul(pobi.get(0))),
                Math.max(sum(pobi.get(1)), mul(pobi.get(1))));
        crongMaxVal = Math.max(Math.max(sum(crong.get(0)), mul(crong.get(0))),
                Math.max(sum(crong.get(1)), mul(crong.get(1))));
    }

    private static int mul(int pageNum) {
        int res = 1;
        int num = pageNum;
        while (true) {
            if (num % 10 == 0) {
                break;
            }
            res *= (num % 10);
            num /= 10;
        }
        return res;
    }

    private static int sum(int pageNum) {
        int res = 0;
        int num = pageNum;
        while (true) {
            if (num % 10 == 0) {
                break;
            }
            res *= (num % 10);
            num /= 10;
        }
        return res;
    }

    public static int compareMaxVal(int pobiMaxVal, int crongMaxVal) {
        if (pobiMaxVal > crongMaxVal) {
            return pobiWinRes;
        } else if (pobiMaxVal < crongMaxVal) {
            return crongWinRes;
        } else {
            return drawRes;
        }
    }

    public static boolean isException(List<Integer> pobi, List<Integer> crong) {
        //시작 면이나 마지막 면인지 체크
        if (isStartEndPage(pobi, crong)) {
            //왼쪽 페이지는 홀수 오른쪽 페이지는 짝수인지 체크
            if (isOddEvenPage(pobi, crong)) {
                //왼쪽 페이지 +1 == 오른쪽 페이지인지 체크
                return isCorrectPage(pobi, crong);
            }
        }
        return false;
    }

    private static boolean isCorrectPage(List<Integer> pobi, List<Integer> crong) {
        return (pobi.get(0) + 1 == pobi.get(1)) && (crong.get(0) + 1 == crong.get(1));
    }

    private static boolean isOddEvenPage(List<Integer> pobi, List<Integer> crong) {
        return (pobi.get(0) % 2 != 0) && (pobi.get(1) % 2 == 0) &&
                (crong.get(0) % 2 != 0) && (crong.get(1) % 2 == 0);
    }

    private static boolean isStartEndPage(List<Integer> pobi, List<Integer> crong) {
        return (pobi.get(0) < pobi.get(1) && pobi.get(0) > 1 && pobi.get(1) < 400) &&
                (crong.get(0) < crong.get(1) && crong.get(0) > 1 && crong.get(1) < 400);
    }
}