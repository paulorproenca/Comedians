package StandUpComedy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import StandUpComedy.Model.Comedian;

public interface ComedianRepository extends JpaRepository<Comedian, Long> {

}
