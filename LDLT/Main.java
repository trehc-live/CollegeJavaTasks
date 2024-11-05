package com.stackoverflow.LDLT;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Supplier;

import static java.util.Calendar.*;

/**
* 1. Основы LocalDate и LocalTime
* Задача: Напишите программу, которая:
* Создает объект LocalDate, представляющий текущую дату.
* Создает объект LocalTime, представляющий текущее время.
* Выводит их значения в формате dd-MM-yyyy HH:mm:ss
*/
class Task1{
    public static void main(String... args) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedDate = currentDate.format(dateFormatter);
        String formattedTime = currentTime.format(timeFormatter);

        System.out.println(formattedDate + " " + formattedTime);
    }
}

/**
* 2. Сравнение дат
* Задача: Напишите метод, который принимает две даты (LocalDate) и определяет, какая из них больше,
* меньше или равна другой. Верните соответствующее сообщение.
**/
class Task2 {
    public static void main(String... args) {
        LocalDate firstDate = LocalDate.now();
        LocalDate secondDate = LocalDate.of(2025, 1, 1);

        System.out.println(compareDates(firstDate, secondDate));
    }

   private static String compareDates(LocalDate a, LocalDate b) {
       int compareValue = a.compareTo(b);

       if (compareValue > 0)
           return a + " is latter than " + b;
       else if (compareValue < 0)
           return a + " is earlier than " + b;
       else
           return "both dates are equal";
   }
}

/**
* 3. Сколько дней до Нового года?
* Задача: Создайте метод, который определяет количество дней до Нового года от текущей даты.
*/
class Task3 {
    public static void main(String... args) {
        System.out.println(daysTillNewYear(LocalDate.now()));
    }

    private static long daysTillNewYear(LocalDate date) {
        LocalDate endOfYear = LocalDate.of(date.getYear() + 1, 1, 3);
        return ChronoUnit.DAYS.between(date, endOfYear);
    }
}

/**
* 4. Проверка високосного года
* Задача: Реализуйте метод, который принимает год и возвращает true, если он является високосным,
* и false в противном случае.
*/
class Task4 {
    public static void main(String... args) {
        System.out.println(isLeapYear(2000));
    }

    private static boolean isLeapYear(int year){
        return (year % 4) == 0;
    }
}

/**
* 5. Подсчет выходных за месяц
* Задача: Напишите метод, который принимает месяц и год и возвращает количество выходных
* (суббот и воскресений) в этом месяце.
*/
class Task5 {
    public static void main(String... args) {
        int year = 2023;
        int month = 10;

        System.out.println("Количество выходных: " + countWeekends(year, month));
    }

    private static int countWeekends(int year, int month) {
         month--;

         Calendar calendar = Calendar.getInstance();
         calendar.set(year, month, 1);

         int weekendsCount = 0;
         int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

         for (int day = 1; day <= daysInMonth; day++) {
             calendar.set(year, month, day);
             int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
             if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY)
                 weekendsCount++;
         }
         return weekendsCount;
    }
}

/**
* 6. Расчет времени выполнения метода
* Задача: Реализуйте метод, который измеряет время выполнения другого метода
* (например, цикла из 1 миллиона итераций).
*/
class Task6 {
    public static void main(String... args) {
        System.out.println(measureFuncTime(() -> {
            for (int i = 0; i < 1000000; i++) {}
            return null;
        }));
    }

    private static long measureFuncTime(Supplier<Void> method) {
        long startTime = System.nanoTime();
        method.get();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}


/**
* 7. Форматирование и парсинг даты
* Задача: Напишите программу, которая принимает строку даты в формате dd-MM-yyyy,
 * конвертирует ее в объект LocalDate, добавляет к ней 10 дней и выводит результат в формате yyyy/MM/dd.
*/
class Task7 {
    public static void main(String... args) {
        convertDate("11-11-1111");
    }

    private static void convertDate(String date) {
        if (!validate(date))
            return;

        DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate localDate = LocalDate.parse(date, inFormat);
        LocalDate newDate = localDate.plusDays(10);

        String formattedDate = newDate.format(outFormat);

        System.out.println(formattedDate);
    }

    private static boolean validate(String date) {
        return date.matches("\\d{2}-\\d{2}-\\d{4}");
    }
}

/**
* 8. Конвертация между часовыми поясами
* Задача: Напишите метод, который принимает дату и время в часовом поясе UTC
* и конвертирует его в другой часовой пояс, например, Europe/Moscow.
*/
class Task8 {//не понял как это реализовать...
    public static void main(String... args) {
        String utcTime = "2023-03-15T12:00:00";
        String timeZone = "Europe/Moscow";

        String convertedTime = convertUtcToTimezone(utcTime, timeZone);
        System.out.println("Converted time: " + convertedTime);
    }

    private static String convertUtcToTimezone(String utcDateTime, String timeZone) {
        ZonedDateTime utcZonedDateTime = ZonedDateTime.parse(utcDateTime + "Z");

        ZoneId targetZoneId = ZoneId.of(timeZone);

        ZonedDateTime targetZonedDateTime = utcZonedDateTime.withZoneSameInstant(targetZoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return targetZonedDateTime.format(formatter);
    }
}

/**
* 9. Вычисление возраста по дате рождения
* Задача: Напишите метод, который принимает дату рождения в формате LocalDate
* и возвращает текущий возраст в годах.
*/
class Task9 {
    public static void main(String... args) {
        System.out.println(getAge(LocalDate.parse("2023-11-11")));
    }

