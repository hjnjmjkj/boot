package com.hk.ssm4.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCountWord {
	public static void main(String[] args) throws IOException {
		Files.readAllLines(Paths.get("C:/Users/davis/Desktop/temp/temp/1.txt"))
			.stream()
			.flatMap(x -> Stream.of(x.trim().split(" "))) 
			.collect(Collectors.groupingBy(p -> p,Collectors.counting()))
			.forEach((k, v) -> System.out.println(k + ":" + v));
	}
}
