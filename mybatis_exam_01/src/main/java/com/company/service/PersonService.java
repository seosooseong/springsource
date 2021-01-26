package com.company.service;

public interface PersonService {
	public int insertPerson(String id, String name);
	
	public int updatePerson(String id, String name);
	public int deletePerson(String id);
}
