package com.vanthuong.tutorial_app;
// http://www.mock-server.com/mock_server/mockserver_clients.html#java-mockserver-client
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Unirest.class)
@PowerMockIgnore("javax.net.ssl.*")
public class MyTest {
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 9000);

    // private MockServerClient mockServerClient;

    @Before
    public void setUp() {
        Map<String, List<String>> params = new LinkedHashMap<String, List<String>>();
        params.put("desc", Arrays.asList("false"));
        new MockServerClient("localhost", 9000)
                .when(request().withMethod("GET").withPath("/abc")
                        .withQueryStringParameters(params))
                .respond(response().withStatusCode(200).withCookie("sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW")
                        .withHeader("Location", "https://www.mock-server.com").withBody("OMG"));
        
        new MockServerClient("localhost", 9000)
        .when(request().withMethod("GET").withPath("/xyz")
                .withQueryStringParameters(params))
        .respond(response().withStatusCode(200).withCookie("sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW")
                .withHeader("Location", "https://www.mock-server.com").withBody("OMG"));
    }

    @Test
    public void testUnirest() throws UnirestException {
        // PowerMockito.mockStatic(Unirest.class);
        SonarProject sonarProject = new SonarProject();
        // params.put("desc", false);

        sonarProject.getSomething("abc");

    }

    @Test
    public void testUnires1t() throws UnirestException {
        // PowerMockito.mockStatic(Unirest.class);
        SonarProject sonarProject = new SonarProject();
        // params.put("desc", false);

        sonarProject.getSomething("xyz");

    }
}
