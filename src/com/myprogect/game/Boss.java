package com.myprogect.game;

public class Boss implements IGameCharacter {
    private String name;
    private int damage;
    private int healthPoints;

    public Boss() {
        name = "Гавкошмыг";
        damage = 2;
        healthPoints = 20;
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
