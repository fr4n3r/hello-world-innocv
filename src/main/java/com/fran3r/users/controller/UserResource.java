package com.fran3r.users.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fran3r.boundary.LocalDateDeserializer;
import com.fran3r.boundary.LocalDateSerializer;
import com.fran3r.users.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * @author Fran Alonso @ byteflair.com
 */

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserResource extends ResourceSupport {
    private String name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;

    public UserResource(User user){
        this(user.getName(), user.getBirthDate());
    }

    @JsonCreator
    public UserResource(@JsonProperty("name") String name, @JsonProperty("birthDate") LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }


}
