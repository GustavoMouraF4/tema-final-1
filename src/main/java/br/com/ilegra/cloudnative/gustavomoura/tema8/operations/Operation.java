package br.com.ilegra.cloudnative.gustavomoura.tema8.operations;

public interface Operation {
    void setFirstValue(double value);
    void setSecondValue(double value);
    double doOperation();
}
