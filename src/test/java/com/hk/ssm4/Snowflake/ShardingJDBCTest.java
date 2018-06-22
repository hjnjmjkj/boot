package com.hk.ssm4.Snowflake;

import io.shardingjdbc.core.keygen.DefaultKeyGenerator;

public class ShardingJDBCTest {
	public static void main(String[] args) {
		DefaultKeyGenerator.setWorkerId(1023);
		DefaultKeyGenerator generator = new DefaultKeyGenerator();
		System.out.println(generator.generateKey());
	}
}
