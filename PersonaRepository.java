package pe.edu.cibertec.DAW1_CL2_GustavoCalderon;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
public interface PersonaRepository extends CrudRepository<Persona, Long> {
	List<Persona> findAll();

	// select m from Marca m where nombre = ?
	List<Persona> findByNombre(String nombre);

    Optional<Persona> findById(Long id);

    void deleteById(Long id);

}
