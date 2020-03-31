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

    public DimTiempo getTiempoByDiaAndMesAndAnioAndCuatrimestre(String fecha) {
        String[] fechaPartida = fecha.split("/");

        if (fechaPartida[2].length() == 2) {
            fechaPartida[2] = "20"+fechaPartida[2];
        }

        int dia = Short.parseShort(fechaPartida[0]);
        int mes = Short.parseShort(fechaPartida[1]);
        int anio = Short.parseShort(fechaPartida[2]);
        int cuatrimestre = 0;

        switch (mes) {
            case 1:
            case 2:
            case 3:
            case 4: cuatrimestre = 1;
                    break;
            case 5:
            case 6:
            case 7:
            case 8: cuatrimestre = 2;
                    break;
            case 9:
            case 10:
            case 11:
            case 12: cuatrimestre = 3;
                    break;
        }
        DimTiempo tiempo = repositorioTiempo.findByDiaAndMesAndAnioAndCuatrimestre(dia, mes, anio, cuatrimestre);
        System.out.println("EL TIEMPO ENCONTRADO ES: " +tiempo);
        return tiempo;
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
