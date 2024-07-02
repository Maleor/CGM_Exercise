package dodardmathieu.cgmexercise.core.components.visit.usecase;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import dodardmathieu.cgmexercise.core.components.visit.dto.VisitDto;
import dodardmathieu.cgmexercise.core.providers.visit.VisitRepository;
import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;

@SpringBootTest
public class GetVisitUseCaseTest {
	
	@Mock
	private VisitRepository visitRepository;
	
	@InjectMocks
	private GetVisitUseCase getVisitUseCase;
	
	@Test
	public void get_visit_test() {
		
		UUID visitId = UUID.randomUUID();
		VisitEntity visit = VisitEntity.builder().id(visitId).build();
		
		when(visitRepository.find(any())).thenReturn(Optional.of(visit));
		
		VisitDto visitDto = getVisitUseCase.getVisit(visitId, this::lightPresenter);
		
		assertThat(visitDto.getId()).isEqualTo(visitId);
	
	}

	private VisitDto lightPresenter(VisitEntity entity) {
		return VisitDto.builder()
				.id(entity.getId())
				.build();	
	}
}
