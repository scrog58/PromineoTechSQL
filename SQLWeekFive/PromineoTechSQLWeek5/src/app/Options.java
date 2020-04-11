package app;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import DAO.PokemonsDao;
import DAO.TrainersDao;
import entity.Pokemons;
import entity.Trainers;

public class Options {
	private Scanner scanner = new Scanner(System.in);
	private TrainersDao trainerDao = new TrainersDao();
	private PokemonsDao pokemonsDao = new PokemonsDao();
	private List<String> options = Arrays.asList(
			"Show All Trainers Info"
			, "Show All Trainers Usernames"
			, "Show Trainer and Pokemons"
			, "New Trainer"
			, "Edit Trainer Username"
			, "Edit Trainer Last Name"
			, "Delete Trainer"
			, "New Pokemon"
			, "Delete Pokemon"
			 );
	
	public void start() {
		String makeSelection = "";
		
		while(!makeSelection.equals("0")) {
			showListOfOptions();
			makeSelection = scanner.nextLine();
			try {
				if(makeSelection.equals("1")){
					showTrainersAllInfo();
				} else if(makeSelection.equals("2")){
					showTrainersUserName();	
				} else if(makeSelection.equals("3")){
					showTrainerandPokemon();
				} else if(makeSelection.equals("4")){
					newTrainer();
				} else if(makeSelection.equals("5")){
					editTrainerUserName();
				} else if(makeSelection.equals("6")){
					editTrainerLastName();
				} else if(makeSelection.equals("7")){
					deleteTrainerandPokemons();
				} else if(makeSelection.equals("8")){
					newPokemon();
				} else if(makeSelection.equals("9")){
					deletePokemon();
				} else if(makeSelection.equals("0")){
					break;
				}
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("Press enter to continue.........");
			scanner.nextLine();
		}
			

		
	}
	
	private void showListOfOptions() {
		System.out.println("Select number from below options: \n");
		for(int i = 0; i < options.size(); i++) {
			System.out.println(i+1+". "+ options.get(i));
		}
		System.out.println("-------------------Press 0 to exit-------------------");
	}
	
	private void showTrainersAllInfo() throws SQLException {
		List<Trainers> trainersAllInfo = trainerDao.getTrainersAllInfo();
		for(Trainers trainer : trainersAllInfo) {
			System.out.println(trainer.getTrainerId() + ") {UserName:"+ trainer.getUserName()+ ",First Name: "+ trainer.getFirstName()+ ",Last Name: "+trainer.getLastName()+"}");
		}
		
	}
	
	private void showTrainersUserName() throws SQLException {
		List<Trainers> trainers = trainerDao.getTrainers();
		for(Trainers trainer : trainers) {
			System.out.println(trainer.getTrainerId() + ": "+ trainer.getUserName());
		}
		
	}
	
	private void showTrainerandPokemon() throws SQLException {
		System.out.println("Enter Trainer id: ");
		int trainerId = Integer.parseInt(scanner.nextLine());
		Trainers trainer = trainerDao.getTrainerById(trainerId);
		System.out.println(trainer.getTrainerId() + ": "+ trainer.getUserName());
		for(Pokemons pokemon: trainer.getPokemons()) {
			System.out.println("\nPokemonId: "+ pokemon.getPokemonId()+ "\nPokemon Name: " + pokemon.getPokemonName()+"\n");
		}
	}
	
	private void newTrainer() throws SQLException {
		System.out.println("Entering a new Trainer. First please list your First Name:");
		String firstName = scanner.nextLine();
		System.out.println("Please enter Last Name:");
		String lastName = scanner.nextLine();
		System.out.println("Please enter a username:");
		String userName = scanner.nextLine();
		
		trainerDao.newTrainer(firstName, lastName, userName); 
		
	}
	
	private void newPokemon() throws SQLException {
		System.out.println("Enter Trainer Id:");
		int trainerId = Integer.parseInt(scanner.nextLine());
		System.out.println("Entering a new Pokemon:");
		String pokemonName = scanner.nextLine();
		String checkPokemonSQLName = pokemonsDao.checkPokemonName(pokemonName);

		if(pokemonName.equals(checkPokemonSQLName)) {
			Pokemons getPokemonId = pokemonsDao.getPokemonIdByName(pokemonName);
			pokemonsDao.addTrainerPokemonIds(trainerId,getPokemonId.getPokemonId());
		} else {
		
		pokemonsDao.getNewPokemon(pokemonName); 
		Pokemons getPokemonId = pokemonsDao.getPokemonIdByName(pokemonName);
		pokemonsDao.addTrainerPokemonIds(trainerId,getPokemonId.getPokemonId());
		}
		
	}
	
	private void editTrainerUserName() throws SQLException {
		System.out.println("Enter New UserName:");
		String userName = scanner.nextLine();
		System.out.println("Enter Trainer Id:");
		int trainerId = Integer.parseInt(scanner.nextLine());
		
		trainerDao.updateTrainerUserName(userName, trainerId);
	}
	
	private void editTrainerLastName() throws SQLException {
		System.out.println("Enter New Last Name:");
		String lastName = scanner.nextLine();
		System.out.println("Enter Trainer Id:");
		int trainerId = Integer.parseInt(scanner.nextLine());
		
		trainerDao.updateTrainerLastName(lastName, trainerId);
	}
	
	private void deleteTrainerandPokemons() throws SQLException {
		System.out.println("Enter Trainer Id:");
		int trainerId = Integer.parseInt(scanner.nextLine());
		trainerDao.deleteTrainer(trainerId);
	}
	
	private void deletePokemon() throws SQLException {
		System.out.println("Enter Trainer Id:");
		int trainerId = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter Pokemon Id:");
		int pokemonId = Integer.parseInt(scanner.nextLine());
		pokemonsDao.deletePokemonByIdandTrainerId(trainerId,pokemonId);
	}
	

}
