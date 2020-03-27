package prac3.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import prac3.entidades.TablaHechos;

import java.util.List;

@RepositoryRestResource
public interface RepositorioHechos extends CrudRepository<TablaHechos, String> {

    List<TablaHechos> findByDuracion(int duracion);
    List<TablaHechos> findByUCI(boolean uci);
    List<TablaHechos> findByFallecido(boolean fallecido);
    List<TablaHechos> findByTratamiento(String tratamiento);
}
