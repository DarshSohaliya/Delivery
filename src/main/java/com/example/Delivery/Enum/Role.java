package com.example.Delivery.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {

    ADMIN,
    MANAGER,
    AGENT,
    CUSTOMER;

    @JsonCreator
    public static Role from(String value){
         return Role.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String getValue(){
        return name();
    }
}
