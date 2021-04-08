package com.asakar.model;

public class Ship {

    private int size;
    private String name;
    private int damage;

    public Ship(int size, String name) {
        this.size = size;
        this.name = name;
        damage = 0;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void incrementDamage() {
        this.damage++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return getName().substring(0,1);
    }
}
