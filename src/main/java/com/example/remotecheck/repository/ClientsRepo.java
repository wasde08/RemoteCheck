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
        System.out.println(query);
        query.setParameter("key", key);
        List<Clients> clients = query.getResultList();
        return clients.get(0);
    }
//    @Transactional
//    public void saveSP(Long product_id, Long fk_seller) {
//        String sql = "INSERT INTO seller_product (seller_seller_id, products_product_id) VALUES (?, ?)";
//        entityManager.createNativeQuery(sql)
//                .setParameter(1, fk_seller)
//                .setParameter(2, product_id)
//                .executeUpdate();
//    }

//    public Product findByName(String name) {
//        Query query = entityManager.createQuery(
//                "select p from Product p where p.productName like :productName ", Product.class);
//        query.setParameter("productName", name);
//        List<Product> products = query.getResultList();
//        Product product = products!=null?products.get(0):null;
//        return product;
//    }
//
//    public List<Product> findBySellerName(String name) {
//        Query query = entityManager.createQuery(
//                "select p from Product p where p.fk_seller.name like :sellerName ", Product.class);
//        query.setParameter("sellerName", name);
//        List<Product> products = query.getResultList();
//        return products;
//    }
//    @Transactional
//    public boolean removeProduct(String name) {
//        Product product = findByName(name);
//        String sql =
//                "delete from product where product_id=? ";
//        entityManager.createNativeQuery(sql)
//                .setParameter(1, product.getProduct_id())
//                .executeUpdate();
//        return true;
//    }
//    @Transactional
//    public boolean delSelProd(Long id) {
//        System.out.println(id);
//        String sql =
//                "delete from seller_product where products_product_id=? ";
//        entityManager.createNativeQuery(sql)
//                .setParameter(1, id)
//                .executeUpdate();
//        return true;
//    }
//
//    @Transactional
//    public Product update(Product product) {
//        if (product == null ) return null;
//        if (product.getProduct_id() != null) {
//            product = entityManager.merge(product);
//        } else {
//            entityManager.persist(product);
//        }
//        return product;
//    }
//
//    public List<ProductData> findByNameView(String name) {
//        /*Query query = entityManager.createQuery(
//                "select new ProductData(p.product_id, p.productName) from " +
//                        "Product p where p.productName like :name ", ProductData.class);
//        query.setParameter("name", '%'+name+'%');
//        List<ProductData> products = query.getResultList();
//        return products;*/
//        Query query = entityManager.createQuery(
//                " select p from ProductData p ", ProductData.class);
//        List<ProductData> products = query.getResultList();
//        return products;
//    }
}

