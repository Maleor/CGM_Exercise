package dodardmathieu.cgmexercise.core.providers.visit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;

public interface VisitRepository {
	
	Optional<VisitEntity> find(UUID visitId);
	
	List<VisitEntity> findAll();

	VisitEntity save(VisitEntity visitEntity);

	void delete(@NotNull UUID visitId);

}
