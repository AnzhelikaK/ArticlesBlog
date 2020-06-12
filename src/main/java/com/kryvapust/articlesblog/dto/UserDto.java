package com.kryvapust.articlesblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder(setterPrefix = "set")


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date created;
}
