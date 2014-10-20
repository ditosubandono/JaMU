package com.smartbee.jamu.jpos.server.jpos;

import com.smartbee.jamu.jpos.server.domain.Cif;
import com.smartbee.jamu.jpos.server.domain.sdata.SCif;
import com.smartbee.jamu.jpos.server.repository.cif.CifService;
import com.smartbee.jamu.jpos.server.repository.sdata.SDataCifImpl;
import com.smartbee.jamu.jpos.server.repository.sdata.SDataCifService;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.AbortParticipant;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.NameRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by galihlasahido on 9/7/14.
 */
public class Inquiry implements TransactionParticipant, AbortParticipant, Configurable {

    @Autowired
    private SDataCifService cifService;

    @Autowired
    private CifService service;

    @Override
    public int prepareForAbort(long id, Serializable context) {
        return 0;
    }

    @Override
    public void setConfiguration(Configuration cfg) throws ConfigurationException {

    }

    @Override
    public int prepare(long id, Serializable context) {
        AnnotationConfigApplicationContext apctx = (AnnotationConfigApplicationContext)
                NameRegistrar.getIfExists("jdbc");
        cifService = apctx.getBean(SDataCifService.class);
//        service = apctx.getBean(CifService.class);

        return PREPARED;
    }

    @Override
    public void commit(long id, Serializable context) {
        ISOMsg m = (ISOMsg) ((Context)context).get("REQUEST");
        ISOSource source = (ISOSource) ((Context)context).get("ISOSOURCE");
        String acc  = m.getString(103);

        List<SCif> cifById = cifService.getCifById(acc);
//        List<Cif> cifById = service.getCifById(acc);
        try {
            if(cifById.size()>0) {
                for(SCif cif : cifById) {
//                for(Cif cif : cifById) {
                    m.set(4, String.valueOf(cif.getSaldo()));
                }
                m.set(39, "00");
            } else {
                m.set(39, "39");
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }

        try {
            m.setResponseMTI();
            source.send(m);
        } catch (ISOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void abort(long id, Serializable context) {

    }
}
