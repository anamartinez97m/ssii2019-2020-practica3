package prac3;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioHechos extends CrudRepository<tablaHechos, String> {

    List<tablaHechos> findByDuracion(int duracion);
}
