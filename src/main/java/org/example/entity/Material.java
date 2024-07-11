package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Material {
    private String id;
    private String description;
    private int qty;
    private Double costPerOne;
    private String username;
}
