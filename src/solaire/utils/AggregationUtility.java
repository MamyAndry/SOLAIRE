/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solaire.utils;

import utils.ObjectUtility;

/**
 *
 * @author Mamisoa
 */
public class AggregationUtility {
    
    public static Double sum(Object[] array, String field) throws Exception{
        Double res = 0.0;
        for (Object object : array) {
            res += (Double) object.getClass().getMethod("get" + ObjectUtility.capitalize(field), (Class<?>[]) null).invoke(object, (Object[]) null);
        }
        return res;
    }
    
    public static Double average(Object[] array, String field) throws Exception{
        Double res = sum(array, field);
        return res/array.length;
    }
}
