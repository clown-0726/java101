package com.lilu.annotations;

public class Main {
    public static void main(String[] args) {
        // 业务代码
        People people1 = new People();
        people1.setId(9);

        People people2 = new People();
        people2.setName("Ming");

        String query1 = Filter.query(people1);
        System.out.println(query1);

        String query2 = Filter.query(people2);
        System.out.println(query2);
    }
}
