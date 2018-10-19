package com.hk.ssm4.nio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.FileUtils;



public class FilesTest {
	public static void main(String[] args) {
		try {
			 // Java8用流的方式读文件，更加高效  
			
			File f=new File("C:\\Users\\davis\\Desktop\\upgrade20180918_bams_test.sql");
	        Files.lines(Paths.get("C:\\Users\\davis\\Desktop\\upgrade20180918_bams_test.sql")).forEach(System.out::println); 
	        ;
	        System.out.println(Files.size(f.toPath()));
	        Path path=Files.createTempDirectory("hello");
	        Files.copy(f.toPath(), Paths.get(path.toString(), "1.sql"));
	        System.out.println(path);
	        
	        FileUtils.forceDeleteOnExit(path.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
