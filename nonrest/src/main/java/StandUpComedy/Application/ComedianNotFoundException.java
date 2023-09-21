package StandUpComedy.Application;

class ComedianNotFoundException extends RuntimeException {

	ComedianNotFoundException(Long id) {
		super("Could not find comedian " + id);
	}
}
