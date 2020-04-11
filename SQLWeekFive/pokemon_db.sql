CREATE DATABASE IF NOT EXISTS pokemon_db;

use pokemon_db;

DROP TABLE IF EXISTS trainers;
DROP TABLE IF EXISTS pokemons;


CREATE TABLE trainers (
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY
	, firstName VARCHAR(20) NOT NULL
	, lastName VARCHAR(25) NOT NULL
	, userName VARCHAR(35) NOT NULL
  , createdDate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE pokemons (
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY
	, name VARCHAR(35)
	, createdDate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL

);

CREATE TABLE trainer_pokemon (
	userid int(11) NOT NULL
	, pokemonid int(11) NOT NULL
  , FOREIGN KEY (userid) REFERENCES trainers(id)
	, FOREIGN KEY (pokemonid) REFERENCES pokemons(id)
);
