package com.kryvapust.articlesblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@ToString(includeFieldNames = false, onlyExplicitlyIncluded = true)

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Integer id;

    @ToString.Include
    private String firstName;

    @ToString.Include
    private String lastName;

    @NonNull
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    private Date created;
}
