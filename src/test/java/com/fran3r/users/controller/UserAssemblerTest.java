package com.fran3r.users.controller;

import com.fran3r.users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

/**
 * @author Fran Alonso @ byteflair.com
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserAssemblerTest {

    @Test
    public void thatCanConvertToResource() {
        UserAssembler userAssembler = new UserAssembler();
        UserResource resource = userAssembler.toResource(User.builder().id("1")
            .name("Jhon")
        .birthDate(LocalDate.of(1, 1, 1)).build());

        Assert.assertNotNull(resource);
        Assert.assertNotNull(resource.getId());
        Assert.assertEquals("Jhon", resource.getName());
        Assert.assertEquals(LocalDate.of(1, 1, 1), resource.getBirthDate());
    }

    @Test
    public void thatCanConvertToEntity() {
        UserAssembler userAssembler = new UserAssembler();
        User entity = userAssembler.toEntity(new UserResource("Jhon", LocalDate.of(1, 1, 1)), "1");

        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getId(), "1");
        Assert.assertEquals("Jhon", entity.getName());
        Assert.assertEquals(LocalDate.of(1, 1, 1), entity.getBirthDate());
    }
}
