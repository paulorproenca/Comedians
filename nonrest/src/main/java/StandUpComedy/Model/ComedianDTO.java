package StandUpComedy.Model;

import java.util.List;
import java.util.Objects;

public class ComedianDTO {

    private Long id;
    private String name;
    private String email;

    private List<Joke> theJokes;


   // private RestService theService;

    public ComedianDTO(Long id, String name, String email, List<Joke> jokes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.theJokes = jokes;
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

    /*
    public void setTheJoke(int jokeid) {

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            //HTTP GET method
            HttpGet httpget = new HttpGet("http://localhost:5555/api/jokes/" + jokeid);
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
    */


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
