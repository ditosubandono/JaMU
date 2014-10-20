package com.smartbee.jamu.jpos.server.repository.sdata;

import com.smartbee.jamu.jpos.server.domain.sdata.SCif;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by galihlasahido on 10/17/14.
 */
@Service
public class SDataCifImpl implements SDataCifService {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired(required = true)
    private SDataRepo repo;

    @Override
    public List<SCif> getCifById(String id) {
        if (id==null) {
            return null;
        }

        return repo.findByNomorrekening(id);
    }
}
