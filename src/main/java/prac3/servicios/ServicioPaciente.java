package prac3.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prac3.entidades.DimPaciente;
import prac3.repositorios.RepositorioPaciente;

import java.util.List;

@Service
public class ServicioPaciente {

    @Autowired
    private RepositorioPaciente repositorioPaciente;

    public List<DimPaciente> getPaciente() {
        return (List<DimPaciente>) repositorioPaciente.findAll();
    }

    public DimPaciente getPacienteById(int idPaciente) {
        return repositorioPaciente.findByIdPaciente(idPaciente);
    }

    public void guardarPaciente(DimPaciente p) {
        repositorioPaciente.save(p);
    }

    public String comprobarPaciente(DimPaciente p) {
        DimPaciente paciente = repositorioPaciente.findByEdadAndSexoAndIMC(p.getEdad(), p.getSexo(), p.getIMC());
        if (paciente != null) {
            String id = paciente.getId();
            return id;
        } else {
            return repositorioPaciente.save(p).getId();
        }
    }

}
