package prac3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prac3.servicios.ServicioHechos;
import prac3.servicios.ServicioHospital;
import prac3.servicios.ServicioPaciente;
import prac3.servicios.ServicioTiempo;

@Component
public class ProcessData {
    @Autowired
    private ServicioHechos servicioHechos;
    @Autowired
    private ServicioHospital servicioHospital;
    @Autowired
    private ServicioPaciente servicioPaciente;
    @Autowired
    private ServicioTiempo servicioTiempo;

    /*PostConstruct*/
}
