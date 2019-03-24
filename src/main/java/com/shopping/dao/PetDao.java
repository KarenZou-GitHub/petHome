package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Pet;

public interface PetDao {

	Pet getPet(String pname);
	
	Pet getPet(int id);
	
	List<Pet> getAllPet();
	
	void addpet(Pet pet);
	
	boolean deletePet(int id);
	
	boolean updatePet(Pet pet);

	List<Pet> getPetsByKeyWord(String keyWord);
	
    List<Pet> getPetsByType(int type);


}
