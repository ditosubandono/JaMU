package com.smartbee.jamu.jpos.server.repository.sdata;

import com.smartbee.jamu.jpos.server.domain.sdata.SCif;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by galihlasahido on 10/17/14.
 */
@Component
public interface SDataCifService {
    public List<SCif> getCifById(String id);
}
