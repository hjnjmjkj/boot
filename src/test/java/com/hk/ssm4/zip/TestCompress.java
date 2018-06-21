package com.hk.ssm4.zip;

import java.io.FileOutputStream;
import java.util.List;

import com.hk.ssm4.util.ZipUtil;

public class TestCompress {
	public static void main(String[] args) {  
        // 要压缩的文件列表  
        String path01 = "C:/Users/davis/Desktop/temp/自有车资料20180516092050.xlsx";  
        String path02 = "C:/Users/davis/Desktop/temp/自有车资料20180516092056.xlsx";  
        String [] str = new String[]{path01,path02};
        try {  
            FileOutputStream os = new FileOutputStream("e:/测试.zip"); // 输出的ZIP文件流  
            ZipUtil.compress(os, "C:/Users/davis/Desktop/temp");  
            for (String string : str) {
				System.out.println(string);
			}
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
