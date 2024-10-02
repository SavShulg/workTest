package org.example;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date nextDelivery = getNextDeliveryDate();
        System.out.println("Следующая дата отправки: " + nextDelivery);
    }

    public static Date getNextDeliveryDate() {
        Calendar today = Calendar.getInstance();
        Date now = new Date(today.getTimeInMillis());

        int[] sendDays = {1, 10, 20};
        Date nearestDeliveryDate = null;

        for (int day : sendDays) {
            Calendar deliveryCalendar = Calendar.getInstance();
            deliveryCalendar.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), day, 18, 0, 0);
            deliveryCalendar.set(Calendar.MILLISECOND, 0);

            // Дата отправки
            Date deliveryDate = new Date(deliveryCalendar.getTimeInMillis());

            if (deliveryDate.after(now)) {
                nearestDeliveryDate = deliveryDate;
                break;
            }
        }
        if (nearestDeliveryDate == null) {
            Calendar deliveryCalendar = Calendar.getInstance();
            deliveryCalendar.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, 1, 18, 0, 0);
            deliveryCalendar.set(Calendar.MILLISECOND, 0);
            nearestDeliveryDate = new Date(deliveryCalendar.getTimeInMillis());
        }
        Date checkDate = getVacCheck(nearestDeliveryDate);

        while (!checkDate.equals(nearestDeliveryDate)) {
            nearestDeliveryDate = checkDate;
            checkDate = getVacCheck(nearestDeliveryDate);
        }
        return nearestDeliveryDate;
    }
    public static Date getVacCheck(Date modDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(modDate);

        // проверка на выходные
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -2);
        }
        return new Date(cal.getTimeInMillis());
    }
}
