package senai.sp.cotia.auditorio.rest;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;


import senai.sp.cotia.auditorio.model.Reservation;
import senai.sp.cotia.auditorio.model.Sucesso;
import senai.sp.cotia.auditorio.model.Usuario;
import senai.sp.cotia.auditorio.repository.ReservationRepository;
import senai.sp.cotia.auditorio.type.StatusEvent;
	

@RestController
@RequestMapping("api/reservation")
public class ReservationRestController {
	@Autowired
	private ReservationRepository repository;
	
	@RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object saveReservation(@RequestBody Reservation reservation ) {
		String token = null;
		Sucesso sucesso = new Sucesso();
		sucesso.setStatusCode(201);
		sucesso.setMensagem("reservado com sucesso");

	
		// algoritimo para descriptografar
		Algorithm algoritimo = Algorithm.HMAC256(UserRestController.SECRET);
		// objeto para verificar o token	
		JWTVerifier verifier = JWT.require(algoritimo).withIssuer(UserRestController.EMISSOR).build();
		// validar o token
		DecodedJWT decoded = verifier.verify(token);
		reservation.setStatusEvent(StatusEvent.ANALISE);
		Map<String, Claim> payload = decoded.getClaims();
		payload.get("id_usuario");
		Usuario user   = new Usuario();
	
		repository.save(reservation);
		return new ResponseEntity<Object>(sucesso, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<Reservation> getReservations(){
		Reservation reservation = new Reservation(); 
		return repository.findAll(); 
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Void>  deleteReservation(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	/*
	 * @RequestMapping(value = "historico", method = RequestMethod.GET) public
	 * Iterable<Reservation> getAllHistorico(StatusEvent status) { return
	 * repository.findAllByStatus(status.FINALIZADO); }
	 */
}
