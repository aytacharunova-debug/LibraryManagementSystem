package com.library.daoInterface;
import com.library.model.Fine;
import java.util.List;

public interface IFine {
	

	
		void add(Fine fine);
		void update(Fine fine);
		void delete(int id);
		Fine getById( int id);
		List<Fine> getAll();
		  
	
}
