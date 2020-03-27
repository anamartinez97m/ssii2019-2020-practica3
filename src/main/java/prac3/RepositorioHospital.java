package prac3;

import org.springframework.data.repository.CrudRepository;
import prac3.dimHospital;

import java.util.List;

public interface RepositorioHospital extends CrudRepository<dimHospital, String> {

    List<dimHospital> findByNombre(String nombre);
}
