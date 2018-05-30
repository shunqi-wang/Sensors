package com.example.wanshunq.sensors;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class test {
    public static void main(String[] args) {
        JSONObject names=null;
        String content="";
        try{
            FileReader fr=new FileReader("Test.json");
            BufferedReader br=new BufferedReader(fr);
            String st;
            while ((st=br.readLine())!=null){
                content+=st;
            }
            names=new JSONObject(content);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(content);
    }
}
