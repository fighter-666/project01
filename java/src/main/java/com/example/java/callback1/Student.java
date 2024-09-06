package com.example.java.callback1;

public class Student {
    private String name = null;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class doHomeWork implements doJob{

        @Override
        public void fillBlank(int a, int b, int result) {
            System.out.println(name + "求助小红计算：" + a + "+" + b + "=" + result);
        }
    }

    public void callHelp(int a, int b) {
        new SuperCalculator().add(a, b, new doHomeWork());
    }

    public void fillBlank(int a, int b, int result) {
        System.out.println(name + "求助小红计算：" + a + "+" + b + "=" + result);
    }
}
