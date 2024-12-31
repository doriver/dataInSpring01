package com.example.data.note;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class strCasting01 {

    public static void main(String[] args) {
        String strInt = "123";
        int intValue = Integer.parseInt(strInt);
        System.out.println(intValue);

        String strLong = "1234567890";
        long longValue = Long.parseLong(strLong);
        System.out.println(longValue);

        String strDateTime = "2024-12-27T14:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateTimeValue = LocalDateTime.parse(strDateTime, formatter);
        System.out.println(dateTimeValue);


        String strDay = "MONDAY";
        Day dayValue = Day.valueOf(strDay);
        System.out.println(dayValue);

        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 10, 0, 0);
        System.out.println(dateTime);
    }

    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

}
