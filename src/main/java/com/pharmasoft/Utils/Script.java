package com.pharmasoft.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Script {

    private String path;
    private String[] args;

    public Script(String path, String[] args){
        this.path = path;
        this.args = args;
    }

    public ArrayList<String> Run() throws IOException {
        String s;
        String [] cmd = new String[args.length+2];
        cmd[0] = "python3";
        cmd[1] = path;
        for (int i = 2; i < cmd.length; i++){
            cmd[i] = args[i-2];
        }
        Runtime r = Runtime.getRuntime();

        Process p = r.exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        ArrayList<String> returnArray = new ArrayList<String>();
        while((s=in.readLine()) != null){
            System.out.println(s);
            if (s.equals("None")) continue;
            returnArray.add(s);
        }
        return returnArray;
    }

}
