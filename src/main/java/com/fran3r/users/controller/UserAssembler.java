package com.fran3r.users.controller;

import com.fran3r.users.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Component
public class UserAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = createResourceWithId(user.getId(), user);

        resource.setName(user.getName());
        resource.setBirthDate(user.getBirthDate());

        return resource;
    }

    public User toEntity(UserResource userResource, String id){
        return User.builder().id(id)
                   .name(userResource.getName())
                   .birthDate(userResource.getBirthDate())
                   .build();
    }

}
