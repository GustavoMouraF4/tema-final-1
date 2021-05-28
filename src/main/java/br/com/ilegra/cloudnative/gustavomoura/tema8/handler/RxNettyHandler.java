package br.com.ilegra.cloudnative.gustavomoura.tema8.handler;

import br.com.ilegra.cloudnative.gustavomoura.tema8.configuration.AppConfig;
import br.com.ilegra.cloudnative.gustavomoura.tema8.controller.HealthChecker;
import br.com.ilegra.cloudnative.gustavomoura.tema8.operations.TypeOfOperations;
import br.com.ilegra.cloudnative.gustavomoura.tema8.service.CalculatorService;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rx.Observable;

public class RxNettyHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private static final String CALCULATOR = "/calculator/";
    private static final String HEALTHCHECK = "/healthCheck";

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        HealthCheckEndpoint healthCheckEndpoint = new HealthCheckEndpoint((HealthChecker) applicationContext.getBean("HEALTH"));
        CalculatorService calculatorService = (CalculatorService) applicationContext.getBean("CALCULATOR");

        if (request.getUri().startsWith(CALCULATOR)){
            if (request.getQueryParameters().containsKey("num1")
                    && request.getQueryParameters().containsKey("num2")
                    && request.getQueryParameters().containsKey("op")){
                try{
                    double num1 = Double.parseDouble(request.getQueryParameters().get("num1").get(0));
                    double num2 = Double.parseDouble(request.getQueryParameters().get("num2").get(0));
                    TypeOfOperations op = TypeOfOperations.valueOf(request.getQueryParameters().get("op").get(0));
                    double result = calculatorService.calculateOperation(num1, num2, op);
                    response.setStatus(HttpResponseStatus.OK);
                    return response.writeStringAndFlush("RESULT: " + num1 + " " + op.name() + " " + num2 + " = " + result);
                }catch (Exception e){
                    response.setStatus(HttpResponseStatus.BAD_REQUEST);
                    throw new ArithmeticException("it was impossible to perform the operation, there must be some incorrect data, please check");
                }
            }
            if (request.getQueryParameters().containsKey("history")){
                response.setStatus(HttpResponseStatus.OK);
                return response.writeStringAndFlush(calculatorService.listHistory().toString());
            }
        }else if(request.getUri().startsWith(HEALTHCHECK)) {
            return healthCheckEndpoint.handle(request, response);
        }
        return response.close();
    }
}
