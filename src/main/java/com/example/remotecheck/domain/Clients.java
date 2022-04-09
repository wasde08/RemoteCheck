package com.example.remotecheck.domain;

import javax.persistence.*;

@Entity
@Table(name="clients")
public class Clients {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(length=20)
    private String key;

    public Clients() {
    }

    public Clients( String key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", key='" + key + '\'' +
                '}';
    }
}
