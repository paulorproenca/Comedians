package StandUpComedy;

import org.springframework.data.jpa.repository.JpaRepository;
import StandUpComedy.Comedian;

interface ComedianRepository extends JpaRepository<Comedian, Long> {

}
