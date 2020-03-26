import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioHospital extends CrudRepository<dimHOSPITAL, String> {

    List<dimHOSPITAL> findByNombre(String nombre);

}
