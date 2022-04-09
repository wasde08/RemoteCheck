package com.example.remotecheck.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Records {
    @Id
    @GeneratedValue()
    private Long id;
    @Column
    private Timestamp timestamp;
    @Column
    private Double cpu;
    @Column
    private Double ram;
    @Column
    private String ip;
    @Column
    private Double ssd;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Clients clients_id;

    public Records() {
    }

//    public Records(String timestamp, Double cpu, Double ram, String ip, Double ssd, Clients clients_id) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//        System.out.println("--------------");
//        Date parsedDate = dateFormat.parse(timestamp+"000");
//        System.out.println(dateFormat);
//        Timestamp tm = new java.sql.Timestamp(parsedDate.getTime());
//        System.out.println(tm);
//        System.out.println("--------------");
//
//        this.timestamp = tm;
//        this.cpu = cpu;
//        this.ram = ram;
//        this.ip = ip;
//        this.ssd = ssd;
//        this.clients_id = clients_id;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getRam() {
        return ram;
    }

    public void setRam(Double ram) {
        this.ram = ram;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Double getSsd() {
        return ssd;
    }

    public void setSsd(Double ssd) {
        this.ssd = ssd;
    }

    public Clients getClients_id() {
        return clients_id;
    }

    public void setClients_id(Clients clients_id) {
        this.clients_id = clients_id;
    }

    @Override
    public String toString() {
        return "Records{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", ip='" + ip + '\'' +
                ", ssd=" + ssd +
                ", clients_id=" + clients_id +
                '}';
    }
}
