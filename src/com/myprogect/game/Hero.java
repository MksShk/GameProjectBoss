package com.myprogect.game;

public class Hero implements IGameCharacter {
    private String name;
    private int damage;
    private int healthPoints;

    public Hero() {
        this("Герой");
    }

    public Hero(String name) {
        this.name = name;
        damage = 1;
        healthPoints = 10;
    }

    @Override
    public void hit(IGameCharacter rival) {
        rival.receiveDamage(damage);
    }

    @Override
    public int sayHealthPointStatus() {
        return healthPoints;
    }

    @Override
    public String sayName() {
        return name;
    }

    @Override
    public void receiveDamage(int damage) {
        if (damage < 0) damage = 0;
        healthPoints -= damage;
        if (healthPoints < 0) healthPoints = 0;
    }
}
