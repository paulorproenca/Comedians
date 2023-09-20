package StandUpComedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import StandUpComedy.Comedian;
import StandUpComedy.ComedianRepository;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(ComedianRepository repository) {

		return args -> {
			int[] BilboJokes = {1,2,3};
			log.info("Preloading " + repository.save(new Comedian("Bilbo Baggins", "bilbo@mail.org",BilboJokes)));
			int[] frodoJokes = {4,5};
			log.info("Preloading " + repository.save(new Comedian("Frodo Baggins", "thief@frog.uk", frodoJokes)));
		};
	}
}
