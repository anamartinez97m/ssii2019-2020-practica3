package prac3.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import prac3.entidades.DimTiempo;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource
public interface RepositorioTiempo extends CrudRepository<DimTiempo, String> {

    List<DimTiempo> findByFecha(Date fecha);
    List<DimTiempo> findByDia(int dia);
    List<DimTiempo> findByMes(int mes);
    List<DimTiempo> findByAnio(int anio);
    List<DimTiempo> findByCuatrimestre(int cuatrimestre);
    List<DimTiempo> findByDiaSemana(String diaSemana);
    List<DimTiempo> findByEsFinde(byte esFinde);

    DimTiempo findByDiaAndMesAndAnioAndCuatrimestre(int dia,
                                                    int mes,
                                                    int anio,
                                                    int cuatrimestre);
    DimTiempo findByFechaAndDiaAndMesAndAnioAndCuatrimestreAndDiaSemanaAndEsFinde(Date fecha,
                                                                                  int dia,
                                                                                  int mes,
                                                                                  int anio,
                                                                                  int cuatrimestre,
                                                                                  String diaSemana,
                                                                                  byte esFinde);
}
