package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberToWords {
    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("12345.67");
        String result = convert(value);
        System.out.println(result);
    }

    public static final String [] UNITS = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь","восемь", "девять"};
    public static final String [] TEENS = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать","восемнадцать", "девятнадцать"};
    public static final String [] TENS = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят",
            "семьдесят", "восемьдесят","девяносто"};
    public static final String [] HOUNDREDS = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот","девятьсот"};
    public static final String [] THOUSANDS = {"", "тысяча", "две тысячи", "три тысячи", "четыре тысячи", "пять тысяч",
            "шесть тысяч", "семь тысяч", "восемь тысяч","девять тысяч"};

    public static String convert(BigDecimal number) {
        if (number == null || number.compareTo(BigDecimal.ZERO) < 0 || number.compareTo(new BigDecimal("99999.99")) > 0) {
            throw new IllegalArgumentException("Число должно быть в диапазоне [0, 99999.99]");
        }

        number = number.setScale(2, RoundingMode.HALF_UP); // округление до 2 знаков после ","

        String[] parts = number.toString().split("\\.");
        int wholePart = Integer.parseInt(parts[0]);
        int fractionalPart = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;

        StringBuilder result = new StringBuilder(convertWholePart(wholePart));

        if (fractionalPart > 0) {
            result.append(" целых ").append(convertWholePart(fractionalPart)).append(" сотых");
        }else {
            result.append(" рублей");
        }
        return result.toString().trim();
    }

    public static String convertWholePart(int number) {
        if (number == 0) {
            return "ноль";
        }
        StringBuilder result = new StringBuilder();
        if (number >= 10000) {
            result.append(THOUSANDS[number / 10000]);
            number %= 1000;
        }
        if (number >= 100) {
            result.append(" ").append(HOUNDREDS[number / 100]);
            number %= 100;
        }
        if (number >= 20) {
            result.append(" ").append(TEENS[number / 10]);
            number %= 10;
        } else if (number >= 10) {
            result.append(" ").append(TENS[number - 10]);
            return result.toString().trim();
        }
        if (number > 0) {
            result.append(" ").append(UNITS[number]);
        }
        return result.toString().trim();
    }
}
