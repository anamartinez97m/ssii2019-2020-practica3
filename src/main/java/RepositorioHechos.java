import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioHechos extends CrudRepository<tablaHECHOS, String> {

    List<tablaHECHOS> findByDuracion(int duracion);

}
