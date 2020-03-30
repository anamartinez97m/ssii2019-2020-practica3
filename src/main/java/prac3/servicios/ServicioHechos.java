package prac3.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prac3.entidades.DimPaciente;
import prac3.entidades.TablaHechos;
import prac3.repositorios.RepositorioHechos;

import java.util.List;

@Service
public class ServicioHechos {

    @Autowired
    private RepositorioHechos repositorioHechos;

    public List<TablaHechos> getHechos() {
        return (List<TablaHechos>) repositorioHechos.findAll();
    }

    public void guardarHecho(TablaHechos th) {
        repositorioHechos.save(th);
    }

    public String comprobarHecho(TablaHechos th) {
        TablaHechos hecho = repositorioHechos.findByDuracionAndUCIAndFallecidoAndTratamiento(th.getDuracion(),
                th.isUCI(), th.isFallecido(),th.getTratamiento());
        if (hecho != null) {
            String id = hecho.getId();
            return id;
        } else {
            return repositorioHechos.save(th).getId();
        }
    }
}
