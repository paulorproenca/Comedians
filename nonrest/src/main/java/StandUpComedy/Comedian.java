package StandUpComedy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
class Comedian {
	private @Id @GeneratedValue Long id;
	private String name;
	private String email;
	private int[] jokes;

	protected Comedian() {}
	public Comedian(String name, String email, int[] jokes) {
		this.name = name;
		this.email = email;
		this.jokes = jokes;
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

	public int[] getJokes() {
		return jokes;
	}
	public void setJokes(int[] jokes) {
		this.jokes = jokes;
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
		return Objects.equals(this.id, c.id) && Objects.equals(this.name, c.name)
				&& Objects.equals(this.email, c.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.email);
	}

	@Override
	public String toString() {
		return "COMEDIAN{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.email + '\'' + '}';
	}
}
