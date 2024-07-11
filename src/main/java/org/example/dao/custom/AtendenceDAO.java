package org.example.dao.custom;

import org.example.dao.AttendenceNesessaryDAO;
import org.example.dao.CrudDAO;
import org.example.entity.Atendence;

public interface AtendenceDAO extends CrudDAO<Atendence>, AttendenceNesessaryDAO {
}
