package com.example.billetms.Batch.Configuration;

import com.example.billetms.entities.Book;
import com.example.billetms.entities.Client;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseConnect {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_USER_URL = "jdbc:postgresql://localhost:5432/p7apiweb";
    static final String DB_BOOK_URL = "jdbc:postgresql://localhost:5432/p7BookndBiblio";

    static final String USER = "postgres" ;
    static final String PASS = "mpompo98";


    public static Book getBookFromDB(String bookId) {
        Book book = new Book();
        try (Connection connection = DriverManager.getConnection(DB_BOOK_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT book.id ,book.titre FROM public.book WHERE book.id IN(" + bookId + ")");
            while (resultSet.next()) {
            book.setId(resultSet.getLong("id"));
            book.setTitre(resultSet.getString("titre"));
            }
            resultSet.close();

        }
        catch (SQLException e){
            System.out.println("Connection fail BookDB");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Fail to load driver");
            e.printStackTrace();
        }
        return book;
    }

    public static Client getClientFromDB (String bookerId) {
        Client client = new Client();
        try( Connection connection = DriverManager.getConnection(DB_USER_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT client.id ,client.nom , client.prenom , client.mail FROM p7apiweb.public.client WHERE client.id IN  (" + bookerId + ")");

            while (resultSet.next()) {
                client.setNom(resultSet.getString("nom"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setMail(resultSet.getString("mail"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println("Connection fail. client DB");
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("Fail to load driver");
            e.printStackTrace();
        }
        return client;

    }


}
