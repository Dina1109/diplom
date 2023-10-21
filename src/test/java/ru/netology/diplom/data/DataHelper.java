package ru.netology.diplom.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    static Faker enOption = new Faker(new Locale("en"));
    static Faker faker = new Faker(new Locale("ru"));
    static DataGenerator dataGenerator = new DataGenerator();
    static CardNumber cardNumber = new CardNumber();

    public static CardInformation getValidCard() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(1).getYear(),
                dataGenerator.shiftMonth(1).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getCurrentMonthAndYear() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0).getYear(),
                dataGenerator.shiftMonth(0).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getDeclinedCard() {
        return new CardInformation(
                cardNumber.getDeclinedCardNumber(),
                dataGenerator.shiftYear(1).getYear(),
                dataGenerator.shiftMonth(1).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getAllFieldsEmpty() {
        return new CardInformation(
                " ",
                " ",
                " ",
                " ",
                " ");
    }

    public static CardInformation getCardNumberEmpty() {
        return new CardInformation(
                " ",
                dataGenerator.shiftYear(1).getYear(),
                dataGenerator.shiftMonth(1).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getYearEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                " ",
                dataGenerator.shiftMonth(1).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getMonthEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(2).getYear(),
                " ",
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getHolderEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(2).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                " ",
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getCVVEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(3).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                enOption.name().fullName(),
                "");
    }

    public static CardInformation getExpiredYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(-1).getYear(),
                dataGenerator.shiftMonth(0).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getExpiredMonth() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0).getYear(),
                dataGenerator.shiftMonth(-1).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getInvalidNumber() {
        return new CardInformation(
                cardNumber.getInvalidCardNumber(),
                dataGenerator.shiftYear(1).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getWrongYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.wrongYear().getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getInvalidMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(3).getYear(),
                dataGenerator.wrongMonth().getMonth(),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getNumericName() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                Integer.toString(enOption.number().numberBetween(1, 999)),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getInvalidCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                faker.name().fullName(),
                Integer.toString(enOption.number().numberBetween(0, 99)));
    }

    public static CardInformation getZeroCard() {
        return new CardInformation(
                "0000000000000000",
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                faker.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getZeroMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0).getYear(),
                "00",
                faker.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getZeroCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5).getYear(),
                dataGenerator.shiftMonth(2).getMonth(),
                faker.name().fullName(),
                "000");
    }

    @Value
    public static class CardInformation {
        private String cardNumber;
        private String year;
        private String month;
        private String holder;
        private String CVV;
    }
}
