package com.library.daoInterface;

import java.util.List;

import com.library.model.Publisher;

public interface IPublisher {
	
	void add(Publisher publisher);
	void update(Publisher Publisher);
	void delete(int id);
	Publisher getById( int id);
	List<Publisher> getAll();
	  

}
