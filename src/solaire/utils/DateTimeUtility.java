/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.utils;

import java.util.Calendar;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
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
    
    public static HashMap<Integer, String> getMappingDayOfTheWeek(){
        HashMap<Integer, String> mapping = new HashMap<>();
        mapping.put(1, "SUNDAY");
        mapping.put(2, "MONDAY");
        mapping.put(3, "TUESDAY");
        mapping.put(4, "WEDNESDAY");
        mapping.put(5, "THURSDAY");
        mapping.put(6, "FRIDAY");
        mapping.put(7, "SATURDAY");
        return mapping;
    }
    
    public static String getDayOfWeek(Date date){
        int dayNumber = getDayNumberOld(date);
        return getMappingDayOfTheWeek().get(dayNumber);
    }
    
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
