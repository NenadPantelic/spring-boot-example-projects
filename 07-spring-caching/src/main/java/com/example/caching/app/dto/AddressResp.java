package com.example.caching.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResp {
    private Long id;
    private String country;
    private String city;
    private Integer zipCode;
    private String street;
    private Integer flatNumber;
}
