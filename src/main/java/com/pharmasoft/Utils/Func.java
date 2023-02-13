package com.pharmasoft.Utils;

import java.util.HashMap;
import java.util.Map;

public class Func {
    /**
     * Jsonifies a hashmap for Api calls
     * @return json String
     */
    public static String jsonify(HashMap<String,String> data) throws Exception {
        String build = "{";
        int count = 0;
        int max = data.size();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (count+1==max){
                build = build + "\"" + key + "\":\"" + value + "\"";
                return build;

            }
            build = build + "\"" + key + "\":\"" + value + "\",";
            count++;
        }

        throw new Exception("Error jsonifying");
    }

}
