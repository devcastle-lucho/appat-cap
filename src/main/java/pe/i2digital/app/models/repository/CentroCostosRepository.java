/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.models.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.i2digital.app.models.entity.CentroCostos;

public interface CentroCostosRepository extends CrudRepository<CentroCostos, Integer> {
    //Creacion query por nombres de metodos en keywords de JPQL
    /*---------------------------------------------------*/
    public List<CentroCostos> findByNombre(String nombre);
    public Optional<CentroCostos> findByCodigo(String codigo);
    //Listar :Comienzo con una palabra, y de orden desc
    public List<CentroCostos> findByNombreStartingWithOrderByCodigoDesc(String p1);
    //Listar :Comienzo con una palabra que no sea sensitive case, y que sea mayor aun id determinado
    public List<CentroCostos> findByIdAfterAndNombreStartingWithIgnoreCase(Integer id, String nombre);

    //Creacion query por definicion SQL en JPQL
    /*---------------------------------------------------*/
    @Query("select c from CentroCostos c where c.codigo=?1 ")
    public Optional<CentroCostos> busquedaJPQLxCodigo(String codigo);
    @Query("select c from CentroCostos c where c.id>?1 and UPPER(c.nombre) like concat('',UPPER(?2),'%')")
    public List<CentroCostos> busquedaJPQLxIdxNombrePerzonalizado(Integer id, String nombre);

    /*---------------------------------------------------*/
    //Creacion query por definicion SQL nativo
    @Query(value="select * from sh_empresa_20441636831.tb_at_centrocostos  where tb_centrocostos_cod=?1 ", nativeQuery = true)
    public Optional<CentroCostos> busquedaxCodigo(String codigo);

    @Query(value="select * from sh_empresa_20441636831.tb_at_centrocostos  where tb_centrocostos_id>:id and UPPER(tb_centrocostos_nom) like concat(UPPER(:nombre),'%')", nativeQuery = true)
    public List<CentroCostos> busquedaxIdxNombrePerzonalizado(@Param("id")Integer id,@Param("nombre") String nombre);

    /*---------------------------------------------------*/
    //Creacion query mediante nombres de queries : 1.-Archivo externo .properties, 2.-Archivo externo .xml, 3.-Notacion
    //1.-x JPQL: listar
    public List<CentroCostos> findAllNamedFile();
    //1.-x JPQL: busqueda x codigo
    public Optional<CentroCostos> findByCodigoNamedFile(String codigo);
    //1.-x JPQL: cantidad de caracteres del nombre de una centro de costos en base del codigo
    public int sampleNamedQuery(String codigo);

    //1.-x SQL Nativo: listar
    @Query(nativeQuery = true)
    public List<CentroCostos> findAllNamedNativeFile();
    @Query(nativeQuery = true)
    public int sampleNamedNativeQuery(String codigo);

    //2.-x JPQL: listar
    public List<CentroCostos> findAllXML();
    //2.-x JPQL: busqueda x codigo
    public Optional<CentroCostos> buscarxCodigoXML(String codigo);

    //2-x SQL Nativo: listar
    @Query(nativeQuery = true)
    public List<CentroCostos> findAllNativeXML();

    //3.-x JPQL: listar
    public List<CentroCostos> findAllJPQL();
    //3.-x JPQL: busqueda x codigo
    public Optional<CentroCostos> buscarxCodigoJPQL(String codigo);

    //3-x SQL Nativo: listar
    @Query(nativeQuery = true)
    public List<CentroCostos> findAllNative();
}
