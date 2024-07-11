package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.OrderDetailDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
        private String oId;
        private String name;
        private String date;
        private int qty;
        private String cid;
}
