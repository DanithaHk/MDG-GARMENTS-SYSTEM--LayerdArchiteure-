package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersDTO {

        private String oId;
        private String name;
        private String date;
        private int qty;
        private String cid;
}
