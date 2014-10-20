package com.smartbee.jamu.jpos.server.jpos;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;

import java.io.Serializable;

/**
 * Created by galihlasahido on 9/7/14.
 */
public class Selector implements GroupSelector, Configurable {
    private Configuration cfg;

    @Override
    public void setConfiguration(Configuration cfg) throws ConfigurationException {
        this.cfg = cfg;
    }

    @Override
    public String select(long id, Serializable context) {
        String groups = "";
        try {
            ISOMsg m = (ISOMsg) ((Context)context).get("REQUEST");
            try {
                groups = cfg.get (m.getMTI().concat(m.getString(3)), null);
            } catch (ISOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groups;

    }

    @Override
    public int prepare(long id, Serializable context) {
        return PREPARED;
    }

    @Override
    public void commit(long id, Serializable context) {

    }

    @Override
    public void abort(long id, Serializable context) {

    }
}
