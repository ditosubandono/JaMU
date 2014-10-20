package com.smartbee.jamu.jpos.server.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by galihlasahido on 9/5/14.
 */
@Controller
public class Dashboard {

    @RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
    public @ResponseBody String staticBoard() {
        return "dashboard/index";
    }

}
