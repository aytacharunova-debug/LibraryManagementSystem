package com.library.daoInterface;

import com.library.model.Category;
import java.util.List;

public interface ICategory {
	

	  void add(Category category);
		void update(Category category);
		void delete(int id);
		Category getById( int id);
		List<Category> getAll();
		  
	
}