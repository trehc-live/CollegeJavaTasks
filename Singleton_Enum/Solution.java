package com.crazyfrogfan.Singleton_Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * Задача 1.
 * Создание класса базы данных:<br>
 * Реализуйте класс, который будет представлять подключение к базе данных.
 * Класс должен быть реализован как Singleton,
 * чтобы при каждом обращении возвращался один и тот же объект.<br>
 * Требования:<br>
 * Класс должен быть Singleton.<br>
 * Метод подключения должен выводить сообщение о создании подключения.
 * Проверьте, что при создании нескольких экземпляров — они ссылаются на один и тот же объект.
 */
class ConToDB {
    private static final ConToDB conToDB = new ConToDB();

    private ConToDB() {}

    public static ConToDB getInstance() {
        return conToDB;
    }

    public void con() {
        System.out.println("сообщение о создании подключения");
    }
}

/**
 * Задача 2.
 * Логирование в системе:
 * Создайте класс для системы логирования, который реализует паттерн Singleton.
 * Класс должен сохранять все сообщения логов в список и предоставлять метод для их вывода.
 * Требования:
 * Класс должен быть Singleton.
 * Реализуйте метод для добавления сообщений в логи.
 * Реализуйте метод для вывода всех логов.
 */
class LogSystem {
    private static final LogSystem logSystem = new LogSystem();
    private static List<String> logs = new ArrayList<>();

    private LogSystem() {}

    public void addLog(String log) {
        logs.add(log);
    }

    public void printLogs() {
        for (String log : logs)
            System.out.println(log);
    }
}

/**
 * Задача 3.
 * Реализация статусов заказа:
 * Создайте Enum для статусов заказа в интернет-магазине: NEW, IN_PROGRESS, DELIVERED, CANCELLED.
 * Реализуйте класс Order, который будет хранить информацию о заказе и текущем статусе,
 * а также методы для смены статуса.
 *
 * Требования:
 *
 * Определите Enum для статусов заказа.
 * Реализуйте методы в классе Order для изменения и отображения статуса заказа.
 * Реализуйте логику проверки переходов статусов, чтобы нельзя было отменить доставленный заказ.
 */
enum Status {
    NEW, IN_PROGRESS, DELIVERED, CANCELLED;
}

class Order {
    private Status status;

    public Order(Status status) {
        this.status = status;
    }

    public void setStatus(Status status) {
        if(this.status.equals(Status.DELIVERED) && status.equals(Status.CANCELLED))
            return;
        this.status = status;
    }

    public void print() {
        System.out.println(status);
    }
}

/**
 * Задача 4.
 * Сезоны года:
 * Создайте Enum, представляющий времена года: WINTER, SPRING, SUMMER, AUTUMN.
 * Затем реализуйте функцию, которая принимает значение этого Enum и возвращает
 * соответствующее название сезона на русском языке.
 *
 * Требования:
 *
 * Определите Enum для сезонов.
 * Реализуйте метод, который принимает сезон и возвращает строку с его названием.
 */
enum Seasons {
    WINTER("зима"), SPRING("весна"), SUMMER("лето"), AUTUMN("осень");
    private final String name;

    Seasons(String name) {
        this.name = name;
    }

    public String getSeason() {
        return name;
    }
}

public class Solution {

}
