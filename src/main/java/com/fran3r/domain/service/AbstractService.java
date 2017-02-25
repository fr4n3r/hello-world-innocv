package com.fran3r.domain.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.hateoas.Identifiable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.function.Consumer;
/**
 * @author Fran Alonso @ byteflair.com
 */
public abstract class AbstractService<T extends Serializable & Identifiable<ID>, ID extends Serializable> {

    public T create(T entity, Consumer<T> function) {
        Assert.isNull(entity.getId(), "Unable to create new entity with given id");

        T created;

        try {
            created = getRepository().insert(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't create " + entity.getClass().getSimpleName() + " entity " + entity, e);
        }

        function.accept(created);

        return created;
    }

    public abstract MongoRepository<T, ID> getRepository();

    public T update(T entity, Consumer<T> function) {
        Assert.notNull(entity);

        T saved;

        try {
            saved = getRepository().save(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't update entity with id " + entity.getId(), e);
        }

        function.accept(saved);

        return saved;
    }

    public void delete(ID id, Consumer<T> function) {
        T current = get(id);
        if (current == null) {
            throw new RuntimeException("Couldn't delete null entity with id " + id);
        }
        try {
            getRepository().delete(current);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't delete entity with id " + id, e);
        }
        function.accept(current);
    }

    public T get(ID id) {
        T entity;

        try {
            entity = getRepository().findOne(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't get entity with id " + id, e);
        }

        return entity; // entity puede devolver null
    }
}
