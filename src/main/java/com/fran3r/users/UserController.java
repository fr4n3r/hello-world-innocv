package com.fran3r.users;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Fran Alonso @ byteflair.com
 */
@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {
    private UserService userService;
    private UserResourceValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    public ResponseEntity<User> create(@RequestBody @Valid UserResource from, PersistentEntityResourceAssembler assembler){

        return new ResponseEntity(assembler.toResource(created), HttpStatus.CREATED);
    }
}
