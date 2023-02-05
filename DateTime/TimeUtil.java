
import cn.hutool.core.collection.ListUtil;

import java.util.List;

public class TimeUtil {

    /**
     * 时长比较类
     */
    public static int timeCompare(String o1, String o2) {
        String[] s1 = o1.split(":");
        String[] s2 = o2.split(":");
        for (int i = 0; i < s1.length && i < s2.length; i++) {
            Long l1 = Long.valueOf(s1[i]);
            Long l2 = Long.valueOf(s2[i]);
            if (!l1.equals(l2)) {
                return (int) (l1 - l2);
            }
        }
        return 0;
    }

    public static String handleTimeBackHourMin(String t1, String t2) {
        Long[] i = handleTimeBackHourMinSec(ListUtil.toList(t1, t2));
        return i[0] + ":" + i[1];
    }

    public static String handleTimeBackHourMinSec(String t1, String t2) {
        Long[] i = handleTimeBackHourMinSec(ListUtil.toList(t1, t2));
        return i[0] + ":" + i[1] + ":" + i[2];
    }

    /**
     * 计算时长总和
     *
     * @param time 格式 时:分:秒
     * @return [时, 分, 秒]
     */
    public static Long[] handleTimeBackHourMinSec(List<String> time) {
        long resMin = 0, resSec = 0, resHour = 0;
        for (String t : time) {
            String[] split = t.split(":");
            resHour += Long.parseLong(split[0]);
            resMin += Long.parseLong(split[1]);
            resSec += Long.parseLong(split[2]);
        }
        resMin += (resSec / 60);
        resSec %= 60;
        resHour += (resMin / 60);
        resMin %= 60;

        return new Long[]{resHour, resMin, resSec};
    }

//    /**
//     * 计算时长总和
//     *
//     * @param time 格式 分:秒
//     * @return [时, 分, 秒]
//     */
//    public static Long[] handleMinSecTimeBackHourMinSec(List<String> time) {
//        long resMin = 0, resSec = 0, resHour = 0;
//        for (String t : time) {
//            String[] split = t.split(":");
//            resMin += Long.parseLong(split[0]);
//            resSec += Long.parseLong(split[1]);
//        }
//        resMin += (resSec / 60);
//        resSec %= 60;
//        resHour += (resMin / 60);
//        resMin %= 60;
//
//        return new Long[]{resHour, resMin, resSec};
//    }

    public static String handleTimeBackMin(List<String> time) {
        Long[] hourMinSec = handleTimeBackHourMinSec(time);
        long hour = hourMinSec[0];
        long min = hourMinSec[1] + hour * 60;
        return Long.toString(min);
    }

    public static String handleTimeBackMinSec(List<String> time) {
        Long[] split = handleTimeBackHourMinSec(time);
        long hour = split[0];
        long min = split[1] + hour * 60;
        return min + ":" + split[2];
    }

    public static String handleTimeBackSec(List<String> time) {
        Long[] split = handleTimeBackHourMinSec(time);
        long hour = split[0];
        long min = split[1] + hour * 60;
        return min * 60 + split[2].toString();
    }
}

