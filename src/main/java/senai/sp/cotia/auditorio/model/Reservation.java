package senai.sp.cotia.auditorio.model;

import java.util.Calendar;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import senai.sp.cotia.auditorio.type.StatusEvent;
@Data
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Calendar dataInicio;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Calendar dataTermino;
	private boolean repetir;
	private String participantes;
	@ManyToOne
	private Usuario usuario;
	@Enumerated(EnumType.STRING)
	private StatusEvent statusEvent;
	
	
}
