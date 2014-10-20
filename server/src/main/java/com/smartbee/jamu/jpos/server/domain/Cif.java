package com.smartbee.jamu.jpos.server.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by galihlasahido on 9/5/14.
 */
public class Cif {
    private Long id;
    private String nomor_rekening;
    private String nama;
    private Integer saldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomor_rekening() {
        return nomor_rekening;
    }

    public void setNomor_rekening(String nomor_rekening) {
        this.nomor_rekening = nomor_rekening;
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
