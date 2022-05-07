package com.example.remotecheck.repository;

import com.example.remotecheck.domain.Image;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ImageRepo {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    @Transactional
    public void save(Image image) {
        entityManager.persist(image);
    }

    @Transactional
    public Image findById(long id) {
        Query query = entityManager.createQuery(
                "select i from Image i where i.clients_id.id = :id order by i.id desc", Image.class);
        query.setParameter("id", id);
        List<Image> images = query.getResultList();
        return images.get(0);
    }
}
