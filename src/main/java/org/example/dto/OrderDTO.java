package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
        private String oId;
        private String name;
        private String date;
        private int qty;
        private String cid;
        List<OrderDetailDTO> orderDetailDTOS;
}
