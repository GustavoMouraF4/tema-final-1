package br.com.ilegra.cloudnative.gustavomoura.tema8.handler;

import br.com.ilegra.cloudnative.gustavomoura.tema8.configuration.AppConfig;
import br.com.ilegra.cloudnative.gustavomoura.tema8.controller.HealthChecker;
import br.com.ilegra.cloudnative.gustavomoura.tema8.service.CalculatorService;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class RxNettyHandlerTest {

    private final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    private final HttpServer<ByteBuf,ByteBuf> httpServer = RxNetty.createHttpServer(8089, new RxNettyHandler());
    @Test
    public void TestingConnection() throws InterruptedException, IOException {
        httpServer.start();
        HttpUriRequest request = new HttpGet("http://localhost:8089/calculator?num1=7&op=SUM&num2=5");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        httpServer.shutdown();
    }

    @Test
    public void TestingMediaType() throws InterruptedException, IOException {
        httpServer.start();
        HttpUriRequest request = new HttpGet("http://localhost:8089/calculator?num1=3&op=SUM&num2=1");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals( "text/plain", ContentType.getOrDefault(httpResponse.getEntity()).getMimeType());
        httpServer.shutdown();
    }

    @Test
    public void TestingConnectionWithHistory() throws InterruptedException, IOException {
        httpServer.start();
        HttpUriRequest request = new HttpGet("http://localhost:8089/calculator?history");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        httpServer.shutdown();
    }
}