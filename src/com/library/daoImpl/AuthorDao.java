package com.library.daoImpl;
import com.library.daoInterface.IAuthor;
import com.library.model.Author;
import com.library.util.DBconnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class AuthorDao implements IAuthor {
	
	@Override
	
	public void add(Author author) {
		String sql ="INSERT INTO author(name,surname,country) VALUES (?,?,?)";
		try(Connection conn=DBconnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			            // Sual işarələrinin yerini doldururuq
			            pstmt.setString(1, author.getName());    // 1-ci ? = name
			            pstmt.setString(2, author.getSurname()); // 2-ci ? = surname
			            pstmt.setString(3, author.getCountry()); // 3-cü ? = country (şəkildəki yeni field)
			            
			            pstmt.executeUpdate(); // Əmri icra et
			            System.out.println("Müəllif uğurla əlavə edildi!");
			            
			        } catch (SQLException e) {
			            System.out.println("Əlavə zamanı xəta: " + e.getMessage());
			        }
		}
			
			
		
				
	
			@Override
			public List<Author> getAll() {
			    List<Author> authors = new ArrayList<>();
			    String sql = "SELECT * FROM author";
			
			    try (Connection conn = DBconnection.getConnection();
			         Statement stmt = conn.createStatement();
			         ResultSet rs = stmt.executeQuery(sql)) {
			
			        while (rs.next()) {
			            // Bazadan gələn hər sətiri yeni Author obyektinə yığırıq
			        	Author author = new Author(); // Yeni obyekt yaradırıq
			                
			                // 4. Bazadakı sütunları oxuyub obyektin içinə doldururuq
			                author.setAuthorId(rs.getInt("author_id"));
			                author.setName(rs.getString("name"));
			                author.setSurname(rs.getString("surname"));
			                author.setCountry(rs.getString("country"));
			            
			            authors.add(author); // Siyahıya əlavə et
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    return authors;
			}
			@Override
			public void update(Author author) {
			    // 1. SQL Sorğusunun hazırlanması
			    String query = "UPDATE author SET name = ?, surname = ?, country = ? WHERE author_id = ?";

			    try (Connection conn = DBconnection.getConnection();
			         PreparedStatement pstmt = conn.prepareStatement(query)) {
			        
			        // 2. Sual işarələrinin (?) yerinə yeni dəyərlərin ötürülməsi
			        pstmt.setString(1, author.getName());       // Birinci '?' üçün (name)
			        pstmt.setString(2, author.getSurname());    // İkinci '?' üçün (surname)
			        pstmt.setString(3, author.getCountry());    // Üçüncü '?' üçün (country)
			        pstmt.setInt(4, author.getAuthorId());     // Dördüncü '?' üçün (WHERE şərti - hansı müəllif?)
			        
			        // 3. Sorğunun icra edilməsi
			        pstmt.executeUpdate();
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
			@Override
			public Author getById(int id) {
			    Author author = null; // Əgər tapılmasa, null qayıdacaq
			    String query = "SELECT * FROM author WHERE author_id = ?";

			    try (Connection conn = DBconnection.getConnection();
			         PreparedStatement pstmt = conn.prepareStatement(query);
			    		ResultSet rs = pstmt.executeQuery()) {
			        
			        // 1. Sual işarəsinin yerinə axtardığımız ID-ni qoyuruq
			        pstmt.setInt(1, id);
			         
			        // 2. Sorğunu icra edirik və nəticəni ResultSet-ə alırıq
			        
			            
			            // 3. Əgər belə bir ID-li müəllif varsa (rs.next() true olacaq)
			            if (rs.next()) {
			                author = new Author(); // Yeni obyekt yaradırıq
			                
			                // 4. Bazadakı sütunları oxuyub obyektin içinə doldururuq
			                author.setAuthorId(rs.getInt("author_id"));
			                author.setName(rs.getString("name"));
			                author.setSurname(rs.getString("surname"));
			                author.setCountry(rs.getString("country"));
			            }
			        
			    }catch (SQLException e) {
			        e.printStackTrace();
			    }
			    
			    return author; // Tapılan müəllifi (və ya null-u) geri qaytarırıq
			}
			
			@Override
			public void delete(int id) {
			    // 1. SQL Sorğusu
			    String query = "DELETE FROM author WHERE author_id = ?";

			    try (Connection conn =DBconnection. getConnection();
			         PreparedStatement pstmt = conn.prepareStatement(query)) {
			        
			        // 2. Hansı ID-li müəllifin silinəcəyini təyin edirik
			        pstmt.setInt(1, id);
			        
			        // 3. Sorğunu icra edirik
			        pstmt.executeUpdate();
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
			
}
 
	
 

