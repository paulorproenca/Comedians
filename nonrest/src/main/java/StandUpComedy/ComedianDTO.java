package StandUpComedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

class ComedianDTO {

    private Long id;
    private String name;
    private String email;

    private List<Joke> theJokes;

    ComedianDTO(Long id,String name, String email, int[] jokes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.theJokes = new ArrayList<Joke>();
        for(int x=0;x<jokes.length;x++){
            setTheJoke(jokes[x]);
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public List<Joke> getTheJokes() {
        return theJokes;
    }

    public void setTheJoke(int jokeid) {

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet("http://localhost:5262/api/jokes/" + jokeid);
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler < String > responseHandler = response -> {
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
            Joke joke = new ObjectMapper().readValue(responseBody, Joke.class);
            this.theJokes.add(joke);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String role) {
        this.email = role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Comedian))
            return false;
        Comedian c = (Comedian) o;
        return Objects.equals(this.id, c.getId()) && Objects.equals(this.name, c.getName())
                && Objects.equals(this.email, c.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email);
    }

    @Override
    public String toString() {
        return "COMEDIANDTO{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.email + '\'' + '}';
    }
}
