package com.example.demo;

import lombok.Getter;

public class Cat {
    @Getter private String name;
    @Getter private String origin;
    @Getter private String size;
    @Getter private float age;
    @Getter private float pounds;

    public Cat(String name, String origin, String size, float age, float pounds) {
        this.name = name;
        this.origin = origin;
        this.size = size;
        this.age = age;
        this.pounds = pounds;
    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        Cat cat = (Cat) obj;
        if(!name.equals(cat.name)) return false;
        if(!origin.equals(cat.origin)) return false;
        if(!size.equals(cat.size)) return false;
        if(age != cat.age) return false;
        return pounds == cat.pounds;

    }
    @Override
    public int hashCode() {
        int result = (int) ((int)pounds ^ ((int)pounds >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + origin.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + (int) age;
        return result;
    }
}