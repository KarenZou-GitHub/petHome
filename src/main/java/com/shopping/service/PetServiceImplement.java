package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.PetDao;
import com.shopping.dao.ProductDao;
import com.shopping.entity.Pet;

@Service
public class PetServiceImplement implements PetService {
	@Autowired
	private PetDao petdao;

	@Override
	public Pet getPet(String pname) {
		return petdao.getPet(pname);
	}

	@Override
	public Pet getPet(int id) {
		return petdao.getPet(id);
	}

	@Override
	public List<Pet> getAllPet() {
		return petdao.getAllPet();
	}

	@Override
	public void addpet(Pet pet) {
		petdao.addpet(pet);
	}

	@Override
	public boolean deletePet(int id) {
		return petdao.deletePet(id);
	}

	@Override
	public boolean updatePet(Pet pet) {
		return petdao.updatePet(pet);
	}

	@Override
	public List<Pet> getPetsByKeyWord(String searchKeyWord) {
		return petdao.getPetsByKeyWord(searchKeyWord);
	}

	@Override
	public List<Pet> getPetsByType(int type) {
		// TODO Auto-generated method stub
		return petdao.getPetsByType(type);
	}

	@Override
	public List<Pet> searchPet(String keyWord) {
		// TODO Auto-generated method stub
		return petdao.getPetsByKeyWord(keyWord);
	}



}
