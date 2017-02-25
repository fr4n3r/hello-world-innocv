package com.fran3r.users.controller;

import com.fran3r.boundary.ResourceUtils;
import com.fran3r.users.User;
import com.fran3r.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fran Alonso @ byteflair.com
 */
@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    private UserResourceValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<UserResource> create(@RequestBody @Valid UserResource form, PersistentEntityResourceAssembler assembler){

        User created = userService.create(User.builder()
                                              .name(form.getName())
                                              .birthDate(form.getBirthDate())
                                              .build());
        return new ResponseEntity(assembler.toResource(created), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get/{id}")
    public ResponseEntity<UserResource> get(String id, PersistentEntityResourceAssembler assembler){
        User user = userService.get(id);
        return new ResponseEntity(assembler.toResource(user), HttpStatus.FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getall")
    public ResponseEntity<List<UserResource>> getAll(PersistentEntityResourceAssembler assembler){
        List<User> userList = userService.getAll();

        return new ResponseEntity(userList.stream().map(assembler::toResource).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update")
    public ResponseEntity<UserResource> update(@RequestBody @Valid UserResource form, PersistentEntityResourceAssembler assembler){

        //Get the id from the link
        String id =  ResourceUtils.getIdFromRel(form, "user");
        User updated = userService.update(User.builder()
                                            .id(id)
                                            .name(form.getName())
                                            .birthDate(form.getBirthDate()).build());
        return new ResponseEntity(updated, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public ResponseEntity delete(String id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
