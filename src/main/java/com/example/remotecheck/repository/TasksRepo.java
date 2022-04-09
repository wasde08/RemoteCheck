package com.example.remotecheck.repository;

import com.example.remotecheck.domain.Clients;
import com.example.remotecheck.domain.Records;
import com.example.remotecheck.domain.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class TasksRepo {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    @Autowired
    private ClientsRepo repository;

    public List<Tasks> findCommandsByKey(String key) {
        Query query = entityManager.createQuery(
                "select t from Tasks t where t.clients_id.key = :key and t.status=false", Tasks.class);
        query.setParameter("key", key);
        List<Tasks> tasks = query.getResultList();
        return tasks;
    }

    @Transactional
    public Tasks update(Tasks tasks) {
        tasks.setClients_id(repository.findByName(tasks.getClients_id().getKey()));
        entityManager.merge(tasks);
        return tasks;
    }

//    @Transactional
//    public Records save(Records records) {
//        if (records != null) {
//            records = entityManager.merge(records);
//        }
//        return records;
//    }
}
