package br.com.ilegra.cloudnative.gustavomoura.tema8.operations;

public class Subtraction implements Operation{
    private double firstValue;
    private double secondValue;

    @Override
    public void setFirstValue(double value) {
        this.firstValue = value;
    }

    @Override
    public void setSecondValue(double value) {
        this.secondValue = value;
    }

    @Override
    public double doOperation() {
        return this.firstValue - this.secondValue;
    }

    @Override
    public String toString() {
        return firstValue + " - " + secondValue + " = " + doOperation() + "\n";
    }
}
