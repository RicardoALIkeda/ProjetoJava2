package com.dei.demo;

import java.util.ArrayList;
import java.util.List;

public class DemoManager {
    private List<Demo> demos;
    public DemoManager() {
        this.demos = new ArrayList<>();
        this.demos.add(new Demo("Modelo 1", "Marca 1"));
        this.demos.add(new Demo("Modelo 2", "Marca 2"));
    }
    public List<Demo> getDemos() {
        return demos;
    }
    
}
