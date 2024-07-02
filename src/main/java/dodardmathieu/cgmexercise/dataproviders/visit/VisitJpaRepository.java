package dodardmathieu.cgmexercise.dataproviders.visit;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitJpaRepository extends JpaRepository<VisitEntity, UUID>{
	
}
