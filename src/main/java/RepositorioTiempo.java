import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioTiempo extends CrudRepository<dimTiempo, String> {

    List<dimTiempo> findByMes(String mes);
}
