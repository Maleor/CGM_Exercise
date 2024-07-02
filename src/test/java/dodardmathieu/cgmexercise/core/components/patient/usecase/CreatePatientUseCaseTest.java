package dodardmathieu.cgmexercise.core.components.patient.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import dodardmathieu.cgmexercise.core.components.patient.dto.PatientCreationRequestDto;
import dodardmathieu.cgmexercise.core.providers.patient.PatientRepository;
import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;

@SpringBootTest
public class CreatePatientUseCaseTest {

	@Mock
	private PatientRepository patientRepository;

	@InjectMocks
	private CreatePatientUseCase createPatientUseCase;

	@Test
	public void create_patient_test() {
		
		
		PatientCreationRequestDto request = PatientCreationRequestDto.builder()
				.name("name")
				.surname("surname")
				.socialSecurityNumber(1234567891237L)
				.build();

		
		
		when(patientRepository.save(any())).thenReturn(PatientEntity.builder()
				.name("name")
				.surname("surname")
				.socialSecurityNumber(1234567891237L)
				.id(UUID.randomUUID())
				.build());
		
		UUID newPatient = createPatientUseCase.create(request, PatientEntity::getId);
		
		assertThat(newPatient).isNotNull();
	
	}
}
