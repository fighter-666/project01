package com.example.java.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Class implements Iterable<Student>{

    private final List<Student> students=new ArrayList<>();

    public Class(){
        students.add(new Student("张三",18));
        students.add(new Student("李四",28));
        students.add(new Student("王五",38));
    }

    public boolean addStudent(Student student){
        return students.add(student);
    }

    public boolean removeStudent(Student student){
        return students.remove(student);
    }

    @Override
    public Iterator<Student> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Student>{
        int index = 0;

        @Override
        public boolean hasNext() {
            if (index< students.size()){
                return true;
            }
            return false;
        }

        @Override
        public Student next() {
            Student student = students.get(index);
            index++;
            return student;
        }
    }
}
