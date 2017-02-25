package com.fran3r.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Value
public class UserResource extends ResourceSupport {
    private String name;
    private LocalDate birthDate;

    @JsonCreator
    public UserResource(@JsonProperty("name") String name, @JsonProperty("birthDate") LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
