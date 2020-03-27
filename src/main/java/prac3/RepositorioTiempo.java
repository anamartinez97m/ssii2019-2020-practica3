package prac3;

import org.springframework.data.repository.CrudRepository;
import prac3.dimTiempo;

import java.util.List;

public interface RepositorioTiempo extends CrudRepository<dimTiempo, String> {

    List<dimTiempo> findByMes(String mes);
}
