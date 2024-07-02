package dodardmathieu.cgmexercise.core.components.visit.usecase;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.core.providers.visit.VisitRepository;
import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;

/**
 * Class dedicated to {@link VisitEntity} deletion
 * 
 * @author Maleor
 *
 */
@Service
public class DeleteVisitUseCase {

	private final VisitRepository visitRepository;
	
	@Autowired
	public DeleteVisitUseCase(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	/**
	 * Delete a {@link VisitEntity}
	 * 
	 * @param visitId - Id of the visit to delete
	 */
	@Transactional
	public void delete(@NotNull UUID visitId) {
		visitRepository.delete(visitId);
	}
}
