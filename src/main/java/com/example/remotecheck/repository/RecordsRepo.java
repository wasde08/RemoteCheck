package com.example.remotecheck.repository;

import com.example.remotecheck.domain.Clients;
import com.example.remotecheck.domain.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class RecordsRepo {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    @Autowired
    private ClientsRepo repository;

    //    public List<Clients> findAll() {
//        Query query = entityManager.createQuery(
//                "select c from Clients c ", Clients.class);
//
//        List<Clients> clients = query.getResultList();
//        return clients;
//    }
    @Transactional
    public Records save(Records records) {
        records.setClients_id(repository.findByName(records.getClients_id().getKey()));
        return entityManager.merge(records);
    }
    @Transactional
    public List<Records> findAllByID(long id) {
        Query query = entityManager.createQuery(
                "select r from Records r where r.clients_id.id = :id order by r.timestamp", Records.class);
        query.setParameter("id", id);
        List<Records> records = query.getResultList();
        return records;
    }
}
