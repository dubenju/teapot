package org.teapot.servlet.view.rythm;

import java.util.HashMap;
import java.util.Map;

import org.rythmengine.Rythm;

public class RythmSample {

    public static void main(String[] args) {
        // System.out.println(Rythm.render("hello @who!", "rythm"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("home.template", "./template/rythm");
//        Rythm ry = new Rythm();
        Rythm.init(map);
        System.out.println(Rythm.render("helloworld.html", "World"));
    }

}
