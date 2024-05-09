package org.example.spring.entity;

public class Student {
    private int id;
    private String name;
    private int age;
    private int gender;
    private String address;
    private String className;
    private int is_daleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getIs_daleted() {
        return is_daleted;
    }

    public void setIs_daleted(int is_daleted) {
        this.is_daleted = is_daleted;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", className='" + className + '\'' +
                ", is_daleted=" + is_daleted +
                '}';
    }
}

