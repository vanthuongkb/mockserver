package com.vanthuong.tutorial_app;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
// http://www.mock-server.com/mock_server/mockserver_clients.html#java-mockserver-client
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Unirest.class)
@PowerMockIgnore("javax.net.ssl.*")
public class MyTest {
    private ClientAndServer mockServer = new ClientAndServer();
    private Map<String, List<String>> params;

    @Before
    public void setUp() {
        params = new LinkedHashMap<String, List<String>>();
        params.put("desc", Arrays.asList("false"));
        
        mockServer = startClientAndServer(9000);
    }

    @After
    public void tearDown() {
        mockServer.stop();
    }

    @Test
    public void testUnirest() throws Exception {
        mockServer.when(
                request()
                .withMethod("GET")
                .withPath("/abc")
                .withQueryStringParameters(params))
        
                .respond(
                        response()
                        .withStatusCode(200)
                        .withCookie("sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW")
                        .withHeader("Location", "https://www.mock-server.com")
                        .withBody("OMG"));

        SonarProject sonarProject = new SonarProject();

        sonarProject.getSomething("abc");
    }

    @Test
    public void testUnires1t() throws UnirestException {
        mockServer.when(request().withMethod("GET").withPath("/xyz").withQueryStringParameters(params))
                .respond(response().withStatusCode(200).withCookie("sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW")
                        .withHeader("Location", "https://www.mock-server.com").withBody("OMG"));

        SonarProject sonarProject = new SonarProject();

        sonarProject.getSomething("xyz");

    }

}
