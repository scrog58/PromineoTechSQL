package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Pokemons;
import entity.Trainers;


public class PokemonsDao {
	
	private Connection connection;
	private final String GET_POKEMON_BY_TRIANERID = "SELECT * FROM pokemons P INNER JOIN trainer_pokemon TP ON TP.pokemonid = P.id WHERE TP.userid = ?";
	private final String NEW_POKEMON_QUERY = "INSERT INTO pokemons(name) VALUES(?)";
	private final String CHECK_POKEMON_NAME_QUERY = "SELECT name FROM pokemons WHERE name = ?";
	private final String ADD_POKEMONID_TRAINERID_TO_TRAINER_POKEMON = "INSERT INTO trainer_pokemon(userid, pokemonid) VALUES(?,?)";
	private final String GET_POKEMON_ID_BY_NAME = "SELECT id FROM pokemons WHERE name = ?";
	private final String REMOVE_ALL_POKEMON_BY_TRAINERID = "DELETE FROM trainer_pokemon WHERE userid = ? ";
	private final String DELETE_POKEMON_BY_ID_ON_TRAINERLEVEL = "DELETE FROM trainer_pokemon WHERE userid = ? AND pokemonid = ?";
	
	public PokemonsDao() {
		connection = DBConnection.getConnection();
	}

	public List<Pokemons> getPokemonByTrainerId(int trainerId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_POKEMON_BY_TRIANERID);
		ps.setInt(1, trainerId);
		ResultSet rs = ps.executeQuery();
		List<Pokemons> pokemons = new ArrayList<Pokemons>();
		
		while(rs.next()) {
			pokemons.add(new Pokemons(rs.getInt(1), rs.getString(2)));
			
		}
		
		return pokemons;
	}
	
	public void getNewPokemon(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(NEW_POKEMON_QUERY);
		ps.setString(1, name);
		ps.executeUpdate();
		
	}
	
	public void addTrainerPokemonIds(int userId, int pokemonId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_POKEMONID_TRAINERID_TO_TRAINER_POKEMON);
		ps.setInt(1, userId);
		ps.setInt(2, pokemonId);
		ps.executeUpdate();
		
	}
	
	public void removeAllPokemonByTrainerId(int trainerId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(REMOVE_ALL_POKEMON_BY_TRAINERID);
		ps.setInt(1, trainerId);
		ps.executeUpdate();
		
	}
	
	public void deletePokemonByIdandTrainerId(int trainerId, int pokemonId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_POKEMON_BY_ID_ON_TRAINERLEVEL);
		ps.setInt(1, trainerId);
		ps.setInt(2, pokemonId);
		ps.executeUpdate();
		
	}
	
	public Pokemons getPokemonIdByName(String pokemonName) throws SQLException {	
		PreparedStatement ps = connection.prepareStatement(GET_POKEMON_ID_BY_NAME);
		ps.setString(1, pokemonName);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return listPokemonId(rs.getInt(1));
		
	}
	
	public String checkPokemonName(String pokemonName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CHECK_POKEMON_NAME_QUERY);
		ps.setString(1, pokemonName);
		ResultSet rs = ps.executeQuery();
		String string = "";
			
		
		if(!rs.next()) {
			string = "NOMATCHPOKMONENAME";
		} else {
			string = rs.getString(1);
			string.toString();
		}
		
		return string;
	}
	

	
	private Pokemons listPokemonId(int pokemonId) throws SQLException {
		return new Pokemons(pokemonId);
	}
	
	private Pokemons listPokemonName(String pokemonName) throws SQLException {
		return new Pokemons(pokemonName);
	}

}
