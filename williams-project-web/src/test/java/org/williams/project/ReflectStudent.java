package org.williams.project;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
public class ReflectStudent {
    private String name;
    private Integer age;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ReflectStudent a = new ReflectStudent();
        for (Method method : a.getClass().getMethods()) {
            if (method.getName().equals("setName")){
                method.invoke(a, "asdf");
            }else if (method.getName().equals("setAge")){
                method.invoke(a, 12);
            }
        }
        for (Method method : a.getClass().getMethods()) {
            if (method.getName().equals("getName")){
                System.out.println(method.invoke(a));
            }
        }
        System.out.println(a);
    }
}
