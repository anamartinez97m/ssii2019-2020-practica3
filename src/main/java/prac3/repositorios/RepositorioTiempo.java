package prac3.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import prac3.entidades.DimTiempo;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource
public interface RepositorioTiempo extends CrudRepository<DimTiempo, String> {

    List<DimTiempo> findByFecha(Date fecha);
    List<DimTiempo> findByDia(short dia);
    List<DimTiempo> findByMes(short mes);
    List<DimTiempo> findByAnio(short anio);
    List<DimTiempo> findByCuatrimestre(short cuatrimestre);
    List<DimTiempo> findByDiaSemana(String diaSemana);
    List<DimTiempo> findByEsFinde(byte esFinde);
    DimTiempo findByFechaAndDiaAndMesAndAnioAndCuatrimestreAndDiaSemanaAndEsFinde(Date fecha,
                                                                                  short dia,
                                                                                  short mes,
                                                                                  short anio,
                                                                                  short cuatrimestre,
                                                                                  String diaSemana,
                                                                                  byte esFinde);
}
