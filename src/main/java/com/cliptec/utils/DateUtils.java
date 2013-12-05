package com.cliptec.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    public static int calcAgeFromDOB(String dob){
        Calendar cal = Calendar.getInstance();
        int now_year = cal.get(Calendar.YEAR);
        int dob_year = Integer.parseInt(dob.split("-")[0]);
        return now_year - dob_year;
    }
}
