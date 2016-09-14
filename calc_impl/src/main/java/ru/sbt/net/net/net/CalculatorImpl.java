package ru.sbt.net.net.net;

import ru.sbt.net.net.Calculator;

public class CalculatorImpl implements Calculator {
    public double calculate(Integer a, Integer b) {
        return (double) (a + b - 410 / 12);
    }
}
