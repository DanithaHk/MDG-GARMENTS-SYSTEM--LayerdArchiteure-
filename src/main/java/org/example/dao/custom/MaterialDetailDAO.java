package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.MaterialDetail;

import java.sql.SQLException;
import java.util.List;

public interface MaterialDetailDAO extends CrudDAO<MaterialDetail> {
    MaterialDetail findMaterial(String nededSwingCloth) throws SQLException, ClassNotFoundException;
    public List<String> getNames() throws SQLException, ClassNotFoundException ;
}
