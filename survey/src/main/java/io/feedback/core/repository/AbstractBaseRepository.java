package io.feedback.core.repository;

import io.feedback.core.entity.AbstractBaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class AbstractBaseRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insertOrUpdate(T entity) {
        if (!(entity instanceof AbstractBaseEntity)) {
            throw new RuntimeException();
        }
        if (((AbstractBaseEntity) entity).getId() == null) {
            getEntityManager().persist(entity);
        }
        else {
            getEntityManager().merge(entity);
        }
    }

    public void delete(T entity) {
        if (!(entity instanceof AbstractBaseEntity)) {
            throw new RuntimeException();
        }
        getEntityManager().remove(entity);
    }
}