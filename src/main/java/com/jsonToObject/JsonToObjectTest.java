package com.jsonToObject;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;

/**
 * Created by Ivan on 2016/7/18.
 */
public class JsonToObjectTest {

    public static String getResult(String path) {
        File file = new File(path);
        String result="";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));

            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                result+=tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
       return  result;
    }

    public static void main(String args[]) {
        String temp="F://7月活动数据/temp.txt";
        String filePath = "F://7月活动数据/718.txt";
        File file = new File(filePath);
        FileOutputStream fs = null;
        BufferedWriter writer=null;
        try {
             writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));
            JSONArray jsonArray = JSONArray.fromObject(getResult(temp));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));
                RunData runData = (RunData) JSONObject.toBean(jsonObject, RunData.class);
                String tempData = runData.getUser_id()+":"+runData.getName()+"\r\n";
                writer.append(tempData);
                System.out.println(tempData);
            }
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
