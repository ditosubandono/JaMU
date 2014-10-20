package com.smartbee.jamu.jpos.server.domain.sdata;

import javax.persistence.*;

/**
 * Created by galihlasahido on 9/5/14.
 */
@Entity
@Table(name = "cif")
public class SCif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomorrekening;
    private String nama;
    private Integer saldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomorrekening() {
        return nomorrekening;
    }

    public void setNomorrekening(String nomorrekening) {
        this.nomorrekening = nomorrekening;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }
}
