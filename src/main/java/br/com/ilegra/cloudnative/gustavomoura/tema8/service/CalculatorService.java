package br.com.ilegra.cloudnative.gustavomoura.tema8.service;

import br.com.ilegra.cloudnative.gustavomoura.tema8.operations.Operation;
import br.com.ilegra.cloudnative.gustavomoura.tema8.operations.TypeOfOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {
    private static List<Operation> history = new ArrayList<>();

    public List<Operation> listHistory(){
        return history;
    }

    @Autowired
    ApplicationContext context;
    public double calculateOperation(Double firstValue, Double secondValue, TypeOfOperations operation){
        if (firstValue == null) throw new IllegalArgumentException("First value is null!!");
        if (secondValue == null) throw new IllegalArgumentException("Second value is null!!");
        if (operation != null){
            Operation op = (Operation) context.getBean(operation.name());
            op.setFirstValue(firstValue);
            op.setSecondValue(secondValue);
            history.add(op);
            return op.doOperation();
        }else {
            throw new IllegalArgumentException("Invalid operation!");
        }
    }
}
