package com.hk.ssm4.jdk;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

public class TestTime {
	@Test
	public void dateToLocalDateTime(){
		java.util.Date date = new java.util.Date();
	    Instant instant = date.toInstant();
	    ZoneId zone = ZoneId.systemDefault();
	    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
	    System.out.println(localDateTime);
	}
	@Test
	public void localDateTimeToDate(){
		LocalDateTime localDateTime = LocalDateTime.now();
	    ZoneId zone = ZoneId.systemDefault();
	    Instant instant = localDateTime.atZone(zone).toInstant();
	    java.util.Date date = Date.from(instant);
	    System.out.println(date);
	}
	
	// 01. java.util.Date --> java.time.LocalDateTime
	@Test
	public void UDateToLocalDateTime() {
	    java.util.Date date = new java.util.Date();
	    Instant instant = date.toInstant();
	    ZoneId zone = ZoneId.systemDefault();
	    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
	    System.out.println(localDateTime);
	}

	// 02. java.util.Date --> java.time.LocalDate
	@Test
	public void UDateToLocalDate() {
	    java.util.Date date = new java.util.Date();
	    Instant instant = date.toInstant();
	    ZoneId zone = ZoneId.systemDefault();
	    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
	    LocalDate localDate = localDateTime.toLocalDate();
	    System.out.println(localDate);
	}

	// 03. java.util.Date --> java.time.LocalTime
	@Test
	public void UDateToLocalTime() {
	    java.util.Date date = new java.util.Date();
	    Instant instant = date.toInstant();
	    ZoneId zone = ZoneId.systemDefault();
	    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
	    LocalTime localTime = localDateTime.toLocalTime();
	    System.out.println(localTime);
	}


	// 04. java.time.LocalDateTime --> java.util.Date
	@Test
	public void LocalDateTimeToUdate() {
	    LocalDateTime localDateTime = LocalDateTime.now();
	    ZoneId zone = ZoneId.systemDefault();
	    Instant instant = localDateTime.atZone(zone).toInstant();
	    java.util.Date date = Date.from(instant);
	    System.out.println(date);
	}


	// 05. java.time.LocalDate --> java.util.Date
	@Test
	public void LocalDateToUdate() {
	    LocalDate localDate = LocalDate.now();
	    ZoneId zone = ZoneId.systemDefault();
	    Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
	    java.util.Date date = Date.from(instant);
	    System.out.println(date);
	}

	// 06. java.time.LocalTime --> java.util.Date
	@Test
	public void LocalTimeToUdate() {
	    LocalTime localTime = LocalTime.now();
	    LocalDate localDate = LocalDate.now();
	    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
	    ZoneId zone = ZoneId.systemDefault();
	    Instant instant = localDateTime.atZone(zone).toInstant();
	    java.util.Date date = Date.from(instant);
	    System.out.println(date);
	}
}
