package StandUpComedy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
class ComedianController {

	private final ComedianRepository repository;

	ComedianController(ComedianRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/comedians")
	List<Comedian> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/comedians")
	Comedian newEmployee(@RequestBody Comedian nc) {
		return repository.save(nc);
	}

	// Single item

//	@GetMapping("/comedians/{id}")
//	Comedian one(@PathVariable Long id) {
//		return repository.findById(id)
//				.orElseThrow(() -> new ComedianNotFoundException(id));
//	}

	@GetMapping("/comedians/{id}/Joke")
	Joke firstjoke(@PathVariable Long id) {

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			//HTTP GET method
			HttpGet httpget = new HttpGet("http://localhost:5555/api/jokes/1");
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
			System.out.println(responseBody);
			System.out.println("----------------------------------------");
			Joke joke = new ObjectMapper().readValue(responseBody, Joke.class);
			return(joke);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/comedians/{id}")
	ComedianDTO one(@PathVariable Long id) {

		Comedian theComedian = repository.findById(id)
				.orElseThrow(() -> new ComedianNotFoundException(id));
		return new ComedianDTO(theComedian.getId(), theComedian.getName(), theComedian.getEmail(), theComedian.getJokes());
	}

	@PutMapping("/comedians/{id}")
	Comedian replaceEmployee(@RequestBody Comedian nc, @PathVariable Long id) {

		return repository.findById(id)
				.map(c -> {
					c.setName(nc.getName());
					c.setEmail(nc.getEmail());
					return repository.save(c);
				})
				.orElseGet(() -> {
					nc.setId(id);
					return repository.save(nc);
				});
	}

	@DeleteMapping("/comedians/{id}")
	void deleteComedian(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
