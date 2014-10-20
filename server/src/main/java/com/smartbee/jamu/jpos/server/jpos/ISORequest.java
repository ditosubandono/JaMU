package com.smartbee.jamu.jpos.server.jpos;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.LocalSpace;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;
import org.jpos.util.Log;

/**
 * Created by galihlasahido on 9/7/14.
 */
public class ISORequest implements ISORequestListener, Configurable {
    private LocalSpace sp;
    private String queueName;
    private Long timeout;
    private Integer port;
    private Configuration cfg;
    private Log log = Log.getLog("Q2","Q2");

    public static final String REQUEST   = "REQUEST";
    public static final String ISOSOURCE = "ISOSOURCE";

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        Context ctx = new Context ();
        ctx.put(REQUEST, m);
        ctx.put(ISOSOURCE, source);
        sp.out (queueName, ctx, timeout);
        return true;
    }

    @Override
    public void setConfiguration(Configuration cfg) throws ConfigurationException {
        this.cfg = cfg;
        String spaceName = cfg.get("space");
        sp = (LocalSpace) SpaceFactory.getSpace(spaceName);
        queueName = cfg.get("queue");
        timeout = cfg.getLong("timeout");
    }
}
