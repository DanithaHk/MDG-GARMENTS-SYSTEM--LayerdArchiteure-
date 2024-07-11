package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.TargetDetailDTO;
import org.example.entity.Target;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TargetDetailBO extends SuperBO {
    boolean saveTargetDeatil(TargetDetailDTO targetDetailDTO) throws SQLException, ClassNotFoundException;

    ArrayList<TargetDetailDTO> getAllTarget() throws SQLException, ClassNotFoundException;

    TargetDetailDTO searchTarget(String id) throws SQLException, ClassNotFoundException;

    Target getNames(String id) throws SQLException, ClassNotFoundException;

    boolean updateTargetDetail(TargetDetailDTO dto) throws SQLException, ClassNotFoundException;
}
