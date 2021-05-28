package br.com.ilegra.cloudnative.gustavomoura.tema8.service;

import br.com.ilegra.cloudnative.gustavomoura.tema8.configuration.AppConfig;
import br.com.ilegra.cloudnative.gustavomoura.tema8.operations.Subtraction;
import br.com.ilegra.cloudnative.gustavomoura.tema8.operations.TypeOfOperations;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void calculateSumOperationTest(){
        double test = calculatorService.calculateOperation(8.0,8.0, TypeOfOperations.SUM);
        Assert.assertEquals(16, test,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateSumOperationWithNullValueTest(){
        double test = calculatorService.calculateOperation(null,8.0, TypeOfOperations.SUM);
        Assert.assertEquals(16, test,0);
    }

    @Test
    public void calculateSubOperationTest(){
        double test = calculatorService.calculateOperation(10.0,8.0,TypeOfOperations.SUB);
        Assert.assertEquals(2, test,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateSubOperationWithNullValueTest(){
        double test = calculatorService.calculateOperation(10.0,null,TypeOfOperations.SUB);
        Assert.assertEquals(2, test,0);
    }
    @Test
    public void calculateDivOperationTest(){
        double test = calculatorService.calculateOperation(8.0,8.0,TypeOfOperations.DIV);
        Assert.assertEquals(1, test,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateDivOperationWithNullValueTest(){
        double test = calculatorService.calculateOperation(null,8.0,TypeOfOperations.DIV);
        Assert.assertEquals(1, test,0);
    }
    @Test(expected = ArithmeticException.class)
    public void calculateDivByZeroTest(){
        double test = calculatorService.calculateOperation(8.0,0.0,TypeOfOperations.DIV);
        Assert.assertEquals(1, test,0);
    }
    @Test
    public void calculateMultOperationTest(){
        double test = calculatorService.calculateOperation(8.0,8.0,TypeOfOperations.MULT);
        Assert.assertEquals(64, test,0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void calculateMultOperationWithNullValueTest(){
        double test = calculatorService.calculateOperation(null,8.0,TypeOfOperations.MULT);
        Assert.assertEquals(64, test,0);
    }
    @Test
    public void calculatePowOperationTest(){
        double test = calculatorService.calculateOperation(3.0,3.0,TypeOfOperations.POW);
        Assert.assertEquals(27, test,0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void calculatePowOperationWithNullValueTest(){
        double test = calculatorService.calculateOperation(null,3.0,TypeOfOperations.POW);
        Assert.assertEquals(27, test,0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void calculatePowOperationWithNullOperationTest(){
        double test = calculatorService.calculateOperation(3.0,3.0,null);
        Assert.assertEquals(27, test,0);
    }
    @Test
    public void listHistoryTest(){
        calculatorService.listHistory().clear();
        calculatorService.calculateOperation(8.0,8.0,TypeOfOperations.SUM);
        calculatorService.calculateOperation(10.0,8.0,TypeOfOperations.SUB);
        calculatorService.calculateOperation(8.0,8.0,TypeOfOperations.DIV);
        calculatorService.calculateOperation(8.0,8.0,TypeOfOperations.MULT);
        calculatorService.calculateOperation(3.0,3.0,TypeOfOperations.POW);
        Assert.assertNotNull(calculatorService.listHistory());
        Assert.assertEquals(5, calculatorService.listHistory().size());
        Assert.assertTrue(calculatorService.listHistory().get(1) instanceof Subtraction);
    }

}