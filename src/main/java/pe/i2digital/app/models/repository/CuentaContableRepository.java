package pe.i2digital.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.dto.projection.CuentaContableVista;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.entity.CuentaContable;

import java.util.List;

public interface CuentaContableRepository  extends CrudRepository<CuentaContable, Integer> {

    //Uso de DTO basado en clase  //Ejemplo de query con keywords
    public List<CuentaContableDTO> findByNumeroStartingWithOrderByIdAsc(String numero);
    //Uso de DTO basado en clases pero por expresion constructor //Cuando uso JPQL
    @Query("select new pe.i2digital.app.models.dto.CuentaContableDTO(c.id,c.numero, c.nombre) " +
            "from CuentaContable c where  UPPER(c.numero) like concat('',UPPER(?1),'%')  " +
            "order by c.id asc")
    public List<CuentaContableDTO>  busquedaPersonalizadaNumero(String numero);
    /*DTO basado en proyecciones / interfaces : Cerrado y Abierto*/
    //Uso de DTO basado en interfaces / proyeccion -> proyeccion cerrada
    public List<CuentaContableVista> findByNumeroStartingWithAndUsaDocumentoTrueOrderById(String numero);
    //Uso de DTO basado en interfaces / proyeccion -> proyeccion cerrada / interfaces anidadas
}
