package com.ag_automation_demo.utility;

import java.util.Random;

/**
 * Commonly used utility methods
 */

public class Utility
{
    public static boolean isNullOrEmpty(String s)
    {
        return (s == null || s.isEmpty());
    }

    public static int generateRandomInteger(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
