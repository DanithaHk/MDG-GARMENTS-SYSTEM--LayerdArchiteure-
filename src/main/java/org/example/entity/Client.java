package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    private String id;
    private String name;
    private String address;
    private String contactNumber;
    private String email;
}
