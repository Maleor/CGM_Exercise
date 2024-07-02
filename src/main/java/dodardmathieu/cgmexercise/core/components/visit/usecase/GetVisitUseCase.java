package dodardmathieu.cgmexercise.core.components.visit.usecase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.common.CgmExerciseResource;
import dodardmathieu.cgmexercise.core.providers.visit.VisitRepository;
import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;
import dodardmathieu.cgmexercise.error.exception.ResourceNotFoundException;

@Service
public class GetVisitUseCase {

	private final VisitRepository visitRepository;
	
	@Autowired
	public GetVisitUseCase(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}
	
	@FunctionalInterface
	public interface GetVisitPresenter<T> {
		T apply(VisitEntity visitEntity);
	}
	
	@Transactional(readOnly = true)
	public <T> T getVisit(@NotNull UUID visitId, @NotNull GetVisitPresenter<T> presenter) {
		return visitRepository.find(visitId).map(presenter::apply).orElseThrow(() -> new ResourceNotFoundException(CgmExerciseResource.VISIT, visitId.toString()));
	}
	
	@Transactional(readOnly = true)
	public <T> List<T> getAll(@NotNull GetVisitPresenter<T> presenter) {
		return visitRepository.findAll().stream().map(presenter::apply).collect(Collectors.toList());
	}
}
