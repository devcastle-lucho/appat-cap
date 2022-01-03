package pe.i2digital.app;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.i2digital.app.models.dao.AsientoContableDAO;
import pe.i2digital.app.models.dao.DetalleAsientoContableDAO;
import pe.i2digital.app.models.dao.GenericDAO;
import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.repository.CentroCostosRepository;
import pe.i2digital.app.services.CentroCostosService;

@SpringBootApplication
public class AppatApplication implements CommandLineRunner
{
    @Autowired
    private CentroCostosRepository repository;
    @Autowired
    private GenericDAO genericDAO;
    @Autowired
    private CentroCostosService service;
    @Autowired
    private AsientoContableDAO asientoContableDAO;
    @Autowired
    private DetalleAsientoContableDAO detalleAsientoContableDAO;
    public static void main(String[] args) {
            SpringApplication.run(AppatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testSeguimientoDocumentos();
    }
    private void testSeguimientoDocumentos() {
        /*var lista = detalleAsientoContableDAO.seguimientoDocumentos("sh_empresa_20441636831","2020",38);
        for (var e:  lista ) {
            System.out.println(" DA Proposito: "+ e.getProposito()+" - "+ "DA Codigo: "+e.getTipo()+" - DA IMPSoles"+e.getImporteSoles() +
                              " - AC ID: "+e.getOAsientoContable().getId()+" - AC glosa: "+e.getOAsientoContable().getGlosa()+" - TOC Codigo: "+e.getOAsientoContable().getOTipoOperacionContable().getCodigo()+
                                " - D Moneda:"+e.getODocumento().getMoneda());
        }
        */
        var lista = detalleAsientoContableDAO.seguimientoDocumentos2("sh_empresa_20441636831","2020",38);
        for (var e:  lista ) {
            System.out.println(" DA Proposito: "+ e.getProposito()+" - "+ "DA Codigo: "+e.getTipo()+" - DA IMPSoles"+e.getImporteSoles() +
                    " - AC ID: "+e.getOAsientoContable().getId()+" - AC glosa: "+e.getOAsientoContable().getGlosa()+" - TOC Codigo: "+e.getOAsientoContable().getOTipoOperacionContable().getCodigo()+
                    " - D Moneda:"+e.getODocumento().getMoneda());
        }
    }
    private void testAsientoContable(){
        var lista = asientoContableDAO.busquedaExcel("2020",38);
        for (var e :  lista) {
            System.out.println(e.getGlosa()+" - "+ e.getNumero() +" - " +e.getCuentaContable() +" - " + e.getDebeSoles());
        }
    }
    private void testVersion() {
        // Test con org.springframework.data.jpa.repository.Query: Query nativa
        String version = repository.getVersion();
        System.out.println("Version 'Forma 1': "+version);
        // Test con org.springframework.data.jpa.repository.Query: @Procedure
        String version2 = repository.getVersionProcedure();
        System.out.println("Version 'Forma 2': "+version2);
        // Test con org.springframework.jdbc.core
        String version3 = genericDAO.getVersion();
        System.out.println("Version 'Forma 3': "+version3);
        String fechaActual = genericDAO.getFechaActual();
        System.out.println("Fecha Actual 'Forma 3': "+fechaActual);
        // Test con org.hibernate
        String version4 = genericDAO.version();
        System.out.println("Version 'Forma 4': "+version4);
    }

    private void testCRUDCentroCostosService() {
        //CRUD Simple - CrudRepository - Spring data
        //READ
        List<CentroCostos> lista= (List<CentroCostos>) service.findAll();
        for (CentroCostos c : lista)
            System.out.println(c.getCodigo()+" - "+ c.getNombre()+" - ");

        /*
        //----CREATE
        var ccDB = service.save(
                CentroCostos.builder()
                        .codigo("06")
                        .nombre("CC Prueba").build()
        );
        System.out.println(ccDB.getId()+"  - " + ccDB.getCodigo()+" - "+ccDB.getNombre());
        */
        /*
        //--UPDATE
        var cc =service.findByCodigo("06");
        System.out.println("Antes");
        System.out.println(cc.getId()+"  - " + cc.getCodigo()+" - "+cc.getNombre());
        cc.setNombre("CC Test");
        var ccDB = service.save(cc);
        System.out.println("Despues");
        System.out.println(ccDB.getId()+"  - " + ccDB.getCodigo()+" - "+ccDB.getNombre());
        */
        //--DELETE
        var cc =service.findByCodigo("06");//READ
        System.out.println("Antes");
        System.out.println(cc.getId()+"  - " + cc.getCodigo()+" - "+cc.getNombre());
        cc.setNombre("CC Test");
        service.deleteById(cc.getId());
        //READ
        lista= (List<CentroCostos>) service.findAll();
        for (CentroCostos c : lista)
            System.out.println(c.getCodigo()+" - "+ c.getNombre()+" - ");

    }
    private void testCRUDCentroCostos() {
        //CRUD Simple - CrudRepository - Spring data
        //READ
        List<CentroCostos> lista= (List<CentroCostos>) repository.findAll();
        for (CentroCostos c : lista)
            System.out.println(c.getCodigo()+" - "+ c.getNombre()+" - ");

        //----CREATE
        //var cc1= new CentroCostos(null,"06","CC Prueba");
        //cc1.setCodigo("06");
        //cc1.setNombre("CC Prueba");
        //var cc1 = CentroCostos.builder().codigo("06").nombre("CC Prueba");
        //var ccDB = repository.save(cc1);
        /*var ccDB = repository.save(
                CentroCostos.builder()
                        .codigo("06")
                        .nombre("CC Prueba").build()
        );
        System.out.println(ccDB.getId()+"  - " + ccDB.getCodigo()+" - "+ccDB.getNombre());
        */
        /*
        //--UPDATE
        var cc =repository.findByCodigo("06").orElse(null);
        if(Objects.nonNull(cc)) {
            System.out.println("Antes");
            System.out.println(cc.getId()+"  - " + cc.getCodigo()+" - "+cc.getNombre());
            cc.setNombre("CC Test");
            var ccDB = repository.save(cc);
            System.out.println("Despues");
            System.out.println(ccDB.getId()+"  - " + ccDB.getCodigo()+" - "+ccDB.getNombre());
        }
        else System.out.println("No existe");
        */
        //--DELETE
        /*var cc =repository.findByCodigo("06").orElse(null);//READ
        if(Objects.nonNull(cc)) {
            System.out.println("Antes");
            System.out.println(cc.getId()+"  - " + cc.getCodigo()+" - "+cc.getNombre());
            cc.setNombre("CC Test");
            repository.deleteById(cc.getId());
            System.out.println("Despues");
            var ccDB =repository.findByCodigo("06").orElse(null);
            if(Objects.isNull(ccDB)) {
                System.out.println("Elemento eliminado, no existe");
            } else System.out.println("Elemento no eliminado, si existe");
        }
        else System.out.println("No existe");
        */
    }
    private void testReadCentroCostos(){
        System.out.println("Hola mundo");
        List<CentroCostos> lista= (List<CentroCostos>) repository.findAll();
        for (CentroCostos c : lista)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        List<CentroCostos> lista2=repository.findByNombre("GENERAL");
        for (CentroCostos c : lista2)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        var  cc = repository.findByCodigo("04").orElse(null);
        System.out.println(cc.getCodigo()+"-"+cc.getNombre());

        List<CentroCostos> lista3=repository.findByNombreStartingWithOrderByCodigoDesc("GRI");
        for (CentroCostos c : lista3)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        List<CentroCostos> lista4=repository.findByIdAfterAndNombreStartingWithIgnoreCase(3,"GRI");
        for (CentroCostos c : lista4)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        var  cc2 = repository.busquedaJPQLxCodigo("05").orElse(null);
        System.out.println(cc2.getCodigo()+"-"+cc2.getNombre());

        List<CentroCostos> lista5=repository.busquedaJPQLxIdxNombrePerzonalizado(3,"GRI");
        for (CentroCostos c : lista5)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());


        var  cc3 = repository.busquedaxCodigo("04").orElse(null);
        System.out.println(cc3.getCodigo()+"-"+cc3.getNombre());

        List<CentroCostos> lista6=repository.busquedaxIdxNombrePerzonalizado(2,"GRI");
        for (CentroCostos c : lista6)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        var  cc4 = repository.findByCodigoNamedFile("05").orElse(null);
        System.out.println(cc4.getCodigo()+"-"+cc4.getNombre());

        List<CentroCostos> lista7=repository.findAllNamedFile();
        for (CentroCostos c : lista7)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        List<CentroCostos> lista8=repository.findAllNamedNativeFile();
        for (CentroCostos c : lista8)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        System.out.println(repository.sampleNamedQuery("04"));
        System.out.println(repository.sampleNamedNativeQuery("05"));

        List<CentroCostos> lista9=repository.findAllXML();
        for (CentroCostos c : lista9)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());

        var  cc5 = repository.buscarxCodigoXML("05").orElse(null);
        System.out.println(cc5.getCodigo()+"-"+cc5.getNombre());

        List<CentroCostos> lista10=repository.findAllNativeXML();
        for (CentroCostos c : lista10)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());


        List<CentroCostos> lista11=repository.findAllJPQL();
        for (CentroCostos c : lista11)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());
        var  cc6 = repository.buscarxCodigoJPQL("05").orElse(null);
        System.out.println(cc6.getCodigo()+"-"+cc6.getNombre());

        List<CentroCostos> lista12=repository.findAllNative();
        for (CentroCostos c : lista12)
            System.out.println(c.getCodigo()+" - "+ c.getNombre());
    } //nomenclatura CamelCase --//Nomenclatura Pascal
}
