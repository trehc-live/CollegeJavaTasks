package com.stackoverflow.OOP_twenty_tasks;


import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. Класс "Человек"
 * Создайте класс Person с полями name, age, gender.
 * Реализуйте методы для вывода информации о человеке и увеличения его возраста. Добавьте метод для изменения имени.
 */
class Person{
    String name;
    short age;
    String gender;

    public Person(String name, short age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(Person person){
        this.name = person.name;
        this.age = person.age;
        this.gender = person.gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void ageIncrement(){
        age++;
    }

    public static void print(){
        System.out.println();
    }
}

/**
 * 2. Наследование: Класс "Работник" и "Менеджер"
 * Создайте класс Worker, который наследуется от класса Person,
 * и добавьте поле salary. Создайте класс Manager,
 * который наследует от Worker и добавляет поле для подчиненных сотрудников.
 */
class Worker extends Person{
    public BigDecimal salary;

    public Worker(Person person, BigDecimal salary) {
        super(person);
        this.salary = salary;
    }
    public Worker(Worker worker){
        this(worker, worker.salary);
    }
}

class Manager extends Worker{
    public List<Worker> employees;

    public Manager(Worker worker, List<Worker> employees) {
        super(worker);
    }
    public Manager(Manager manager){
        this(manager, manager.employees);
    }
}

/**
 * 3. Полиморфизм: Животные
 * Создайте интерфейс Animal с методом makeSound().
 * Реализуйте классы Dog, Cat, и Cow, которые реализуют этот интерфейс.
 * Продемонстрируйте полиморфизм на примере массива животных.
 */
interface Animal{
    void makeSound();
}

class Dog implements Animal{
    @Override
    public void makeSound() {
        System.out.println("ГАФ");
    }
}

class Cat implements Animal{
    @Override
    public void makeSound() {
        System.out.println("МУУ");
    }
}

class Cow implements Animal{
    @Override
    public void makeSound() {
        System.out.println("МЯУ");
    }
}

/**
 * 4. Абстрактный класс "Транспорт"
 * Создайте абстрактный класс Transport с абстрактным методом move().
 * Реализуйте классы Car и Bike, которые наследуются от Transport и реализуют метод move().
 */
abstract class Transport{
    abstract void move();
}

class Car extends Transport {
    @Override
    void move() {
        System.out.println("Ехала");
    }
}

class Bike extends Transport {
    @Override
    void move() {
        System.out.println("Катилась");
    }
}

/**
 * 5. Класс "Книга" и "Библиотека"
 * Создайте класс Book с полями title, author, и year.
 * Создайте класс Library, который содержит коллекцию книг и методы для добавления книг,
 * поиска по автору и году публикации.
 */
class Book{
    String title;
    String author;
    int year;

    public Book(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }
}

class Library{
    //хочу использовать Hash таблицы и буду использовать, если времени хватит. Оно несколько в памяти сожрёт, но в то-же время кратно ускорит поиск.
    List<Book> books;

    public Library(List<Book> books){
        this.books = books;
    }
    public Library(){
        this.books = new ArrayList<>();
    }

    public void addBooks(Book... books){
        this.books.addAll(Arrays.asList(books));
    }
    public void addBooks(List<Book> books){
        this.books.addAll(books);
    }

    public Set<Book> find(int year, String author){
        Set<Book> foundOnce = new HashSet<>();
        for (Book book : books)
            if(book.year == year && book.author.equals(author))
                foundOnce.add(book);
        return foundOnce;
    }

    public void printBooks(Set<Book> books){
        for (Book book : books)
            System.out.println(book.title + "\t" +
                               book.author + "\t" +
                               book.year);
    }
    public void printBooks(){
        for (Book book : books)
            System.out.println(book.title + "\t" +
                    book.author + "\t" +
                    book.year);
    }
}

/**
 * 6. Инкапсуляция: Банк
 * Создайте класс BankAccount с полями accountNumber, balance,
 * и методами для пополнения и снятия средств. Обеспечьте инкапсуляцию для безопасности данных счета.
 */
class BankAccount{
    private long accountNumber;
    private long balance;

    public BankAccount(long accountNumber, long balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void increaseBalance(long money){
        balance = balance + money > 0 ? balance + money : balance;
    }

    public void decreaseBalance(long money){
        balance = balance - money > 0 ? balance - money : balance;
    }
}

/**
 * 7. Счетчик объектов
 * Создайте класс Counter, который хранит количество созданных объектов данного класса.
 * Реализуйте статическое поле для учета количества объектов и метод для его
 * увеличения при каждом создании объекта.
 */
class Counter{
    //как вариант, можно реализовать деструктор с помощью finalize для уменьшения counter
    public static int counter = 0;

    public Counter(){
        counter++;
    }
}

/**
 * 8. Площадь фигур
 * Создайте абстрактный класс Shape с абстрактным методом getArea().
 * Реализуйте классы Circle и Rectangle,
 * которые наследуют от Shape и вычисляют площадь круга и прямоугольника соответственно.
 */
abstract class Shape{
    abstract double getArea();
}

class Circle extends Shape{
    private double r;

    public Circle(double r){
        this.r = r;
    }

    public double getArea(){
        return Math.PI * Math.pow(r, 2);
    }
}

class Rectangle extends Shape{
    private double w;
    private double h;

    public Rectangle(double w, double h){
        this.w = w;
        this.h = h;
    }

    public double getArea(){
        return w * h;
    }
}

/**
 * 9. Наследование: Животные и их движения
 * Создайте класс Animal с методом move().
 * Реализуйте классы Fish, Bird и Dog,
 * которые наследуют Animal и переопределяют метод move()
 * (рыба плавает, птица летает, собака бегает).
 */
class Animal2{
    public static void move(){
        System.out.println("куда-то движется");
    }
}

class Fish extends Animal2{
    public static void move() {
        System.out.println("куда-то плывёт");
    }
}

class Bird extends Animal2{
    public static void move() {
        System.out.println("куда-то летит");
    }
}

class Dog2 extends Animal2{
    public static void move() {
        System.out.println("куда-то бежит");
    }
}

/**
 * 10. Работа с коллекциями: Университет
 * Создайте класс Student с полями name, group, grade.
 * Создайте класс University, который содержит список студентов и методы для добавления студентов,
 * сортировки по имени и фильтрации по успеваемости.
 */
class Student{
    public String name;
    public String group;
    public String grade;

    public Student(String name, String group, String grade){
        this.name = name;
        this.group = group;
        this.grade = grade;
    }
}

class University{
    List<Student> students;

    public University(){
        students = new ArrayList<>();
    }
    public University(List<Student> students) {
        this.students = students;
    }

    public void addStudents(Student... students){
        this.students.addAll(Arrays.asList(students));
    }
    public void addStudents(List<Student> students){
        this.students.addAll(students);
    }

    public void sortByName() {
        students.sort(Comparator.comparing(s -> s.name));
    }

    public List<Student> filterByGrade(String grade){
        return students.stream()
                .filter(student -> student.grade.equals(grade))
                .collect(Collectors.toList());
    }
}

/**
 * 11. Класс "Магазин"
 * Реализуйте класс Product с полями name, price, и quantity.
 * Создайте класс Store, который содержит список продуктов и методы для добавления,
 * удаления и поиска товаров по имени.
 */
class Product{
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class Store{
    HashMap<String, Product> products;

    public Store(){
        products = new HashMap<>();
    }
    public Store(HashMap<String, Product> products){
        this.products = products;
    }

    public void addProducts(Product... products){
        for(Product product : products)
            this.products.put(product.name, product);
    }

    public void deleteProductByName(String name){
        products.remove(name);
    }

    public Product searchByName(String name){
        return products.get(name);
    }
}

/**
 * 12. Интерфейс "Платежная система"
 * Создайте интерфейс PaymentSystem с методами pay() и refund().
 * Реализуйте классы CreditCard и PayPal, которые реализуют этот интерфейс.
 */
interface PaymentSystem{
    void pay();
    void refund();
}

class CreditCard implements PaymentSystem{
    @Override
    public void pay() {
        System.out.println("тут очень интересная реализация для кредитки");
    }

    @Override
    public void refund() {
        System.out.println("тут очень интересная реализация для кредитки");
    }
}

class PayPal implements PaymentSystem{
    @Override
    public void pay() {
        System.out.println("тут очень интересная реализация для пей пала");
    }

    @Override
    public void refund() {
        System.out.println("тут очень интересная реализация для пей пала");
    }
}

/**
 * 13. Генерация уникальных идентификаторов
 * Создайте класс UniqueID,
 * который генерирует уникальные идентификаторы для объектов каждого созданного класса.
 * Реализуйте этот функционал через статическое поле и метод.
 */
class UniqueID{
    public static int ID = 0;
    public int id;

    public UniqueID(){
        ID++;
        id = ID;
    }
}

/**
 * 14. Класс "Точка" и "Прямоугольник"
 * Создайте класс Point с координатами x и y.
 * Реализуйте класс Rectangle, который содержит две точки (левая верхняя и правая нижняя).
 * Реализуйте метод для вычисления площади прямоугольника.
 */
class Point{
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Rectangle2{
    Point lt;
    Point rb;

    public Rectangle2(Point lt, Point rb) {
        this.lt = lt;
        this.rb = rb;
    }

    public double getArea(){
        return (rb.x - lt.x) * (lt.y - rb.y);
    }
}

/**
 * 15. Комплексные числа
 * Создайте класс ComplexNumber с полями для действительной и мнимой частей.
 * Реализуйте методы для сложения, вычитания, умножения и деления комплексных чисел.
 */
class ComplexNumber{
    double real;
    double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    //пока сложна, потом сделаю
}

/**
 * 16. Перегрузка операторов: Матрица
 * Создайте класс Matrix, представляющий двумерную матрицу.
 * Реализуйте методы для сложения и умножения матриц. Продемонстрируйте перегрузку методов.
 */
class Matrix{//я понимаю, что методы solid и прочее, я разделю это по отдельным классам, но если останется время
    double[][] matrix;
    int rows;
    int columns;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    public Matrix(Matrix matrix){
        this.matrix = matrix.matrix;
        rows = matrix.rows;
        columns = matrix.columns;
    }

    public static Matrix sum(Matrix... matrices) {
        Matrix result = new Matrix(matrices[0].rows, matrices[0].columns);

        for (int i = 0; i < matrices[0].rows; i++)
            Arrays.fill(result.matrix[i], 0);

        for(Matrix matrix : matrices)
            sum(result, matrix);

        return result;
    }

    public static Matrix sum(Matrix first, Matrix second){
        if (first.rows != second.rows || first.columns != second.columns)
            throw new IllegalArgumentException();

        for (int i = 0; i < first.rows; i++)
            for (int j = 0; j < first.columns; j++)
                first.matrix[i][j] += second.matrix[i][j];

        return first;
    }

    public static Matrix multiply(Matrix first, Matrix second) {
        if (first.columns != second.rows)
            throw new IllegalArgumentException();

        Matrix result = new Matrix(first.rows, second.columns);

        for (int i = 0; i < first.rows; i++)
            for (int j = 0; j < second.columns; j++) {
                double sum = 0;
                for (int n = 0; n < first.columns; n++)
                    sum += first.matrix[i][n] * second.matrix[n][j];
                result.matrix[i][j] = sum;
            }

        return result;
    }
}

/**
 * 17. Создание игры с использованием ООП
 * Реализуйте классы для небольшой текстовой игры, такие как Player,
 * Enemy, Weapon, с полями и методами, описывающими их поведение.
 */
class Player{
    private String name;
    private short MentalPoints;
    private Weapon weapon;

    public Player(String name, short MentalPoints, Weapon weapon) {
        this.name = name;
        this.MentalPoints = MentalPoints;
        this.weapon = weapon;
    }

    public void takeEmotionalDamage(short damage){
        setMentalPoints((short) (getMentalPoints() - damage));
    }

    public void makeAFriend(Enemy enemy){
        if (enemy.isUnfriendly()) {
            System.out.println(name + " делает дружелюбный выпад " + enemy.getName() + " с помощью " + weapon.getName());
            enemy.takeKindness(weapon.getFriendability());
            System.out.println(enemy.getName() + " увеличил влечение на " + weapon.getFriendability() + ". Текущая неприязнь: " + enemy.isUnfriendly());
        } else
            System.out.println(enemy.getName() + " уже ваш друг.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getMentalPoints() {
        return MentalPoints;
    }

    public void setMentalPoints(short MentalPoints) {
        this.MentalPoints = MentalPoints;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

class Enemy{
    private final String name;
    private short unfriendliness;

    public Enemy(String name, short unfriendliness) {
        this.name = name;
        this.unfriendliness = unfriendliness;
    }

    public void takeKindness(short kindness){
        setUnfriendliness((short) (getUnfriendliness() - kindness));
    }

    public boolean isUnfriendly(){
        return unfriendliness > 0;
    }

    public String getName() {
        return name;
    }

    public short getUnfriendliness() {
        return unfriendliness;
    }

    public void setUnfriendliness(short unfriendliness) {
        this.unfriendliness = unfriendliness;
    }
}

class Weapon{
    private String name;
    private short friendability;

    public Weapon(String name, short friendability) {
        this.name = name;
        this.friendability = friendability;
    }

    public String getName() {
        return name;
    }

    public short getFriendability() {
        return friendability;
    }
}

/**
 * 18. Автоматизированная система заказов
 * Создайте классы Order, Customer, и Product.
 * Реализуйте систему, где можно добавлять заказы,
 * отображать общую стоимость заказа и просматривать историю заказов.
 */
class Order{
    private String info;
    private List<Product2> products;
    private double cost;

    public Order(String info, List<Product2> products) {
        this.info = info;
        this.products = products;
        calculateCost();
    }

    public String getInfo() {
        return info;
    }

    public double getCost(){
        return cost;
    }

    public void calculateCost(){
        for(Product2 product : products)
            cost += product.getPrice();
    }

    public static void print(Order order){
        for(Product2 product : order.products)
            Product2.print(product);
        System.out.println("Общая стоимость: " + order.cost);
    }
}

class Customer{
    List<Order> orderHistory;

    public Customer(){
        orderHistory = new ArrayList<>();
    }

    public void orderSmth(Order order){
        orderHistory.add(order);
    }

    public void printHistory(){
        for (Order order : orderHistory)
            Order.print(order);
    }
}

class Product2{
    private String name;
    private double price;

    public Product2(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void print(Product2 product){
        System.out.println(product.name + "\t" + product.price);
    }
}

/**
 * 19. Наследование: Электроника
 * Создайте класс Device с полем brand и методами turnOn() и turnOff().
 * Реализуйте классы Smartphone и Laptop, которые наследуют от Device и добавляют уникальные методы,
 * например, takePhoto() для смартфона.
 */
class Device{
    String brand;

    public Device(String brand){
        this.brand = brand;
    }

    public static void turnOn(){}

    public static void turnOff(){}
}

class Smartphone extends Device{
    public Smartphone(String brand) {
        super(brand);
    }

    public static void takePhoto(){}
}

class laptop extends Device{
    public laptop(String brand) {
        super(brand);
    }

    public static void pressKey(){}
}

public class Solution {
    public static void main(String[] args) {

    }
}