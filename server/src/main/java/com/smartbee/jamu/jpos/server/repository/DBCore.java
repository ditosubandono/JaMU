package com.smartbee.jamu.jpos.server.repository;

import com.smartbee.jamu.jpos.server.config.Database;
import com.smartbee.jamu.jpos.server.config.SDatabase;
import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by galihlasahido on 10/17/14.
 */
public class DBCore extends QBeanSupport {
    @Override
    protected void initService() {
//        ApplicationContext ctx = new
// AnnotationConfigApplicationContext(Database.class);
        ApplicationContext ctx = new
                AnnotationConfigApplicationContext(SDatabase.class);
        NameRegistrar.register("jdbc", ctx);

    }

}
