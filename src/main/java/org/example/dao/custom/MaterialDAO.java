package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Material;

import java.sql.SQLException;
import java.util.List;

public interface MaterialDAO extends CrudDAO<Material> {
    List<String> getNames() throws SQLException, ClassNotFoundException;

    Material materialsList(String name) throws SQLException, ClassNotFoundException;
}
