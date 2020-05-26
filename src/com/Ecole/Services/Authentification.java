/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ecole.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hamma
 */
public class Authentification {
    
    
           public void Login(String Login, String Password) {
        try {
            ConnectionRequest r = new ConnectionRequest();
            System.out.println("1");
            r.setPost(false);
            System.out.println("2");
            r.setUrl("http://127.0.0.1:8000/logi/");
            System.out.println("3");
            r.addArgument("username", Login);
            r.addArgument("password", Password);
            System.out.println("4");
            NetworkManager.getInstance().addToQueueAndWait(r);
            System.out.println("5");
            if (r.getResponseCode() == 200) {
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                java.util.List<Map<String, Object>> content = (java.util.List<Map<String, Object>>) result.get("root");
                String access = (String) content.get(0).get("Access");
                List<String> roles =  (List<String>) content.get(0).get("Roles");

                System.out.println(access);
                if(access.equalsIgnoreCase("granted")){
                    System.out.println("Access Garanted ! ");
                    System.out.println("Roles" + roles);
                }
                

            } else if (r.getResponseCode() == 400) {
                System.out.println("Access Denied");;
            } else {
                System.out.println("Access Denieeed");

            }
        } catch (Exception err) {
            Log.e(err);
        }
    }
}