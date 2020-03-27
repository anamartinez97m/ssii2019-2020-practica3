import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioHospital extends CrudRepository<dimHospital, String> {

    List<dimHospital> findByNombre(String nombre);
}
