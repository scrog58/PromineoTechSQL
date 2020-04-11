package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Trainers;

public class TrainersDao {
	
	private Connection connection;
	private PokemonsDao pokemonsDao;
	private final String GET_TRAINERS_QUERY = "SELECT * FROM trainers";
	private final String GET_TRAINER_BY_ID_QUERY = "SELECT * FROM trainers WHERE id = ?";
	private final String NEW_TRAINER_NAME_QUERY = "INSERT INTO trainers(firstName, lastName, userName) VALUES(?,?,?)";
	private final String DELETE_TRAINER = "DELETE FROM trainers WHERE id = ? ";
	private final String UPDATE_TRAINER_USERNAME = "UPDATE trainers SET userName = ? WHERE id = ?";
	private final String UPDATE_TRAINER_LASTNAME = "UPDATE trainers SET lastName = ? WHERE id = ?";
	
	public TrainersDao() {
		connection = DBConnection.getConnection();
		pokemonsDao = new PokemonsDao();
		
	}
	
	public List<Trainers> getTrainers() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_TRAINERS_QUERY).executeQuery();
		List<Trainers> trainers = new ArrayList<Trainers>();
		
		while(rs.next()) {
			trainers.add(listTrainers(rs.getInt(1), rs.getString(2)));
		}
		return trainers;
	}
	
	public List<Trainers> getTrainersAllInfo() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_TRAINERS_QUERY).executeQuery();
		List<Trainers> trainersAllInfo = new ArrayList<Trainers>();
		
		while(rs.next()) {
			trainersAllInfo.add(listAllTrainersInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return trainersAllInfo;
	}
	
	public Trainers getTrainerById(int trainerId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TRAINER_BY_ID_QUERY);
		ps.setInt(1, trainerId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return listTrainers(rs.getInt(1), rs.getString(2));
	}
	
	
	public void newTrainer(String firstName, String lastName, String userName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(NEW_TRAINER_NAME_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, userName);
		ps.executeUpdate();
		
	}
	
	public void deleteTrainer(int trainerId) throws SQLException {
		pokemonsDao.removeAllPokemonByTrainerId(trainerId);
		PreparedStatement ps = connection.prepareStatement(DELETE_TRAINER);
		ps.setInt(1, trainerId);
		ps.executeUpdate();
		
	}
	
	public void updateTrainerUserName(String userName, int trainerId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_TRAINER_USERNAME);
		ps.setString(1,userName);
		ps.setInt(2, trainerId);
		ps.executeUpdate();
		
	}
	
	public void updateTrainerLastName(String lastName, int trainerId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_TRAINER_LASTNAME);
		ps.setString(1,lastName);
		ps.setInt(2, trainerId);
		ps.executeUpdate();
		
	}
	
	
	
	private Trainers listTrainers(int trainerId, String trainerUserName) throws SQLException {
		return new Trainers(trainerId, trainerUserName, pokemonsDao.getPokemonByTrainerId(trainerId));
	}
	
	private Trainers listAllTrainersInfo(int trainerId, String firstName, String lastName, String userName) throws SQLException {
		return new Trainers(trainerId, firstName, lastName, userName);
	}

	
	
}
