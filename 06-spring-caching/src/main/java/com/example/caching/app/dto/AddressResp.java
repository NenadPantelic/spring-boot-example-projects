package com.example.caching.app.dto;

import com.example.caching.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
