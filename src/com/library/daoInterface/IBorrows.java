package com.library.daoInterface;
import com.library.model.Borrows;
import java.util.List;

public interface IBorrows {
	

	
		void add(Borrows borrows);
		void update(Borrows borrows);
		void delete(int id);
		Borrows getById( int id);
		List<Borrows> getAll();
		  
	
}