    private static int getAge(LocalDate localDate){
        return LocalDate.now().getYear() - localDate.getYear();
    }
}

/**
* 10. Создание календаря на месяц
* Задача: Напишите метод, который принимает месяц и год и выводит все даты месяца с указанием,
* является ли каждая из них рабочим днем или выходным.
*/
class Task10 {
    public static void main(String... args) {
        printCalendar(2024, 8);
    }

    private static void printCalendar(int year, int month) {
        month--;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(year, month, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY)
                System.out.println(day + " - выходной");
            else
                System.out.println(day + " - рабочий");
        }
    }
}

/**
* 11. Генерация случайной даты в диапазоне
* Задача: Реализуйте метод, который принимает две даты и генерирует случайную дату в этом диапазоне.
*/
class Task11 {
    public static void main(String... args) {
        System.out.println(generateRandomDate(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));
    }

    public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("startDate must be before or equal to endDate");

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        long randomDays = new Random().nextInt((int) daysBetween + 1);

        return startDate.plusDays(randomDays);
    }
}

/**
* 12. Расчет времени до заданной даты
* Задача: Создайте метод, который принимает дату и время события и рассчитывает,
* сколько часов, минут и секунд осталось до него от текущего момента.
*/
class Task12 {
    public static void main(String... args) {
        HMSTillEvent("2024-12-31 23:59:59");
    }

    private static void HMSTillEvent(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime eventTime = LocalDateTime.parse(dateTime, formatter);
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(currentTime, eventTime);

        if (duration.isNegative()) {
            System.out.println("Событие уже прошло.");
            return;
        }

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        System.out.printf("Осталось: %d часов, %d минут, %d секунд%n", hours, minutes, seconds);
    }
}

/**
* 13. Вычисление количества рабочих часов
* Задача: Напишите метод, который принимает два объекта LocalDateTime,
* представляющие начало и конец рабочего дня, и возвращает количество рабочих часов (без учета выходных).
*/
class Task13 {
    public static void main(String... args) {
        LocalDateTime start = LocalDateTime.of(2023, 12, 25, 9, 0);
        LocalDateTime end = LocalDateTime.of(2023, 12, 30, 18, 0);

        int workingHours = countWorkH(start, end);
        System.out.println("Количество рабочих часов: " + workingHours);
    }

    private static int countWorkH(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Начало рабочего дня не может быть после конца рабочего дня.");
        }

        LocalTime workStart = start.toLocalTime();
        LocalTime workEnd = end.toLocalTime();
        int totalWorkingHours = 0;
        LocalDateTime current = start;

        while (current.isBefore(end) || current.isEqual(end)) {
            if (current.getDayOfWeek() != DayOfWeek.SATURDAY && current.getDayOfWeek() != DayOfWeek.SUNDAY) {
                LocalDateTime dayStart = current.toLocalDate().atTime(workStart);
                LocalDateTime dayEnd = current.toLocalDate().atTime(workEnd);

                if (dayStart.isBefore(dayEnd)) {
                    Duration duration = Duration.between(dayStart, dayEnd);
                    totalWorkingHours += duration.toHours();
                }
            }
            current = current.plusDays(1).toLocalDate().atTime(workStart);
        }

        return totalWorkingHours;
    }
}

/**
* 14. Конвертация даты в строку с учетом локали
* Задача: Создайте метод, который принимает объект LocalDate и выводит его в строковом формате с учетом локали,
* например, ru или en.
*/
class Task14 {
    public static void main(String... args) {
        dateInlocal(LocalDate.now());
    }

    private static void dateInlocal(LocalDate date) {
        Locale locale = Locale.ENGLISH;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
        System.out.println(date.format(formatter));
    }
}

/**
* 15. Определение дня недели по дате
* Задача: Напишите метод, который принимает дату и возвращает название дня недели на русском языке.
*/
class Task15 {
    public static void main(String... args) {
        System.out.println(getWeekDay(LocalDate.now()));
    }

    private static String getWeekDay(LocalDate date) {
        Map<DayOfWeek, String> week = Map.of(
                DayOfWeek.MONDAY, "Понедельник",
                DayOfWeek.TUESDAY, "Вторник",
                DayOfWeek.WEDNESDAY, "Среда",
                DayOfWeek.THURSDAY, "Четверг",
                DayOfWeek.FRIDAY, "Пятница",
                DayOfWeek.SATURDAY, "Субота",
                DayOfWeek.SUNDAY, "Воскресенье");

        return week.get(date.getDayOfWeek());
    }
}

public class Main {
    public static void main(String[] args) {
        Task1.main();
        Task2.main();
        Task3.main();
        Task4.main();
        Task5.main();
        Task6.main();
        Task7.main();
        Task8.main();
        Task9.main();
        Task10.main();
        Task11.main();
        Task12.main();
        Task13.main();
        Task14.main();
        Task15.main();
    }
}
