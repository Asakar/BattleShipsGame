package model;

public class BattleShip {

    private int size;

    public BattleShip(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "BattleShip";
    }
}
