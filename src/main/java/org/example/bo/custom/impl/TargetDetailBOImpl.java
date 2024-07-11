package org.example.bo.custom.impl;

import org.example.bo.custom.TargetDetailBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.TargetDAO;
import org.example.dao.custom.TargetDetailDAO;
import org.example.dto.TargetDetailDTO;
import org.example.entity.Target;
import org.example.entity.TargetDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class TargetDetailBOImpl implements TargetDetailBO {
    TargetDetailDAO targetDetailDAO = (TargetDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.TARGETDETAIL);
    TargetDAO targetDAO = (TargetDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.TARGET);

    @Override
    public boolean saveTargetDeatil(TargetDetailDTO targetDetailDTO) throws SQLException, ClassNotFoundException {
        return targetDetailDAO.add(new TargetDetail(targetDetailDTO.getId(),targetDetailDTO.getName(),targetDetailDTO.getDate(),targetDetailDTO.getTCoverOrNot(),targetDetailDTO.getEid()));
    }

    @Override
    public ArrayList<TargetDetailDTO> getAllTarget() throws SQLException, ClassNotFoundException {
        ArrayList<TargetDetailDTO> targetDetailDTOS = new ArrayList<>();
        ArrayList <TargetDetail> targetDetails = targetDetailDAO.getAll();
        for(TargetDetail t : targetDetails) {
            TargetDetailDTO targetDetailDTO = new TargetDetailDTO(t.getId(),t.getName(),t.getDate(),t.getTCoverOrNot(),t.getEid());
            targetDetailDTOS.add(targetDetailDTO);
        }

        return targetDetailDTOS;    }

    @Override
    public TargetDetailDTO searchTarget(String id) throws SQLException, ClassNotFoundException {
        TargetDetail targetDetail = targetDetailDAO.search(id);
        return (new TargetDetailDTO(targetDetail.getId(),targetDetail.getName(),targetDetail.getDate(),targetDetail.getTCoverOrNot(),targetDetail.getEid()));

    }

    @Override
    public Target getNames(String id) throws SQLException, ClassNotFoundException {
        return targetDAO.search(id);
    }

    @Override
    public boolean updateTargetDetail(TargetDetailDTO dto) throws SQLException, ClassNotFoundException {
        return targetDetailDAO.update(new TargetDetail(dto.getId(),dto.getName(),dto.getDate(),dto.getTCoverOrNot(),dto.getEid()));
    }
}
