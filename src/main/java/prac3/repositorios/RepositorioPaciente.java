package prac3.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import prac3.entidades.DimPaciente;

import java.util.List;

@RepositoryRestResource
public interface RepositorioPaciente extends CrudRepository<DimPaciente, String> {

    List<DimPaciente> findByEdad(short edad);
    List<DimPaciente> findBySexo(char sexo);
    List<DimPaciente> findByIMC(float IMC);
    List<DimPaciente> findByFormaFisica(String formaFisica);
    List<DimPaciente> findByTabaquismo(boolean tabaquismo);
    List<DimPaciente> findByAlcoholismo(boolean alcoholismo);
    List<DimPaciente> findByColesterol(boolean colesterol);
    List<DimPaciente> findByHipertension(boolean hipertension);
    List<DimPaciente> findByCardiopatia(boolean cardiopatia);
    List<DimPaciente> findByReuma(boolean reuma);
    List<DimPaciente> findByEPOC(boolean EPOC);
    List<DimPaciente> findBycancer(boolean cancer);
    DimPaciente findByEdadAndSexoAndIMC(short edad, char sexo, float IMC);
}
