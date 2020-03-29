package prac3.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prac3.entidades.DimTiempo;
import prac3.repositorios.RepositorioTiempo;

import java.util.List;

@Service
public class ServicioTiempo {

    @Autowired
    private RepositorioTiempo repositorioTiempo;

    public List<DimTiempo> getTiempo() {
        return (List<DimTiempo>) repositorioTiempo.findAll();
    }

    public void guardarTiempo(DimTiempo t) {
        repositorioTiempo.save(t);
    }

    public String comprobarTiempo(DimTiempo t) {
        DimTiempo tiempo = repositorioTiempo.findByFechaAndDiaAndMesAndAnioAndCuatrimestreAndDiaSemanaAndEsFinde(t.getFecha(), t.getDia(), t.getMes(), t.getAnio(), t.getCuatrimestre(),t.getDiaSemana(), t.isEsFinde());
        if (tiempo != null) {
            String id = tiempo.getIdTiempo();
            return id;
        } else {
            return (repositorioTiempo.save(t).getIdTiempo());
        }
    }

}
