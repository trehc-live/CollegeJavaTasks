package com.stackoverflow.Stream;

import java.util.Arrays;
import java.util.List;

public class Main {
    /**
     * 1)Создайте список чисел.
     * Отфильтруйте только четные числа, отсортируйте их по возрастанию и найдите их сумму.
     * 2)Создайте список строк, отфильтруйте строки длиной более 5 символов,
     * преобразуйте их в нижний регистр и соберите в новую коллекцию
     */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(7, 4, 3, 7, 8, 0);
        int hi = numbers.stream().filter(a -> a % 2 == 0).sorted().mapToInt(Integer::intValue).sum();
        System.out.println(hi);

        List<String> strings = Arrays.asList("Здарова, тип, я сижу тут, думаю чут чуТ", "java.util.stream.ReferencePipeline$3@58372a00", "GOODNITE");
        List<String> bye = strings.stream().filter(string -> string.length() > 5).map(String::toLowerCase).toList();
        System.out.println(bye);
    }
}
