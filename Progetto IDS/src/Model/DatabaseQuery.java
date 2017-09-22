package Model;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.*;

public class DatabaseQuery {
	
	private static DatabaseQuery db = null;
	
	private final String database = "jdbc:postgresql://localhost:5432/eldisondimo";
	private final String dbUsername = "eldisondimo";
	private final String dbPassword = "";
	
	private DatabaseQuery() {

	}
	
	public static DatabaseQuery getInstance() {
		if(db==null) {
			db = new DatabaseQuery();
		}
		return db;
	}
		
	public int addGenre(String gen) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
		    	try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO genere  (nome) VALUES (?)")){
		            	pst.clearParameters();
		                pst.setString(1, gen);
		                int i = pst.executeUpdate(); 
		                if (i > 0)
		                	return 1;	           
		                else
		                	return 0;	
		            }catch(SQLException e){
		        	    	System.out.println("Errore: " + e);
		        	    	System.exit(1);
		            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
	     return 0;
	}
	
	public int existsGenre(String gen) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM genere WHERE nome = ?;")){ 
	    		 	pst.setString(1, gen);
	                ResultSet rs = pst.executeQuery(); 
	                if(rs.next()){
	                	if(rs.getInt(1) > 0){
	                		return 1;
	                	}
	                	else 
	                		return 0;
	                }
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     }catch(SQLException e){
   	    	System.out.println("Errore: " + e);
   	    	System.exit(1);
       }
	    return 0;
	}
	
	public int addBand(String name, String gen, int year) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
		    	try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO band  (nome, generePrinc, annoFormazione) VALUES (?,?,?)")){
		            	pst.clearParameters();
		                pst.setString(1, name);
		                pst.setString(2, gen);
		                pst.setInt(3, year);
		                int i = pst.executeUpdate(); 
		                if (i > 0)
		                	return 1;	           
		                else
		                	return 0;	
		            }catch(SQLException e){
		        	    	System.out.println("Errore: " + e);
		        	    	System.exit(1);
		            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
	     return 0;
	}

	public int existsBand(String owner) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM band WHERE nome = ?;")){ 
	    		 	pst.setString(1, owner);
	                ResultSet rs = pst.executeQuery(); 
	                if(rs.next()){
	                	if(rs.getInt(1) > 0){
	                		return 1;
	                	}
	                	else 
	                		return 0;
	                }
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     }catch(SQLException e){
   	    	System.out.println("Errore: " + e);
   	    	System.exit(1);
       }
	    return 0;
	}
	
	public int existsMusician(String owner) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM musicista WHERE nomeArte = ?;")){ 
	    		 	pst.setString(1, owner);
	                ResultSet rs = pst.executeQuery(); 
	                if(rs.next()){
	                	if(rs.getInt(1) > 0){
	                		return 1;
	                	}
	                	else 
	                		return 0;
	                }
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     }catch(SQLException e){
    	    	System.out.println("Errore: " + e);
    	    	System.exit(1);
        }
	    return 0;
	}

	public int addMusician(Musician m) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
		    	try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO musicista  (nomeArte, generePrinc, annoNascita, strumenti, band) VALUES (?,?,?,?,?)")){
		            	pst.clearParameters();
		                pst.setString(1, m.getName());
		                pst.setString(2, m.getGenre());
		                pst.setInt(3, m.getYear());
		                pst.setString(4, m.getInstruments());
		                pst.setString(5, m.getBand());
		                int i = pst.executeUpdate(); 
		                if (i > 0)
		                	return 1;	//Inserimento avvenuto con successo	            
		                else
		                	return 0;	
		            }catch(SQLException e){
		        	    	System.out.println("Errore: " + e);
		        	    	System.exit(1);
		            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
	     return 0;
	}
	
	public Catalog searchCatalog(String title, String genre, Double minPrice, Double maxPrice) {
		 try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		 Catalog c = new Catalog();
		 String query = "SELECT D.*, C.numeroCopie FROM disco D JOIN catalogo C ON D.codice = C.disco";
		 
		 if(title!=null) {
			 title = title.trim();
			 if(title.equals("")){
				 title = null;
			 }
		 }
		 
		 if(title != null || genre != null || minPrice != null || maxPrice != null) {
			 query += " WHERE ";
		 }
		 
		 if(title != null){
			 query += "D.titolo LIKE " + "'%" + title + "%'";
		 }
		 
		 if(title != null && genre != null) {
			 query += " AND ";
		 }
		 
		 if(genre != null){
			 query += "genere = '" + genre + "'";
		 }
		 
		 if(title != null || genre != null)
			 query += " AND ";
		 
		 if(minPrice != null){
			 query += "prezzo >= " + minPrice;
		 }
		 
		 if(maxPrice != null){
			 query += " AND prezzo <= " + maxPrice;
		 }
		 
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try (Statement st = conn.createStatement()){ 
	    		 System.out.println(query);
	    		 ResultSet rs = st.executeQuery(query); 
	                while(rs.next()){
	                	Disc d = new Disc(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDate(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
	                	c.add(d, rs.getInt(11));
	                }
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
		return c;
	}
	
	private int userAlreadyExists(String userName) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM persona WHERE nomeUtente = ?;")){ 
	    		 	pst.setString(1, userName);
	                ResultSet rs = pst.executeQuery(); 
	                if(rs.next()){
	                	if(rs.getInt(1) > 0){
	                		return 1;
	                	}
	                	else 
	                		return 0;
	                }
	            }catch(SQLException e){
	        	    	System.out.println("Errore: ");
	        	    	System.exit(1);
	            }
	     }catch(SQLException e){
     	    	System.out.println("Errore: ");
     	    	System.exit(1);
         }
	    return 0;
	}
	
	public int signin(Person p) {
		if(p==null)
			return 0;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try (Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	if(userAlreadyExists(p.getUsername()) == 0) {
		    	try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO persona (codiceFiscale, nomeUtente, password, nome, cognome, indirizzo, citta, numeroTelefono, numeroCellulare, isPersonale) VALUES (?,?,?,?,?,?,?,?,?,false)")){
		            	pst.clearParameters();
		                pst.setString(1, p.getIdCode());
		                pst.setString(2, p.getUsername());
		                pst.setString(3, p.getPassword());
		                pst.setString(4, p.getName());
		                pst.setString(5, p.getSurname());
		                pst.setString(6, p.getAddress());
		                pst.setString(7, p.getCity());
		                pst.setString(8, p.getPhone());
		                pst.setString(9, p.getCellphone());
		                int i = pst.executeUpdate(); 
		                if (i > 0)
		                	return 1;	//Inserimento avvenuto con successo	            
		                else
		                	return 0;	
		            }catch(SQLException e){
			        	    	System.out.println("Errore: " + e);
			        	    	return 0;
		            }
	    	}
	    	else
	    		return -1;	//Nome utente esiste già
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	return 0;
	     }
	}
	
	private void addCatalog(int code, int quantity, Connection conn) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	    	 try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO catalogo  (disco, numeroCopie) VALUES (?,?)")){
	            	pst.clearParameters();
	                pst.setInt(1, code);
	                pst.setInt(2, quantity);
	                int i = pst.executeUpdate();
	                if(i>0)
	                	System.out.println("aggiunto al catalogo");
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	return;
	            }
	}
	
	public Catalog getCatalog() {
		 try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		  Catalog c = new Catalog();
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT D.*, G.numeroCopie FROM disco as D JOIN catalogo G ON G.disco = D.codice;")){               
	                ResultSet rs = pst.executeQuery(); 
	                while(rs.next()){
	                	Disc d = new Disc(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDate(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
	                	c.add(d, rs.getInt(11));
	                }
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
	     
		return c;
	}
	
	public int addDisc (Disc d, int quantity) {
		if(this.existsGenre(d.getGenre())==0) {
			this.addGenre(d.getGenre());
		}
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO disco  (titolo, titoliPezzi, fotoCopertina, prezzo, dataSulSito, titMusicista,titBand, descrizione, genere) VALUES (?,?,?,?,(now()),?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
	            	pst.clearParameters();
	                pst.setString(1, d.getTitle());
	                pst.setString(2, d.getSongs());
	                pst.setString(3, d.getImage());
	                pst.setBigDecimal(4, new BigDecimal(d.getPrice()));  
	                pst.setString(5, d.getMusician());
	                pst.setString(6, d.getBand());
	                pst.setString(7, d.getDescription());
	                pst.setString(8, d.getGenre());
	                int i = pst.executeUpdate(); 
	                if (i > 0){
		                ResultSet rs=pst.getGeneratedKeys();
		                if(rs.next()){
			                	addCatalog(rs.getInt(1),quantity, conn);
		                }
		                return 1;
	                }
	                else
	                		return 0;
	                
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
	     
	     return 0;
	}
	
	public void editStoreQuantity(int code, int quantity) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("UPDATE catalogo SET numeroCopie = ? WHERE disco = ?")){
	            	pst.clearParameters();
	                pst.setInt(1, quantity);
	                pst.setInt(2, code);
	                pst.executeUpdate(); 
	            }catch(SQLException e){
	        	    	System.out.println("Errore: ");
	        	    	return;
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: ");
	    	return;
	     }
	}

	public void removeFromStore(int code) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("DELETE FROM catalogo WHERE disco = ?")) {
	            	pst.clearParameters();
	                pst.setInt(1, code);
	                pst.executeUpdate(); 
	            }catch(SQLException e){
	        	    	System.out.println("Errore: ");
	        	    	return;
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: ");
	    	return;
	     }
	}

	public void insertCart(int code, String user, int quantity) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO carrello  (utente, disco, quantita)VALUES (?,?,?);")){
	            	pst.clearParameters();
	                pst.setString(1, user);
	                pst.setInt(2, code);
	                pst.setInt(3, quantity);
	                pst.executeUpdate();
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	return;
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	return;
	     }
	}
	
	public void removeFromCart(int code, String user) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("DELETE FROM carrello WHERE utente = ? AND disco = ?;")){
	            	pst.clearParameters();
	                pst.setString(1, user);
	                pst.setInt(2, code);
	                pst.executeUpdate(); 
	            }catch(SQLException e){
	        	    	System.out.println("Errore: ");
	        	    	return;
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: ");
	    	return;
	     }
	}
	
	public Catalog getCart(String user) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Catalog c = new Catalog();
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT D.*, C.quantita FROM disco AS D JOIN carrello as C ON C.disco = D.codice WHERE C.utente = ?")){
	            	pst.clearParameters();
	                pst.setString(1, user);
	                ResultSet rs = pst.executeQuery(); 
	                while(rs.next()){
	                	Disc d = new Disc(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDate(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
	                	c.add(d, rs.getInt(11));
	                }
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
		return c;
	}
	
	public void editCartQuantity(String user, int disc, int quantity) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("UPDATE carrello SET quantita = ? WHERE disco = ? AND utente = ?")){
	            	pst.clearParameters();
	                pst.setInt(1, quantity);
	                pst.setInt(2, disc);
	                pst.setString(3, user);
	                pst.executeUpdate(); 
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	return;
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	return;
	     }
	}
	
	public boolean loginClient(String userName, String password) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT * FROM persona WHERE nomeUtente = ? AND password = ? AND isPersonale = 'false'")){
	            	pst.clearParameters();
	                pst.setString(1, userName);
	                pst.setString(2, password);
	                ResultSet rs = pst.executeQuery(); 
	                if(rs.next())					
	                	return true;
	                else								
	                	return false;
	            }catch(SQLException e){
	        	    	System.out.println("Errore: ");
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: ");
	    	System.exit(1);
	     }
		return false;
	}
	
	public boolean loginAdmin(String userName, String password) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT * FROM persona WHERE nomeUtente = ? AND password = ? AND isPersonale = 'true'")){
	            	pst.clearParameters();
	                pst.setString(1, userName);
	                pst.setString(2, password);
	                ResultSet rs = pst.executeQuery(); 
	                if(rs.next())						// Esiste l'utente personale
	                	return true;
	                else								// Nome utente o password sbagliati
	                	return false;
	            }catch(SQLException e){
	        	    	System.out.println("Errore: ");
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: ");
	    	System.exit(1);
	     }
		return false;
	}
	
	public Catalog getSuggested(String user) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Catalog c = new Catalog();
		String genere = null;
		String query = "DROP VIEW IF EXISTS nAcquistiPerGenere;"
				+ "CREATE VIEW nAcquistiPerGenere(genere, nAcquisti) AS "
				+ "SELECT D.genere, COUNT(*) as nAcquisti "
				+ "FROM persona as P, disco as D, acquisto as A, venditaProd as V "
				+ "WHERE P.nomeUtente = '" + user + "' AND P.nomeUtente = A.cliente AND A.codice = V.codAcquisto "
				+ "AND D.codice = V.disco "
				+ "GROUP BY D.genere;";

	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try (Statement st = conn.createStatement()){
	    		 	st.execute(query);
	                ResultSet rs = st.executeQuery("SELECT genere FROM nAcquistiPerGenere WHERE nAcquisti = ANY ( SELECT MAX(nAcquisti) FROM nAcquistiPerGenere );");
	                
	                while(rs.next()){
	                	genere = rs.getString(1);
	                } 
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	    	 
	    	 try ( PreparedStatement pst = conn.prepareStatement("SELECT D.*, C.numeroCopie FROM disco as D JOIN catalogo C ON C.disco = D.codice WHERE D.genere = ? limit 5; ")){
	            	pst.clearParameters();
	                pst.setString(1, genere);
	                ResultSet rs = pst.executeQuery(); 
	                while(rs.next()){
	                	Disc d = new Disc(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDate(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
	                	c.add(d, rs.getInt(11));
	                } 
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
		return c;
	}
	
	public Buy buyCD(Buy b) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO acquisto  (cliente, prezzoTot,dataAcquisto, IPAddressClient, metodoPagamento, metodoConsegna)VALUES (?,?,now(),?,?,?)", Statement.RETURN_GENERATED_KEYS)){
	            	pst.clearParameters();
	                pst.setString(1, b.getClient());
	                pst.setBigDecimal(2, new BigDecimal(b.getPrice()));
	                pst.setString(3, InetAddress.getLocalHost().getHostAddress());
	                pst.setString(4, b.getPayment());
	                pst.setString(5, b.getDeliver());
	                int esito = pst.executeUpdate(); 
	                if(esito > 0){
	                	ResultSet rs=pst.getGeneratedKeys();
		                if(rs.next()){
		                	b.setCode(rs.getInt(1));
		                	return b;
		                } 
	                }
	                else {
	                	System.out.println("Errore acquisto");
	                }
	            }catch(UnknownHostException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
		return null;
	}
	
	public int boughtCD(int buyCod, int codDisc) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
			try ( PreparedStatement pst = conn.prepareStatement("INSERT INTO venditaProd  (codAcquisto, disco) VALUES (?,?)")){
	           	pst.clearParameters();
	            pst.setInt(1, buyCod);
	            pst.setInt(2, codDisc);
	            pst.executeUpdate();
	           }catch(SQLException e){
	       	    	System.out.println("Errore: " + e);
	       	    	return -1;
	        }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
		return 0;
	}

	public List<String> getAllBand() {
		List<String> band = new ArrayList<String>();
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	    try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	try (Statement st = conn.createStatement()){
                ResultSet rs = st.executeQuery("SELECT DISTINCT nome FROM band;");
                
	            if(rs.next()){
	              	band.add(rs.getString(1));
	            }
	    	} catch(SQLException e) {
	      		System.out.println("Errore: " + e);
	       		System.exit(1);
	   		}
	    } catch(SQLException e){
	    	System.out.println("Errore: " + e);
  	    	System.exit(1);
	    }
		
		return band;
	}
	
	public double getMaxPrice(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		double prezzo = 0;
		  try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
		    	 try ( Statement st = conn.createStatement()){
		            	ResultSet rs = st.executeQuery("SELECT MAX(prezzo) FROM disco");
		            	while(rs.next()){
		            		prezzo = rs.getDouble(1);
		            	}
		            
		            }catch(SQLException e){
		        	    	System.out.println("Errore: " + e);
		        	    	System.exit(1);
		            }
		     } catch(SQLException e){
		    	System.out.println("Errore: " + e);
		    	System.exit(1);
		     }
		  
		return prezzo;
	}
	
	public boolean isSuper(String user){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		double prezzo = 0;
		
		String query = "SELECT SUM(prezzoTot)"
				+ "FROM acquisto "
				+ "WHERE cliente = " + "'" + user + "'"
				+ "GROUP BY cliente,EXTRACT(year from dataAcquisto) "
				+ "HAVING COUNT(*) >= 3;";
		
	     try ( Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword)) {   
	    	 try (Statement st = conn.createStatement()){
	    		 	ResultSet rs = st.executeQuery(query);
	                
	                while(rs.next()){
	                	prezzo = rs.getDouble(1);
	                	if(prezzo >= 750)
	                		return true;
	                	else
	                		return false;
	                } 
	            }catch(SQLException e){
	        	    	System.out.println("Errore: " + e);
	        	    	System.exit(1);
	            }
	     } catch(SQLException e){
	    	System.out.println("Errore: " + e);
	    	System.exit(1);
	     }
	   return false;
	}
}
