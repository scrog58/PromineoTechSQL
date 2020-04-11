package entity;

import java.util.List;

public class Trainers {
	
	private int trainerId;
	private String userName;
	private String firstName;
	private String lastName;
	private List<Pokemons> pokemons;
	
	public Trainers(int trainerId, String userName, List<Pokemons> pokemons) {
		this.setTrainerId(trainerId);
		this.setUserName(userName); 
		this.setPokemons(pokemons);
		
	
	}
	
	public Trainers(int trainerId, String firstName, String lastName, String userName) {
		this.setTrainerId(trainerId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserName(userName); 
	
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Pokemons> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Pokemons> pokemons) {
		this.pokemons = pokemons;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
