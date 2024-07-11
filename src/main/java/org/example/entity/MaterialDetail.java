package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialDetail {
    private String oId;
    private String desc;
    private String mId;
    private int materialQty;
}
