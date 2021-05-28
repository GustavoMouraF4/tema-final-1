package br.com.ilegra.cloudnative.gustavomoura.tema8.main;

import br.com.ilegra.cloudnative.gustavomoura.tema8.configuration.AppConfig;
import br.com.ilegra.cloudnative.gustavomoura.tema8.handler.RxNettyHandler;
import io.reactivex.netty.RxNetty;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        RxNetty.createHttpServer(8080,
                new RxNettyHandler()).startAndWait();
    }
}
