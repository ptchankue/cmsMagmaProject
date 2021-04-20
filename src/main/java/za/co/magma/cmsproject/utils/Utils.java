package za.co.magma.cmsproject.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    Map<String, java.util.Map<String, String>> mapWebsites = new HashMap<>();

    public Map getWebsiteMap(){

        Map<String, String> w3cc = new HashMap<>();
        w3cc.put("title", "My Car");
        w3cc.put("header", "My Car");
        w3cc.put("footer", "My footer information");

        Map<String, String> hotel = new HashMap<>();

        mapWebsites.put("w3css", w3cc);
        mapWebsites.put("hotel", hotel);
        return mapWebsites;
    }
}
