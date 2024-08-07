package io.feedback.core.repository;

import io.feedback.core.entity.AbstractEntity;
import io.feedback.wrapper.org.springframework.core.GenericTypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class AbstractRepository<T extends AbstractEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    private GenericTypeResolver genericTypeResolver;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GenericTypeResolver getGenericTypeResolver() {
        return genericTypeResolver;
    }

    @Autowired
    public void setGenericTypeResolver(GenericTypeResolver genericTypeResolver) {
        this.genericTypeResolver = genericTypeResolver;
    }

    public T findById(Long id) {
        Class<T> entityClass = getGenericTypeResolver().resolveTypeArgument(getClass(), AbstractRepository.class);
        return getEntityManager().find(entityClass, id);
    }

    public void insertOrUpdate(T entity) {
        if (entity.getId() == null) {
            getEntityManager().persist(entity);
        } else {
            getEntityManager().merge(entity);
        }
    }

    public void delete(T entity) {
        getEntityManager().remove(entity);
    }
}