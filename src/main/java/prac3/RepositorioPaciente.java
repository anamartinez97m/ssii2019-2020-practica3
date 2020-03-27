package prac3;

import org.springframework.data.repository.CrudRepository;
import prac3.dimPaciente;

import java.util.List;

public interface RepositorioPaciente extends CrudRepository<dimPaciente, String> {

    List<dimPaciente> findByEdad(int edad);
}
