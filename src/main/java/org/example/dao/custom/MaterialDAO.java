package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Material;

import java.sql.SQLException;

public interface MaterialDAO extends CrudDAO<Material> {
    Material materialsList(String name) throws SQLException, ClassNotFoundException;
}
