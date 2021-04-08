package com.asakar.start;

public enum Rows {

    A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10);

    private int row;

    Rows(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }
}
