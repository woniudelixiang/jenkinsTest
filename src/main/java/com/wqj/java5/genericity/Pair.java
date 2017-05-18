package com.wqj.java5.genericity;

public class Pair<T>{  
    private T value;  
    public T getValue() {  
        return value;  
    }  
    public void setValue(T value) {  
        this.value = value;  
    }
    
    public boolean equals(Object obj) {
        return (this == obj);
    }
    
//    public boolean equals(T value) {  
//        return false;  
//    }
    
}