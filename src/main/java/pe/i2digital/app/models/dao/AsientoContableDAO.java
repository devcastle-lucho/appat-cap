package pe.i2digital.app.models.dao;

import pe.i2digital.app.models.dto.ExcelAsientoContableDTO;

import java.util.List;

public interface AsientoContableDAO {
    public List<ExcelAsientoContableDTO> busquedaExcel(String periodo, Integer id);
}
