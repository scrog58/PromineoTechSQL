package entity;

import java.util.List;

public class Pokemons {
	private int pokemonId;
	private String pokemonName;
	
	
	public Pokemons(int pokemonId, String pokemonName) {
		this.setPokemonId(pokemonId);
		this.setPokemonName(pokemonName); 
	}
	
	public Pokemons(int pokemonId) {
		this.setPokemonId(pokemonId);
	}
	
	public Pokemons(String pokemonName) {
		this.setPokemonName(pokemonName); 
	}
	
	
	public int getPokemonId() {
		return pokemonId;
	}
	
	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}
	
	public String getPokemonName() {
		return pokemonName;
	}
	
	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}
	
	

}
