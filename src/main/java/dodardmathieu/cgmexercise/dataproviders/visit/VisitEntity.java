package dodardmathieu.cgmexercise.dataproviders.visit;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

import dodardmathieu.cgmexercise.core.components.visit.dto.VisitReason;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitType;
import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Table(name = "visit")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VisitEntity implements Serializable {

	@GenericGenerator(name = "UUID", strategy = "uuid2")
	@GeneratedValue(generator = "UUID")
	@Type(type="uuid-char")
	@Id
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private PatientEntity patient;

	private Instant start;
	
	// Here I store the duration as an integer since we are told to "record the time of the visit". However, I think it would make more sense
	// store the visit end instant which would not prevent us from computing the duration when required.
	private Integer duration;
	
	@Enumerated(EnumType.STRING)
	private VisitType type;
	
	@Enumerated(EnumType.STRING)
	private VisitReason reason;

	private String familyHistory;
}
