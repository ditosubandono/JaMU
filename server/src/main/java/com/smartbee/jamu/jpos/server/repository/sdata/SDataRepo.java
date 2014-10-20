package com.smartbee.jamu.jpos.server.repository.sdata;

import com.smartbee.jamu.jpos.server.domain.sdata.SCif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by galihlasahido on 10/17/14.
 */
public interface SDataRepo extends JpaRepository<SCif, Long> {
    List<SCif> findByNomorrekening(String nomorrekening);

}
