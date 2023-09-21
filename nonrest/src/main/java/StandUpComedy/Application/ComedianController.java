package StandUpComedy.Application;

import StandUpComedy.Model.ComedianDTO;
import StandUpComedy.Application.ComedianNotFoundException;
import StandUpComedy.Repository.ComedianRepository;
import StandUpComedy.Model.Comedian;
import StandUpComedy.Model.Joke;

import StandUpComedy.Service.RestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
class ComedianController {

	private RestService theService;
	private final ComedianRepository repository;

	ComedianController(ComedianRepository repository, RestService theService) {
		this.theService = theService;
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

	@GetMapping("/comedians/{id}/firstjoke")
	Joke firstjoke(@PathVariable Long id) {

		Comedian theComedian = repository.findById(id)
				.orElseThrow(() -> new ComedianNotFoundException(id));
		return theService.getTheJoke(theComedian.getJokes()[0]);
	}

	@GetMapping("/comedians/{id}")
	ComedianDTO one(@PathVariable Long id) {

		List<Joke> theJokes = new ArrayList<Joke>();

		Comedian theComedian = repository.findById(id)
				.orElseThrow(() -> new ComedianNotFoundException(id));
		for(int jokeId : theComedian.getJokes()){
			theJokes.add(theService.getTheJoke(jokeId));
		}

		return new ComedianDTO(theComedian.getId(), theComedian.getName(), theComedian.getEmail(), theJokes);
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
