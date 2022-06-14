package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("HOLA DESDE MI IMPLEMENTACION DEL BEAN");
    }
}
