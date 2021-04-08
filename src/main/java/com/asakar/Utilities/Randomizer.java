package com.asakar.Utilities;

public class Randomizer {

    public static int getRandomInRange(int start, int end){
        java.util.Random generator = new java.util.Random();
        return start + generator.nextInt(end - start);
    }

}
