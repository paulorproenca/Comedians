package StandUpComedy;

class ComedianNotFoundException extends RuntimeException {

	ComedianNotFoundException(Long id) {
		super("Could not find comedian " + id);
	}
}
