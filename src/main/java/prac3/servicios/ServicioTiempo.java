package prac3.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prac3.entidades.DimTiempo;
import prac3.repositorios.RepositorioTiempo;

import java.sql.Date;
import java.util.List;

@Service
public class ServicioTiempo {

    @Autowired
    private RepositorioTiempo repositorioTiempo;

    public List<DimTiempo> getTiempo() {
        return (List<DimTiempo>) repositorioTiempo.findAll();
    }

    public DimTiempo getTiempoByFecha(Date fecha) {
        DimTiempo t = new DimTiempo();
        t.setFecha(fecha);
        t.setDia((short) fecha.getDay());
        t.setMes((short) fecha.getMonth());
        t.setAnio((short) fecha.getYear());

        switch (t.getMes()) {
            case 1:
            case 2:
            case 3:
            case 4: t.setCuatrimestre((short) 1);
                    break;
            case 5:
            case 6:
            case 7:
            case 8: t.setCuatrimestre((short) 2);
                    break;
            case 9:
            case 10:
            case 11:
            case 12: t.setCuatrimestre((short) 3);
                    break;
        }

        return repositorioTiempo.findByFechaAndDiaAndMesAndAnioAndCuatrimestre(t.getFecha(), t.getDia(), t.getMes(), t.getAnio(), t.getCuatrimestre());
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
