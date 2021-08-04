package com.example.caching.redis.app.service.utils;

import com.example.caching.redis.app.dao.AddressRepository;
import com.example.caching.redis.app.dao.UserRepository;
import com.example.caching.redis.app.model.Address;
import com.example.caching.redis.app.model.User;
import com.example.caching.redis.app.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DbSeed {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Address> populateAddresses(int numOfRecords) {
        log.info("Generating random address....");
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < numOfRecords; i++) {
            Address address = Address.builder().city(RandomUtils.generateRandomText(20, true))
                    .street(RandomUtils.generateRandomText(20, false))
                    .zipCode(RandomUtils.getRandomPositiveInt(5000))
                    .flatNumber(RandomUtils.getRandomPositiveInt(100))
                    .build();
            addresses.add(addressRepository.save(address));
        }
        return addresses;
    }

    public void populateUsers(int numOfRecords, List<Address> addresses) {
        log.info("Generating random users....");
        String[] usernames = {"john_doe", "ada_lovelace", "joker123", "KD123", "do100jewsky"};
        for (int i = 0; i < numOfRecords; i++) {
            String firstName = RandomUtils.generateRandomText(50, false);
            String lastName = RandomUtils.generateRandomText(50, false);
            String username = RandomUtils.generateRandomText(50, false);
            String email = RandomUtils.generateRandomText(50, false);
            Address address = (Address) RandomUtils.getRandomAddress(addresses);
            User user = User
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .username(username)
                    .email(email)
                    .address(address)
                    .build();
            userRepository.save(user);
        }

    }
}
