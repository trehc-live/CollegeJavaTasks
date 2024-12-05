package com.stackoverflow.Thread;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Задача 1.
 * Условие:
 * Напишите программу, в которой:
 * Есть общий счётчик (Counter).
 * 5 потоков одновременно увеличивают его значение на 1 в цикле 1000 раз каждый.
 * Цель:
 * Используйте synchronized или ReentrantLock, чтобы обеспечить корректное значение счётчика.
 */
class Task1 {
    private abstract static class Counter {
        protected int counter;

        abstract public void increment();

        public int getCounter() {
            return counter;
        }
    }

    private static class CounterSynchronized extends Counter {
        @Override
        synchronized public void increment() {
            counter++;
        }
    }

    public static void main(String... args) throws Exception {
        final int threads = 5;
        CounterSynchronized c1 = new CounterSynchronized();
        try (ExecutorService executor = Executors.newFixedThreadPool(threads)) {
            for (int i = 0; i < threads; i++)
                executor.submit(() -> {
                    for (int j = 0; j < 1000; j++)
                        c1.increment();
                });
        }
        Thread.sleep(750);
        System.out.println(c1.getCounter());
    }
}

/**
 * Задача 2: Генерация последовательности чисел
 * Условие:
 * Создайте программу, которая:
 * Имеет общий список чисел.
 * 10 потоков одновременно добавляют в этот список числа от 1 до 100.
 * Используйте потокобезопасную коллекцию, например, CopyOnWriteArrayList или ConcurrentLinkedQueue.
 * Цель:
 * Обеспечить потокобезопасное добавление чисел в общий список.
 */
class Task2 {
    public static void main(String... args) throws Exception {
        final int threads = 10;
        ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
        try (ExecutorService executor = Executors.newFixedThreadPool(threads)) {
            for (int i = 0; i < threads; i++)
                executor.submit(() -> {
                    for (int j = 1; j < 100; j++)
                        list.add(j);
                });
        }
        Thread.sleep(750);
        System.out.println(list);
    }
}

/**
 * Задача 3: Распределение задач с использованием пула потоков
 * Условие:
 * Напишите программу, которая:
 * Создаёт пул из 4 потоков (ExecutorService).
 * Выполняет 20 задач, каждая из которых просто выводит имя потока и номер задачи.
 * Цель:
 * Использовать пул потоков для распределения задач между потоками.
 */
class Task3 {
    public static void main(String... args) {
        final int threads = 4;
        try (ExecutorService executor = Executors.newFixedThreadPool(threads)) {
            for (int i = 1; i <= 20; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Задача : " + taskId + " выполняются потоком : " + Thread.currentThread().getName());
                });
            }
        }
    }
}

/**
 * Задача 4: Симуляция работы банка
 * Условие:
 * Создайте программу для работы банка, где:
 * Есть несколько аккаунтов с начальным балансом.
 * Несколько потоков выполняют операции перевода денег между аккаунтами.
 * Операции перевода должны быть потокобезопасными.
 * Цель:
 * Реализуйте потокобезопасную операцию перевода с использованием synchronized или ReentrantLock.
 */
class Task4 {
    private static class Account {
        private static int ID = 0;
        private int id;
        private int balance;

        public Account(int balance) {
            ID++;
            this.id = ID;
            this.balance = balance;
        }

        public int getId() {
            return id;
        }

        synchronized public int getBalance() {
            return balance;
        }

        synchronized public void setBalance(int balance) {
            this.balance = balance;
        }
    }

    private static class Bank {
        private List<Account> accounts;

        public Bank() {
            accounts = new ArrayList<>();
        }

        public Bank(Account... accounts) {
            this.accounts = Arrays.stream(accounts).toList();
        }

        private boolean validateTransaction(Account from, Account to, int moneyAmount) {
            return from.getBalance() >= moneyAmount && accounts.contains(from) && accounts.contains(to);
        }

        public boolean transferMoney(Account from, Account to, int moneyAmount) {
            if (!validateTransaction(from, to, moneyAmount))
                return false;
            from.setBalance(from.getBalance() - moneyAmount);
            to.setBalance(to.getBalance() + moneyAmount);
            return true;
        }

        public Account getAccount(int id) {
            return accounts.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        }
    }

    public static void main(String... args) throws Exception {
        Bank bank = new Bank(new Account(1000), new Account(1000));
        Runnable task1 = () -> {
            synchronized (bank) {
                bank.transferMoney(bank.getAccount(1), bank.getAccount(2), 100);
            }
        };
        Runnable task2 = () -> {
            synchronized (bank) {
                bank.transferMoney(bank.getAccount(2), bank.getAccount(1), 200);
            }
        };
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);


