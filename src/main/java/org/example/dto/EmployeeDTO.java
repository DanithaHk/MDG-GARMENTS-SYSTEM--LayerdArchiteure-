package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeDTO {
    private String id;
    private String name;
    private String address;
    private String contactNumber;
    private String jobRole;
    private String username;
}
