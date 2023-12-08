/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.utils;

import java.util.Calendar;
import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author Mamisoa
 */
public class DateTimeUtility {
    
    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
//    public static String getDayOfWeek(Date date){
//        int dayNumber = getDayNumberOld(date);
//        String res = "";
//        switch (dayNumber) {
//            case 1 -> {
//                res = "SUNDAY";
//            }
//            case 2 -> {
//                res = "MONDAY";
//            }
//            case 3 -> {
//                res = "TUESDAY";
//            }
//            case 4 -> {
//                res = "WEDNESDAY";
//            }
//            case 5 -> {
//                res = "THURSDAY";
//            }
//            case 6 -> {
//                res = "FRIDAY";
//            }
//            case 7 -> {
//                res = "SATURDAY ";
//            }
//            default -> {
//            }
//        }
//        return res;
//    }
    
    public static boolean isTimeBeetween(Time toCompare, Time start, Time end){
        return toCompare.after(start) && toCompare.before(end) || toCompare.compareTo(start) == 0 || toCompare.compareTo(end) == 0;
    }
    
    public static Time addHours(Time time, int hours){
       return Time.valueOf(time.toLocalTime().plusHours(hours));
    }
    
    public static Time addMinutes(Time time, int minutes){
       return Time.valueOf(time.toLocalTime().plusMinutes(minutes));
    }
    
    public static double convertMinutesToHours(int minutes){
        return minutes / 60.0;
    }
}
