package dodardmathieu.cgmexercise.core.components.visit.usecase;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.core.providers.visit.VisitRepository;

@Service
public class DeleteVisitUseCase {

	private final VisitRepository visitRepository;
	
	@Autowired
	public DeleteVisitUseCase(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Transactional
	public void delete(@NotNull UUID visitId) {
		visitRepository.delete(visitId);
	}
}
