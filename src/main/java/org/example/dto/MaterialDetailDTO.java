package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialDetailDTO {
    private String oId;
    private String desc;
    private String mId;
    private int materialQty;
}
