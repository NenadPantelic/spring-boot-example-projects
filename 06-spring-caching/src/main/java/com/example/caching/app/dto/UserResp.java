package com.example.caching.app.dto;

import com.example.caching.app.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
    private AddressResp address;
}
