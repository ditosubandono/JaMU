package com.smartbee.jamu.jpos.client.web;

import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.q2.QBeanSupport;
import org.jpos.q2.iso.QMUX;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.space.SpaceUtil;
import org.jpos.util.NameRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by galihlasahido on 9/7/14.
 */
@Controller
public class AtmSimulation extends QBeanSupport {
    public Space psp    = SpaceFactory.getSpace("jdbm:myspace");
    public static final String TRACE = "JPTS_TRACE";

    @RequestMapping(value = "/dashboard/main.menu", method = RequestMethod.GET)
    public String firshBoard(Model model) {
        getLog().info(psp.rdp("account"));
        model.addAttribute("acc", psp.rdp("account"));
        return "screen/main.menu";
    }

    @RequestMapping(value = "/dashboard/account.action", method = RequestMethod.POST)
    public String accountAction(HttpServletRequest request) {
        String accountNumber = request.getParameter("accountNumber");
        psp.put("account", accountNumber);
        return "redirect:/dashboard/main.menu";
    }

    @RequestMapping(value = {"/","/dashboard","/dashboard/opening.menu"}, method = RequestMethod.GET)
    public String staticBoard() {
        return "screen/opening.screen";
    }

    @RequestMapping(value = "/dashboard/inquiry/{acc}", method = RequestMethod.GET)
    public String infoSaldo(Model model, @PathVariable("acc") String acc) throws NameRegistrar.NotFoundException {
        QMUX mux = (QMUX) NameRegistrar.get("mux.clientsimulator-mux");
        ISOMsg m = new ISOMsg ();
        long traceNumber = SpaceUtil.nextLong(psp, TRACE) % 100000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmssS");

        try {
            m.setMTI("0200");
            m.set(2, "0000000000000000000");
            m.set(3, "370000");
            m.set(4, ISOUtil.zeropad("0", 12));
            m.set(7, ISODate.getDateTime(new Date()));
            m.set(11, ISOUtil.zeropad(traceNumber, 6));
            m.set(12, ISODate.getTime(new Date()));
            m.set(13, ISODate.getDate(new Date()));
            m.set(15, ISODate.getDate(new Date()));
            m.set(18, "6010");
            m.set(32, ISOUtil.zeropad("111", 11));
            m.set(33, ISOUtil.zeropad("111", 11));
            m.set(37, ISOUtil.zeropad(String.valueOf(simpleDateFormat.format(new Date())).substring(0, 12), 12));
            m.set(49, "360");
            m.set(98, "000000");
            m.set(102, "           ");
            m.set(103, acc);
            ISOMsg response = mux.request(m, 30000);
            if(response==null)
                model.addAttribute("message", "TIDAK ADA RESPONSE DARI SERVER  : ");
            else
                model.addAttribute("message", "JUMLAH SALDO ANDA  : "+
                        Integer.valueOf(response.getString(4)) );

        } catch (ISOException e) {
            model.addAttribute("message", e.toString());
        }

        model.addAttribute("acc", acc);

        return "screen/main.menu";
    }


}
