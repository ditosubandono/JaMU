package com.smartbee.jamu.jpos.server.repository.cif;

import com.smartbee.jamu.jpos.server.domain.Cif;

import java.util.List;

/**
 * Created by galihlasahido on 10/17/14.
 */
public interface CifService {
    public List<Cif> getCifById(String id);
}
