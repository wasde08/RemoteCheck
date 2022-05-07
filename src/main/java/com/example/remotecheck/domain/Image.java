package com.example.remotecheck.domain;

import javax.persistence.*;


@Entity
public class Image {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String img;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Clients clients_id;


    public Image() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Clients getClients_id() {
        return clients_id;
    }

    public void setClients_id(Clients clients_id) {
        this.clients_id = clients_id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", img='" + img +
                ", clients_id=" + clients_id +
                '}';
    }
}
