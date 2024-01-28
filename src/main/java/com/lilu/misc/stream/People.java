package com.lilu.misc.stream;

public class People {
    public String name;
    public Integer age;
    public String desc;
    public String title;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public People(String name, Integer age, String desc, String title) {
        this.name = name;
        this.age = age;
        this.desc = desc;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
