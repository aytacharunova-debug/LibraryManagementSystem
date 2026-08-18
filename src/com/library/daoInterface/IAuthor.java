package com.library.daoInterface;
import com.library.model.Author;
import java.util.List;

public interface IAuthor {
	

	
		void add(Author author);
		void update(Author author);
		void delete(int id);
		Author getById( int id);
		List<Author> getAll();
		  
	
}
