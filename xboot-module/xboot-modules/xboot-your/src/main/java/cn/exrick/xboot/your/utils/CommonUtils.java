package cn.exrick.xboot.your.utils;

import lombok.Builder;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CommonUtils {
    @Data
    @Builder
    public static class DayCompare{
        private int year;
        private int month;
        private int day;
    }
    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare dayComparePrecise(Date fromDate, Date toDate){
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        return DayCompare.builder().day(day).month(month).year(year).build();
    }
    /**
     * 计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少
     * 比如：2011-02-02 到  2017-03-02
     *                                以年为单位相差为：6年
     *                                以月为单位相差为：73个月
     *                                以日为单位相差为：2220天
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare dayCompare(Date fromDate,Date toDate){
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        //只要年月
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int year = toYear  -  fromYear;
        int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
        int day = (int) ((to.getTimeInMillis()  -  from.getTimeInMillis())  /  (24  *  3600  *  1000));
        return DayCompare.builder().day(day).month(month).year(year).build();
    }
    /**
     * 计算2个日期相差多少年
     * 列：2011-02-02  ~  2017-03-02 大约相差 6.1 年
     * @param fromDate
     * @param toDate
     * @return
     */
    public static String yearCompare(Date fromDate,Date toDate){
        DayCompare result = dayComparePrecise(fromDate, toDate);
        double month = result.getMonth();
        double year = result.getYear();
        //返回2位小数，并且四舍五入
        DecimalFormat df = new DecimalFormat("######0.0");
        return df.format(Math.floor(year + month / 12));
    }


    /**
     * java生成随机字母6位数
     * @param
     * @return
     */
    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 字符串
            // 取得大写字母还是小写字母
//            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
            //只取大写字母
            int choice = 65;
            val += (char) (choice + random.nextInt(26));
        }
        return val;
    }
}
