package io.feedback.core.repository;

import io.feedback.core.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.core.GenericTypeResolver;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class
        AbstractRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T findById(Long id) {
        T entity = getEntityManager().find((Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractRepository.class), id);
        return entity;
    }

    public void insertOrUpdate(T entity) {
        if (!(entity instanceof AbstractEntity)) {
            throw new RuntimeException("Entity is not extending AbstractBaseEntity");
        }
        if (((AbstractEntity) entity).getId() == null) {
            getEntityManager().persist(entity);
        } else {
            getEntityManager().merge(entity);
        }
    }

    public void delete(T entity) {
        if (!(entity instanceof AbstractEntity)) {
            throw new RuntimeException("Entity is not extending AbstractBaseEntity");
        }
        getEntityManager().remove(entity);
    }
}