package dodardmathieu.cgmexercise.dataproviders.patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Table(name = "patient")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity implements Serializable {

	@GenericGenerator(name = "UUID", strategy = "uuid2")
	@GeneratedValue(generator = "UUID")
	@Type(type="uuid-char")
	@Id
	private UUID id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	private long socialSecurityNumber;
	
	@NotNull
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@Builder.Default
	private List<VisitEntity> visits = new ArrayList<>();

}
