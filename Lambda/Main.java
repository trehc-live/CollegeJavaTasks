package com.stackoverflow.Lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Задача 1: Удвоение чисел
 * Создайте лямбда-выражение, которое принимает число и возвращает его удвоенное значение.
 * Затем примените это выражение к списку чисел и выведите результат.
 */
class Task1 {
    public static void main(String... args) {
        int[] numbers = new int[]{1, 2, 3};
        Function<Integer, Integer> func = x -> x * 2;

        numbers = Arrays.stream(numbers).map(func::apply).toArray();
        Arrays.stream(numbers).forEach(System.out::println);
    }
}

/**
 * Задача 2: Фильтр положительных чисел
 * Создайте лямбда-выражение для фильтрации положительных чисел из списка.
 * Используйте его вместе с методом filter из Stream API, чтобы оставить только положительные числа в списке.
 */
class Task2 {
    public static void main(String... args) {
        int[] numbers = new int[]{-1, -2, 1, -4, 2, 3};
        Function<int[], int[]> func = x -> Arrays.stream(x).filter(num -> num >= 0).toArray();

        numbers = func.apply(numbers);
        Arrays.stream(numbers).forEach(System.out::println);
    }
}

/**
 * Задача 3: Преобразование списка строк в их длины
 * Дан список строк.
 * Используйте лямбда-выражение для преобразования каждой строки в её длину и сохраните результат
 * в новом списке.
 */
class Task3 {
    public static void main(String... args) {
        List<String> strings = Arrays.asList("1", "12", "123", "12345");
        Function<String, Integer> func = String::length;
        List<Integer> res = strings.stream().map(func).toList();

        System.out.println(res);
    }
}

/**
 * Задача 4: Поиск слов по длине
 * Дан список слов. Напишите лямбда-выражение, которое проверяет, содержит ли слово больше пяти символов.
 * Используйте его для фильтрации и создания нового списка только с длинными словами.
 */
class Task4 {
    public static void main(String... args) {
        List<String> strings = Arrays.asList("hell", "prison", "death", "suffer", "puppy");
        Predicate<String> func = x -> x.length() >= 5;
        List<String> res = strings.stream().filter(func).toList();

        System.out.println(res);
    }
}


/**
 * Задача 5: Подсчёт символов 'a'
 * Дан список слов. Напишите лямбда-выражение,
 * которое подсчитывает количество символов 'a' в каждом слове и выводит результат.
 */
class Task5 {
    public static void main(String... args) {
        List<String> strings = Arrays.asList("hella", "mama", "death", "address", "alabama");
        BiFunction<List<String>, Character, Integer> func = (x, ch) -> x
                .stream()
                .mapToInt(y -> y.length() - y.replace(String.valueOf(ch), "").length())
                .sum();

        System.out.println(func.apply(strings, 'a'));
    }
}

/**
 * Задача 6: Применение операции к каждому элементу
 * Создайте лямбда-выражение, которое выводит каждый элемент списка на консоль, умножая его на 3 перед выводом.
 */
class Task6 {
    public static void main(String... args) {
        List<Integer> strings = Arrays.asList(1, 2, 3, 4);
        Consumer<List<Integer>> func = x -> x
                .stream()
                .map(y -> y * 3)
                .forEach(System.out::println);
        func.accept(strings);
    }
}

/**
 * Задача 7: Генерация случайных чисел
 * Напишите лямбда-выражение, которое генерирует случайные числа.
 * Используйте его для создания списка из 10 случайных чисел.
 */
class Task7 {
    public static void main(String... args) {
        Supplier<Integer> func = () -> new Random().nextInt();
        List<Integer> arr = new ArrayList<>(Collections.nCopies(10, 0))
                .stream()
                .map(x -> func.get())
                .toList();
        arr.forEach(System.out::println);
    }
}

/**
 * Задача 8: Проверка на палиндром
 * Создайте лямбда-выражение, которое проверяет,
 * является ли строка палиндромом (читается одинаково слева направо и справа налево).
 * Примените его к списку строк и выведите только палиндромы.
 */
class Task8 {
    public static void main(String... args) {
        List<String> strs = Arrays.asList("Шалаш", "ФИЩиФ", "МАМА");
        Predicate<String> func = x -> {
            String cleanStr = x.replaceAll("\s+", "").toLowerCase();
            String reversedStr = new StringBuilder(cleanStr).reverse().toString();
            return cleanStr.equals(reversedStr);
        };
        strs.stream().filter(func).toList().forEach(System.out::println);
    }
}

/**
 * Задача 9: Подсчёт строк, начинающихся с определённой буквы
 * Напишите лямбда-выражение, которое считает,
 * сколько строк в списке начинаются с определённой буквы, например, 'J'.
 */
class Task9 {
    public static void main(String... args) {
        List<String> strs = Arrays.asList("Ja", "ba", "ja", "ca");
        BiFunction<List<String>, Character, Integer> func = (x, ch) -> (int) x
                .stream()
                .map(String::toLowerCase)
                .filter(y -> y.charAt(0) == Character.toLowerCase(ch))
                .count();
        System.out.println(func.apply(strs, 'J'));
    }
}

/**
 * Задача 10: Объединение строк с разделителем
 * Дан список строк. Создайте лямбда-выражение,
 * которое объединяет все строки из списка в одну строку с разделителем ", ".
 * Например, ["apple", "banana", "cherry"] должно преобразоваться в "apple, banana, cherry".
 */
class Task10 {
    public static void main(String... args) {
        List<String> strs = Arrays.asList("apple", "banana", "cherry");
        Function<List<String>, String> func = x -> x.stream().collect(Collectors.joining(", "));

        System.out.println(func.apply(strs));
    }
}


public class Main {
    public static void main(String[] args) {

    }
}
