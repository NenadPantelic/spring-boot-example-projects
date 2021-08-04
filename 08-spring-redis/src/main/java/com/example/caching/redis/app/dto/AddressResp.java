package com.example.caching.redis.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RedisHash("Address")
public class AddressResp implements Serializable {
    private Long id;
    private String country;
    private String city;
    private Integer zipCode;
    private String street;
    private Integer flatNumber;
}