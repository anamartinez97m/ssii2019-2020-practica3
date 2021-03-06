package prac3.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prac3.entidades.DimHospital;
import prac3.repositorios.RepositorioHospital;

import java.util.List;

@Service
public class ServicioHospital {

    @Autowired
    private RepositorioHospital repositorioHospital;

    public List<DimHospital> getHospital() {
        return (List<DimHospital>) repositorioHospital.findAll();
    }

    public DimHospital getHospitalById(String id) {
        return repositorioHospital.findByIdHospital(id);
    }

    public void guardarHospital(DimHospital h) {
        repositorioHospital.save(h);
    }

    public String comprobarHospital(DimHospital h) {
        DimHospital hospital = repositorioHospital.findByNombreAndCpostal(h.getNombre(), h.getCpostal());
        if (hospital != null) {
            return hospital.getIdHospital();
        } else {
            return repositorioHospital.save(h).getIdHospital();
        }
    }
}
