package br.com.ilegra.cloudnative.gustavomoura.tema8.configuration;

import br.com.ilegra.cloudnative.gustavomoura.tema8.controller.HealthChecker;
import br.com.ilegra.cloudnative.gustavomoura.tema8.operations.*;
import br.com.ilegra.cloudnative.gustavomoura.tema8.service.CalculatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("br.com.ilegra.tema1.configuration")
public class AppConfig {

    @Bean(name = "HEALTH")
    public HealthChecker healthChecker(){
        return new HealthChecker();
    }

    @Bean(name = "CALCULATOR")
    public CalculatorService calculator(){return new CalculatorService();}

    @Bean(name = "SUM")
    @Scope(value = "prototype")
    public Sum sum(){
        return new Sum();
    }

    @Bean(name = "SUB")
    @Scope(value = "prototype")
    public Subtraction subtraction(){ return new Subtraction(); }

    @Bean(name = "DIV")
    @Scope(value = "prototype")
    public Division division(){ return new Division(); }

    @Bean(name = "MULT")
    @Scope(value = "prototype")
    public Multiplication multiplication(){ return new Multiplication(); }

    @Bean(name = "POW")
    @Scope(value = "prototype")
    public Pow pow(){
        return new Pow();
    }
}
