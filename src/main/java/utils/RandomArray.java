package utils;

import java.util.Random;

public class RandomArray {

    public static void randomiseIntegerArray(Random randomiser, int[] array, int arraySize, int lowerBound, int upperBound) {
        for(int i=0; i<arraySize; i++) {
            array[i] = randomiser.nextInt(lowerBound,upperBound);
        }
    }
}
