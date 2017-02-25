package com.fran3r.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Identifiable;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Document(collection = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Identifiable<String>, Serializable {

    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
}
