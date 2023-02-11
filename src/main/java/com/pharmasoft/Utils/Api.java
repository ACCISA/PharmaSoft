package com.pharmasoft.Utils;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import com.pharmasoft.Entities.Session;
import org.json.JSONObject;


public class Api {
    public boolean verifyLogin(String employee_id, String password) throws IOException {
        String byteData ="{\"employee_id\":\""+employee_id+"\",\"password\":\""+ password+"\"}";
        byte[] out = byteData.getBytes(StandardCharsets.UTF_8);
        if (APICall("http://127.0.0.1:5000/login", out, true)) return true;
        return false;

    }
    private boolean APICall(String urls, byte[] out,boolean login) throws IOException {
        URL url = new URL(urls);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type","application/json; charset=UTF-8");

        http.connect();
        try(OutputStream os = http.getOutputStream()){
            os.write(out);
        }


        try(InputStream is = http.getInputStream()){
            BufferedReader in = new BufferedReader( new InputStreamReader(is));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
            JSONObject myResponse = new JSONObject(response.toString());
            if (login){
                String message = myResponse.getString("message");
                String token = myResponse.getString("token");
                if (token.equals("none") && message.equals("information_missing")){
                    System.out.println("[App] Information Missing, login failed");
                    return false;
                }
                if (token.equals("none") && message.equals("invalid_login")){
                    System.out.println("[App] Invalid Credentials, login failed");
                    return false;
                }
                if ( !(token.equals("none")) && message.equals("logged_in")){
                    System.out.println("[App] User Logged in");
                    Session loggedin = new Session(token);
                    Session.cur_session = loggedin;
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    private String[] APICall(String apiLocation, String method, String[] args){
        String urlx = "http://127.0.0.1:5000/"+apiLocation;

        for (int i = 0; i < args.length; i++){
            urlx+=args[i];
        }

        try{
            URL url = new URL(urlx);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
//            conn.connect();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();
            boolean ignore = true;
            if (!(ignore)){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print in String
                System.out.println(response.toString());
                //Read JSON response and print
                JSONObject myResponse = new JSONObject(response.toString());
                String resto = myResponse.getString("restaurant");
                String dish = myResponse.getString("dish");
                String price = myResponse.getString("price");
                String size = myResponse.getString("size");
                String urlBckgrnd = myResponse.getString("url");
                return new String[]{resto,dish,price,size,urlBckgrnd};

            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return new String[]{};
    }

    /**
     * Make an API request to create an account. It will check if the username doesnt already have an account.
     * If not the api will store the account creation in to the accounts database.
     *
     * @return will return true if the account creation was successful
     */
    public void AccountCreation(String username, String password){
        try{
            String urlx = "http://127.0.0.1:5000/createAccount?username="+username+"&password="+password;
            URL url = new URL(urlx);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.connect();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();
            boolean ignore = true;
            if (!(ignore)){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print in String
                System.out.println(response.toString());
                //Read JSON response and print
                JSONObject myResponse = new JSONObject(response.toString());
//                System.out.println("statusCode- "+myResponse.getString("warning"));

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String[] SendData(String[] fields){
        String[] dataSend = new String[fields.length];
        for (int i = 0; i < fields.length; i++){
            if (i == 0){
                dataSend[0] = "?f1="+fields[0];
                continue;
            }
            dataSend[i] = "&f"+(i+1)+"="+fields[i];
        }
        return APICall("sendDataManual","GET",dataSend);
    }

}