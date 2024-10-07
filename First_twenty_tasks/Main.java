package com.stackoverflow.First_twenty_tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.zip.DataFormatException;

class cringe{

    /**
     *1. Функция для нахождения максимума
     *Напишите функцию, которая принимает два числа и возвращает максимальное из них.
     *Если числа равны, выбрасывайте исключение с сообщением об ошибке.
     */
    public static BiFunction<Integer, Integer, Integer> max = (a, b)->{
        return a > b ? a : b;
    };

    /**
     * 2. Калькулятор деления
     * Создайте функцию для деления двух чисел.
     * Если делитель равен нулю, выбрасывайте ArithmeticException с сообщением о недопустимости деления на ноль.
     */
    public static BiFunction<Integer, Integer, Integer> divide = (a, b)->{
        try{
            return a / b;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("недопустимость деления на ноль");
        }
    };

    /**
     * 3. Конвертер строк в числа
     * Напишите функцию, которая принимает строку и пытается конвертировать её в целое число.
     * Если строка не может быть конвертирована, выбрасывайте NumberFormatException.
     */
    public static int convertToInt(String x) throws NumberFormatException{
        return Integer.parseInt(x);
    }

    /**
     * 4. Проверка возраста
     * Создайте функцию, которая принимает возраст и выбрасывает IllegalArgumentException,
     * если возраст меньше нуля или больше 150.
     */
    public static void ageHandler(int age){
        if (age < 0 || age > 150)
            throw new IllegalArgumentException();
    }

    /**
     * 5. Нахождение корня
     * Реализуйте функцию, которая находит корень из числа.
     * Если число отрицательное, выбрасывайте IllegalArgumentException.
     */
    public static double root(double x){
        if(x < 0)
            throw new IllegalArgumentException();
        return Math.sqrt(x);
    }

    /**
     * 6. Факториал
     * Напишите функцию, которая вычисляет факториал числа.
     * Если число отрицательное, выбрасывайте исключение.
     */
    public static int Factorial(int x) {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result = result * i;
        }
        return result;
    }

    /**
     * 7. Проверка массива на нули
     * Создайте функцию, которая проверяет массив на наличие нулей.
     * Если в массиве есть нули, выбрасывайте исключение.
     */
    public static void isThereZero(int[] x){
        for(int n : x)
            if(n == 0)
                throw new RuntimeException("нолики");
    }

    /**
     * 8. Калькулятор возведения в степень
     * Реализуйте функцию, которая возводит число в степень. Если степень отрицательная, выбрасывайте исключение.
     */
    public static double power(int x, int n){
        if(n < 0)
            throw new RuntimeException("не оно");
        return Math.pow(x, n);
    }

    /**
     * 9. Обрезка строки
     * Напишите функцию, которая принимает строку и число символов.
     * Функция должна возвращать строку, обрезанную до указанного числа символов.
     * Если число символов больше длины строки, выбрасывайте исключение.
     */
    public static String cut(String x, int n){
        if(n > x.length())
            throw new RuntimeException();
        return x.substring(0, n);
    }

    /**
     * 10. Поиск элемента в массиве
     * Напишите функцию, которая ищет элемент в массиве.
     * Если элемент не найден, выбрасывайте исключение с сообщением об ошибке.
     */
    public static void find(int[] x, int elem){
        for (int n : x){
            if(n == elem)
                System.out.println("found");
        }
        throw new RuntimeException("сообщение об ошибке");
    }

    /**
     * 11. Конвертация в двоичную систему
     * Создайте функцию, которая конвертирует целое число в двоичную строку.
     * Если число отрицательное, выбрасывайте исключение.
     */
    public static String binaryThing(int x){
        if(x < 0)
            throw new RuntimeException("число отрицательное");
        return Integer.toBinaryString(x);
    }

    /**
     * 12. Проверка делимости
     * Реализуйте функцию, которая принимает два числа и проверяет, делится ли первое число на второе.
     * Если второе число равно нулю, выбрасывайте ArithmeticException.
     */
    public static boolean dividableThing(int a, int b) throws ArithmeticException{
        return a % b == 0;
    }

    /**
     * 13. Чтение элемента списка
     * Напишите функцию, которая возвращает элемент списка по индексу.
     * Если индекс выходит за пределы списка, выбрасывайте IndexOutOfBoundsException.
     */
    public static int findByIndex(List<Integer> x, int index){
        if(index > x.size())
            throw new IndexOutOfBoundsException("bye bye");
        return x.get(index);
    }

    /**
     * 14. Парольная проверка
     * Создайте функцию для проверки сложности пароля.
     * Если пароль содержит менее 8 символов, выбрасывайте исключение WeakPasswordException.
     */
    public static void checkPassword(String pass){
        if(pass.length() < 8)
            throw new WeakPasswordException();
    }

    /**
     * 15. Проверка даты
     * Напишите функцию, которая проверяет, является ли строка корректной датой в формате "dd.MM.yyyy".
     * Если формат неверен, выбрасывайте DateTimeParseException.
     */
    public static void checkDate(String date){
        try{
            new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e){
            throw new DateTimeParseException("bye bye", date, 0);
        }
    }

    /**
     * 16. Конкатенация строк
     * Реализуйте функцию, которая объединяет две строки.
     * Если одна из строк равна null, выбрасывайте NullPointerException.
     */
    public static String concatStr(String a, String b){
        if(a == null || b == null)
            throw new NullPointerException();
        return a.concat(b);
    }

    /**
     * 17. Остаток от деления
     * Создайте функцию, которая возвращает остаток от деления двух чисел.
     * Если второе число равно нулю, выбрасывайте исключение.
     */
    public static int devideThingLeftReborn(int a, int b) throws ArithmeticException{
        return a % b;
    }

    /**
     * 18. Квадратный корень
     * Реализуйте функцию, которая находит квадратный корень числа.
     * Если число отрицательное, выбрасывайте исключение.
     */
    public static double sqrt(int x){
        return root(x);
    }

    /**
     * 19. Конвертер температуры
     * Напишите функцию, которая переводит температуру из Цельсия в Фаренгейт.
     * Если температура меньше абсолютного нуля, выбрасывайте исключение.
     */
    public static double toFaringate(double cel){
        return (cel * 9/5) + 32;
    }

    /**
     * 20. Проверка строки на пустоту
     * Создайте функцию, которая проверяет, является ли строка пустой или null.
     * Если строка пустая или равна null, выбрасывайте исключение.
     */
    public static void finally_yahoo(String str){
        if(str.isEmpty()){
            throw new NullPointerException();
        }
    }
}

class WeakPasswordException extends RuntimeException{}

public class Main {
    public static void main(String[] args) {

    }
}