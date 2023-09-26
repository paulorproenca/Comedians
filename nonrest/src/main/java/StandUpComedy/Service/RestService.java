package StandUpComedy.Service;

import StandUpComedy.Model.Joke;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class RestService {

    //private String JokeServiceURL = "http://localhost:5555/api/jokes/";
    private String JokeServiceURL = "http://172.17.0.1:5555/api/jokes/";

    public Joke getTheJoke(int jokeId) {

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet(this.JokeServiceURL +jokeId);
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler< String > responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            return new ObjectMapper().readValue(responseBody, Joke.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
