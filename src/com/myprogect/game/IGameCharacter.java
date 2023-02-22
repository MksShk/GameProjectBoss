package com.myprogect.game;

public interface IGameCharacter {
    void hit(IGameCharacter rival);
    int sayHealthPointStatus();
    String sayName();
    void receiveDamage(int damage);
}
