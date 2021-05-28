package br.com.ilegra.cloudnative.gustavomoura.tema8.operations;

public class Division implements Operation{
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
        if (secondValue > 0){
            return this.firstValue / this.secondValue;
        } else {
            throw new ArithmeticException("Division by 0!!");
        }
    }

    @Override
    public String toString() {
        return firstValue + " / " + secondValue + " = " + doOperation() + "\n";
    }
}
