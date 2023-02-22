package com.myprogect.game;

public class FightManager {
    private IGameCharacter c1, c2, battleWinner;
    private Result result;
    private boolean isBattleRun;

    FightManager() {
        clearBattleArea();
    }

    public class Result {
       private String message;

        Result(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private void clearBattleArea() {
        c1 = null;
        c2 = null;
        battleWinner = null;
        result = new Result("Сражение ещё не началось");
    }

    public void startNewBattle(IGameCharacter c1, IGameCharacter c2) {
        clearBattleArea();
        this.c1 = c1;
        this.c2 = c2;
        isBattleRun = true;
        result = new Result("Сражение началось");
    }

    public void stopBattle() {
        isBattleRun = false;
    }

    public void runBattleRound() {
        if (!isBattleRun) {
            result = new Result("Поле сражения пусто");
            return;
        }
        c1.hit(c2);
        result = new Result(c1.sayName() + " стукнул по лбу " + c2.sayName() + " да так, что у второго осталось ХП: " + c2.sayHealthPointStatus());
        calculateHitResult();
        if (!isBattleRun) {
            return;
        }
        c2.hit(c1);
        result = new Result(c2.sayName() + " усиленно пнул " + c1.sayName() + " да так, что у второго осталось ХП: " + c1.sayHealthPointStatus());
        calculateHitResult();
    }

    private void calculateHitResult() {
        if (c2.sayHealthPointStatus() == 0) {
            battleWinner = c1;
        }
        if (c1.sayHealthPointStatus() == 0) {
            battleWinner = c2;
        }
        if (battleWinner != null) {
            stopBattle();
            result = new Result(battleWinner.sayName() + " размазал своего противника");
        }
    }

    public boolean getBattleStatus() {
        return isBattleRun;
    }

    public IGameCharacter getBattleWinner() {
        return battleWinner;
    }

    public Result getBattleResult() {
        return result;
    }

    public IGameCharacter getC2 () {
        return c2;
    }
    public IGameCharacter getC1 () {
        return c1;
    }
}
