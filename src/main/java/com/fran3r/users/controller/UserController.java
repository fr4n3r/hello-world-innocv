package com.fran3r.users.controller;

import com.fran3r.users.User;
import com.fran3r.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
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
    private UserAssembler userAssembler;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> create(@RequestBody @Valid UserResource form){

        User created = userService.create(userAssembler.toEntity(form, null));

        return new ResponseEntity(userAssembler.toResource(created), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<UserResource> get(@PathVariable String id){
        User user = userService.get(id);
        return new ResponseEntity(userAssembler.toResource(user), HttpStatus.FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PagedResources<UserResource>> getAll(){
        List<User> userList = userService.getAll();

        return new ResponseEntity(userList.stream().map(userAssembler::toResource).collect(Collectors.toList()), HttpStatus.FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<UserResource> update(@RequestBody @Valid UserResource form, @PathVariable String id){

        User user = userAssembler.toEntity(form, id);

        User updated = userService.update(user);
        return new ResponseEntity(userAssembler.toResource(updated), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity delete(@PathVariable String id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
