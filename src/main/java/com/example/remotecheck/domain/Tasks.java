package com.example.remotecheck.domain;

import javax.persistence.*;

@Entity
public class Tasks {
    @Id
    @GeneratedValue()
    private Long id;
    @Column
    private String command;
    @Column
    private boolean status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Clients clients_id;

    public Tasks(String command, boolean status, Clients clients_id) {

        this.command = command;
        this.clients_id = clients_id;
        this.status = status;
    }

    public Tasks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Clients getClients_id() {
        return clients_id;
    }

    public void setClients_id(Clients clients_id) {
        this.clients_id = clients_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", command='" + command + '\'' +
                ", status=" + status +
                ", clients_id=" + clients_id +
                '}';
    }
}
