package ru.specialist.service;

import java.time.LocalTime;

public class MyService {
	
	public String getServerTime() {
		
		return String.format("%tR", LocalTime.now());
	}
	

}
