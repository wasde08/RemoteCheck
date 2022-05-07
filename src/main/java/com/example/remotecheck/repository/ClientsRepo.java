package com.example.remotecheck.repository;

import com.example.remotecheck.domain.Clients;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ClientsRepo {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    public List<Clients> findAll() {
        Query query = entityManager.createQuery(
                "select c from Clients c ", Clients.class);

        List<Clients> clients = query.getResultList();
        return clients;
    }

    @Transactional
    public Clients save(Clients clients) {
        return entityManager.merge(clients);
    }

    @Transactional
    public Clients findByName(String key) {
        Query query = entityManager.createQuery(
                "select c from Clients c where c.key = :key", Clients.class);
        query.setParameter("key", key);
        List<Clients> clients = query.getResultList();
        return clients.get(0);
    }

    @Transactional
    public Clients findById(long id) {
        Query query = entityManager.createQuery(
                "select c from Clients c where c.id = :id", Clients.class);
        query.setParameter("id", id);
        List<Clients> clients = query.getResultList();
        return clients.get(0);
    }
}

