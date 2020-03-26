import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioPaciente extends CrudRepository<dimPACIENTE, String> {

    List<dimPACIENTE> findByEdad(int edad);

}