        thread1.start();
        thread2.start();

        Thread.sleep(750);
        System.out.println("1: " + bank.getAccount(1).getBalance());
        System.out.println("2: " + bank.getAccount(2).getBalance());
    }
}

/**
 * Задача 5: Барьер синхронизации
 * Условие:
 * Используйте CyclicBarrier для создания программы, где:
 * 5 потоков выполняют свои задачи.
 * Когда все потоки завершают свои задачи, они ждут друг друга, чтобы перейти к следующей фазе работы.
 * Цель:
 * Изучить использование CyclicBarrier для синхронизации работы потоков.
 */
class Task5 {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(5, new TasksComplete());

    private static class TasksComplete implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Пропустил ТАСКИ");
            } catch (InterruptedException e) {
            }
        }
    }

    private static class Task implements Runnable {
        int taskNumber;

        public Task(int taskNumber) {
            this.taskNumber = taskNumber;
        }


        @Override
        public void run() {
            try {
                System.out.printf("ТАСК №%d подошёл к барьеру.\n", taskNumber);
                BARRIER.await();
                System.out.printf("ТАСК №%d продолжил движение.\n", taskNumber);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i)).start();
            Thread.sleep(400);
        }
    }
}

/**
 * Задача 6: Ограниченный доступ к ресурсу
 * Условие:
 * Создайте программу, где:
 * Есть ресурс, доступный одновременно только для двух потоков.
 * Используйте Semaphore для ограничения доступа.
 * Цель:
 * Ограничить количество потоков, которые одновременно получают доступ к ресурсу.
 */
class Task6 {
    private static final int PERMITS_MAXIMUM = 2;
    private static final boolean[] PERMITS = new boolean[PERMITS_MAXIMUM];
    private static final Semaphore SEMAPHORE = new Semaphore(PERMITS_MAXIMUM, true);

    private static class Request implements Runnable {
        private int requestNumber;

        public Request(int requestNumber) {
            this.requestNumber = requestNumber;
        }

        @Override
        public void run() {
            System.out.printf("№%d Запросил доступ.\n", requestNumber);
            try {
                SEMAPHORE.acquire();

                int permitID = -1;

                synchronized (PERMITS) {
                    for (int i = 0; i < PERMITS_MAXIMUM; i++)
                        if (!PERMITS[i]) {
                            PERMITS[i] = true;
                            permitID = i;
                            System.out.printf("№%d подключился к свободному доступу %d.\n", requestNumber, permitID);
                            break;
                        }
                }

                Thread.sleep(5000);

                synchronized (PERMITS) {
                    PERMITS[permitID] = false;
                }

                SEMAPHORE.release();
                System.out.printf("№%d отключился.\n", requestNumber);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String... args) throws Exception {
        for (int i = 1; i <= 3; i++) {
            new Thread(new Request(i)).start();
            Thread.sleep(400);
        }
    }
}

/**
 * Задача 7: Обработка результатов задач
 * Условие:
 * Создайте программу, где:
 * 10 потоков выполняют расчёт (например, вычисление факториала числа).
 * Каждый поток возвращает результат через Callable и Future.
 * Цель:
 * Изучить механизм возврата результатов из потоков с использованием Callable и Future.
 */
class Task7 {
    private static long getFactorial(int f) {
        long result = 1;
        for (int i = 1; i <= f; i++)
            result = result * i;
        return result;
    }

