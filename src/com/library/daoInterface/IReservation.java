package com.library.daoInterface;
import com.library.model.Reservation;
import java.util.List;

public interface IReservation {
	

	
		void add(Reservation resrvation);
		void update(Reservation  reservation);
		void delete(int id);
		Reservation getById( int id);
		List<Reservation> getAll();
		  
	
}
