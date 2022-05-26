package senai.sp.cotia.auditorio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.auth0.jwt.interfaces.Claim;

import senai.sp.cotia.auditorio.model.Usuario;
import senai.sp.cotia.auditorio.type.Types;


public interface UserRepository extends PagingAndSortingRepository<Usuario, Long>{
	
	/*public Usuario findByNifAndSenha(String email, String senha);
	public Iterable<Usuario>findByUsuarioId(Claim claim);*/
	
}
