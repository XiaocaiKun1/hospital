package com.neusoft.neu24.his.neuhospital.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
    private  final static String  FILE_PATH ="E:\\Work\\neu\\his\\Demo\\";
    private  final static ObjectMapper mapper = new ObjectMapper();
    public  static   long writeObjectAsJsonFile(Object obj,String filename) throws IOException {
//        1、判断文件是否存在
//        2、如果存在删除，否则新建
//        3、obj - 》  JSON
//        4、String 写入文件
        File  file =new File( FILE_PATH +  filename); // 相对路径
        if (file.exists()){
            file.delete();
        }
        FileOutputStream os =null;
        try
        {
            file.createNewFile();

            String  json = mapper.writeValueAsString(obj);
            byte[] bs  = json.getBytes();
            os =new FileOutputStream(file);
            os.write(bs);
            os.flush();
            return  bs.length;
        }catch (IOException ex ){
            System.out.println( ex.getMessage());
        }finally {
            if (os!=null){
                os.close();
            }
        }
        return   -1;

    }

    public static Object readObjectFromJsonFile(String fileName ,Class clazz) {
        try {
            File file = new File(FILE_PATH+fileName);
            if (!file.exists()) {
                return   new ArrayList();
            }
            FileInputStream is = null;
            try {
                is  =new FileInputStream(  file);
                byte[] bs = new byte[is.available()] ;
                is.read(bs);
                String json =  new String(bs  );
                return  mapper.readValue(json,clazz);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            finally {
                if (is!=null){
                    is.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}