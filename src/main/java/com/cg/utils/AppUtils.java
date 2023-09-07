package com.cg.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AppUtils {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static String getString(String str) {
        try {
            System.out.println(str);
            String data = sc.nextLine();
            if (data.equals("")) {
                throw new Exception();
            }
            return data;
        } catch (Exception e) {
            System.out.println("Empty data. Input again!");
            return getString(str);
        }
    }

    public static int getInt(String str) {
        try {
            return Integer.parseInt(getString(str));
        } catch (Exception e) {
            System.out.println("Nhập vào không hợp lệ");
            return getInt(str);
        }
    }

    public static long getLongId(String id) {
        try {
            return Long.parseLong(getString(id));
        } catch (Exception e) {
            System.out.println("Nhập vào không hợp lệ");
            return getLongId(id);
        }
    }

    public static int getIntWithBound(String str, int begin, int end) {
        try {
            int number = getInt(str);
            if (number < begin || number > end) {
                throw new NumberFormatException(String.format("Vui lòng nhập số từ %d và %d", begin, end));
            }
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getIntWithBound(str, begin, end);
        }
    }

    public static LocalDate getDateWithBound(String message, LocalDate startDate, LocalDate endDate) {
        try {
            System.out.printf("Please enter date from %s to %s with format yyyy-mm-dd \n", startDate.toString(), endDate.toString());
            LocalDate date = LocalDate.parse(getString(message));
            if (date.isBefore(startDate) || date.isAfter(endDate)) {
                System.out.println("Entered date is outside the allowed range.");
                return getDateWithBound(message, startDate, endDate);
            }
            return date;
        } catch (Exception e) {
            System.out.println("Invalid Date Format");
            return getDateWithBound(message, startDate, endDate);
        }
    }

    public static LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }

    public static int getDuration(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return (int) duration.toMinutes();
    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        try {
            return DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy").format(localDateTime);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return null;
    }

    public static String formatDate(LocalDate localDate) {
        try {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime getDateTime(String str) {
        try {
            return  LocalDateTime.parse(getString(str + " (yyyy-MM-dd HH:mm:ss):"), DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Định dạng ngày tháng hợp lệ. Vui lòng nhập ngày và giờ theo định dạng 'yyyy-MM-dd HH:mm:ss'.");
            return getDateTime(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getDateTime(str);
        }
    }

    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(input, formatter);
    }

    public static void main(String[] args) {

        String strTime = AppUtils.getString("Nhập thời gian lập phiếu");
        LocalDateTime time = AppUtils.getDateTime(strTime);
        System.out.println("Thời gian lập phiếu là: " + time);
    }

}
