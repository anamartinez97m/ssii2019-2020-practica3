package prac3.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import prac3.entidades.DimHospital;

import java.util.List;

@RepositoryRestResource
public interface RepositorioHospital extends CrudRepository<DimHospital, String> {

    List<DimHospital> findByNombre(String nombre);
    List<DimHospital> findByCpostal(short cpostal);
    List<DimHospital> findByAutopista(String autopista);
    List<DimHospital> findByGestor(String gestor);
    DimHospital findByNombreAndCpostal(String nombre, short cpostal);
}