    public static void main(String... args) {
        final int THREADS = 10;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<Long>[] futures = new Future[THREADS];
        for (int i = 0; i < THREADS; i++) {
            final int number = i;
            futures[i] = executor.submit(new Callable<Long>() {
                @Override
                public Long call() {
                    return getFactorial(number);
                }
            });
        }
        executor.shutdown();
        for (int i = 0; i < futures.length; i++) {
            try {
                Long result = futures[i].get();
                System.out.println("Факториал " + (i) + " = " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Задача 8: Симуляция производственной линии
 * Условие:
 * Создайте программу, где:
 * Один поток выполняет "производство" данных и добавляет их в BlockingQueue.
 * Другой поток забирает данные из очереди и "обрабатывает" их (например, выводит в консоль).
 * Цель:
 * Используйте BlockingQueue для организации взаимодействия между потоками.
 */
class Task8 {
    private static final int QUEUE_CAPACITY = 10;
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private static final int willBeProduced = 20;
    private static final int willBeConsumed = 20;

    private static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < willBeProduced; i++) {
                    System.out.println("Producing: " + i);
                    queue.put(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Producer interrupted");
            }
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < willBeConsumed; i++) {
                    Integer value = queue.take();
                    System.out.println("Consuming: " + value);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Consumer interrupted");
            }
        }
    }

    public static void main(String... args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }
}

/**
 * Задача 9: Многопоточная сортировка
 * Условие:
 * Реализуйте программу, которая:
 * Делит массив чисел на части.
 * Каждая часть сортируется в отдельном потоке.
 * Затем отсортированные части объединяются в один массив.
 * Цель:
 * Научиться использовать многопоточность для параллельной обработки данных.
 */
class Task9 {
    public static void main(String... args) throws Exception {
        int[] array = {5, 3, 2, 8, 1, 4, 7, 6, 9, 0};
        int numberOfParts = 3;
        int[][] parts = splitArray(array, numberOfParts);

        ExecutorService executor = Executors.newFixedThreadPool(numberOfParts);
        Future<int[]>[] futures = new Future[numberOfParts];

        for (int i = 0; i < numberOfParts; i++) {
            final int[] part = parts[i];
            futures[i] = executor.submit(new Callable<int[]>() {
                @Override
                public int[] call() {
                    Arrays.sort(part);
                    return part;
                }
            });
        }

        int[][] sortedParts = new int[numberOfParts][];
        for (int i = 0; i < numberOfParts; i++)
            sortedParts[i] = futures[i].get();

        int[] sortedArray = mergeSortedArrays(sortedParts);
        executor.shutdown();
        System.out.println(Arrays.toString(sortedArray));
    }

    private static int[][] splitArray(int[] array, int numberOfParts) {
        int partSize = (int) Math.ceil((double) array.length / numberOfParts);
        int[][] parts = new int[numberOfParts][];

        for (int i = 0; i < numberOfParts; i++) {
            int start = i * partSize;
            int end = Math.min(array.length, start + partSize);
            parts[i] = Arrays.copyOfRange(array, start, end);
        }

        return parts;
    }

    private static int[] mergeSortedArrays(int[][] arrays) {
        int totalLength = 0;
        for (int[] array : arrays)
            totalLength += array.length;

        int[] result = new int[totalLength];
        int index = 0;

        for (int[] array : arrays)
            for (int num : array)
                result[index++] = num;

        Arrays.sort(result);
        return result;
    }
}

/**
 * Задача 10: Обед философов (*)
 * Условие:
 * Реализуйте программу, где:
 * Пять философов сидят за круглым столом.
 * Каждый философ ест, если у него есть обе вилки (левая и правая).
 * Вилки являются общими для двух соседних философов.
 * Цель:
 * Избежать взаимной блокировки (deadlock) при использовании общих ресурсов.
 */
class Task10 {
    static class Fork extends ReentrantLock {
    }

    static class Philosopher extends Thread {
        private final Fork leftFork;
        private final Fork rightFork;
        private final int id;

        public Philosopher(int id, Fork leftFork, Fork rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run() {
            while (true) try {
                think();
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void think() throws InterruptedException {
            System.out.println("Philosopher " + id + " thinking.");
            Thread.sleep((long) (Math.random() * 1000));
        }

        private void eat() throws InterruptedException {
            leftFork.lock();
            rightFork.lock();

            System.out.println("Philosopher " + id + " eating.");
            Thread.sleep((long) (Math.random() * 1000));

            leftFork.unlock();
            rightFork.unlock();
        }
    }

    public static void main(String... args) {
        final int NUM_PHILOSOPHERS = 5;
        Fork[] forks = new Fork[NUM_PHILOSOPHERS];
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % NUM_PHILOSOPHERS];
            philosophers[i] = new Philosopher(i, leftFork, rightFork);
            philosophers[i].start();
        }
    }
}

/**
 * Задача 12: Таймер с многопоточностью
 * Условие:
 * Создайте программу, где:
 * Один поток запускает таймер, который каждую секунду выводит время.
 * Второй поток через 10 секунд останавливает таймер.
 * Цель:
 * Научиться управлять работой потоков.
 */
class Task12 {
    public static void main(String[] args) {
        ScheduledExecutorService executor1 = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
        executor1.scheduleAtFixedRate(() -> System.out.println(new Date()), 0, 1, TimeUnit.SECONDS);
        executor2.schedule(() -> {
            executor1.shutdownNow();
            executor2.shutdownNow();
        }, 10, TimeUnit.SECONDS);
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
