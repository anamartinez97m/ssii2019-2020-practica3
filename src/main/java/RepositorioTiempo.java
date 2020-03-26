import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioTiempo extends CrudRepository<dimTIEMPO, String> {

    List<dimTIEMPO> findByMes(String mes);

}
