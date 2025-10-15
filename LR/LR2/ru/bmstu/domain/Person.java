package ru.bmstu.domain;

public class Person {

    private String firstName;
    private String secondName;
    private int age;
    private String phone;

    public Person(String firstName, String secondName, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public Person(String firstName, String secondName, int age, String phone) throws InvalidPhoneNumberException {
        this(firstName, secondName, age);
        this.setPhone(phone);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) throws InvalidPhoneNumberException {
        // ^ - начало строки
        // \+? - необязательный символ "+" в начале
        // [0-9]{11} - ровно 11 цифр
        // $ - конец строки
        if (phone != null && phone.matches("^\\+?[0-9]{11}$")) {
            this.phone = phone;
        } else {
            throw new InvalidPhoneNumberException("Некорректный формат номера телефона: " + phone +
                    ". Ожидается 11 цифр, возможно с '+' в начале.");
        }
    }
}
