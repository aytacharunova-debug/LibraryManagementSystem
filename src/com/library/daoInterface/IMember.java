package com.library.daoInterface;

import java.util.List;

import com.library.model.Member;

public interface IMember {
	void add(Member member);
	void update(Member member);
	void delete(int id);
	Member getById( int id);
	List<Member> getAll();
	  

}
