import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioPaciente extends CrudRepository<dimPaciente, String> {

    List<dimPaciente> findByEdad(int edad);
}
