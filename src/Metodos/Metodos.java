/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author X541
 */
public class Metodos {
     public static void createNewDatabase(String fileName) {

 

        String url = "jdbc:sqlite:C:\\Users\\X541\\Documents\\NetBeansProjects\\Database\\" + fileName;

 

        try (Connection conn = DriverManager.getConnection(url)) {

            if (conn != null) {

                DatabaseMetaData meta = conn.getMetaData();

                System.out.println("Driver " + meta.getDriverName());

                System.out.println("Base de datos creada.");

            }

 

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

 



    public static void createNewTable() {

        // url = ruta de la base de datos

        String url = "jdbc:sqlite:C:\\Users\\X541\\Documents\\NetBeansProjects\\Database\\testeo.db";

        

         //SQL statement for creating a new table

        String sql2 = "CREATE TABLE IF NOT EXISTS ESCUELA (\n"

                + "	dni PRIMARY KEY, \n"

                + "	nombre text NOT NULL, \n"

                + "     telefono text \n"              

                + " );";

        

        

        String sql = "CREATE TABLE IF NOT EXISTS ALUMNO (\n"

                + "	id integer PRIMARY KEY, \n"

                + "	nombre text NOT NULL, \n"

                + "	apellido text NOT NULL, \n"

                + "     dni text, \n"

                + "     FOREIGN KEY(dni) REFERENCES ESCUELA (dni) \n"

                + " );";

        
        

        try (Connection conn = DriverManager.getConnection(url);

                Statement stmt = conn.createStatement()) {

            // creamos la tabla cargando nuestra sentencia en la variable sql

            stmt.execute(sql2);

            stmt.execute(sql);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

   

    Connection connect() {

        // url = ruta de nuestra base de datos

        String url = "jdbc:sqlite:C:\\Users\\X541\\Documents\\NetBeansProjects\\Database\\testeo.db";

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url);

            System.out.println("Conexión establecida.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return conn;

    }

 

    

    

    public void selectAll(){
 String sql = "SELECT id, nombre, apellido, dni FROM ALUMNO";

        

        try (Connection conn = this.connect();

             Statement stmt  = conn.createStatement();

             ResultSet rs    = stmt.executeQuery(sql)){

            

            // bucle para imprimir todos los datos de nuestra tabla

            while (rs.next()) {

                System.out.println(rs.getInt("id") +  "\t" + 

                                   rs.getString("nombre") + "\t" +

                                   rs.getString("apellido") + "\t"+

                                   

                                   rs.getString("dni"));

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        
        
            

        }

    }

    public void insert(String nombre, String apellido, String dni) {
String sql = "INSERT INTO ALUMNO (nombre,apellido,dni) VALUES(?,?,?)";

 

        try (Connection conn = this.connect();

                PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, nombre);

            pstmt.setString(2, apellido);

            pstmt.setString(3, dni);

            pstmt.executeUpdate();

            

            

        } catch (SQLException e) {

            System.out.println(e.getMessage());
       

        
        

        }

    }

    public void insert2(String dni, String nombre, String telefono) {

       

        // insert para añadir clientes a nuestra base de datos

        // el id se genera autómaticamente

        String sql = "INSERT INTO ESCUELA(dni,nombre,telefono) VALUES(?,?,?)";

 

        try (Connection conn = this.connect();

                PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, dni);

            pstmt.setString(2, nombre);

            pstmt.setString(3, telefono);

            pstmt.executeUpdate();

            

            

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public void update(int id, String nombre, String apellido) {

        String sql = "UPDATE ALUMNO SET nombre = ? , "

                + "apellido = ?  "

                + "WHERE id = ?";

 

        try (Connection conn = this.connect();

                PreparedStatement pstmt = conn.prepareStatement(sql)) {

 

            // set the corresponding param

            pstmt.setString(1, nombre);

            pstmt.setString(2, apellido);

            pstmt.setInt(3, id);

            // update 

            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    

    public void update2(String dni, String nombre, String telefono) {

        String sql = "UPDATE ESCUELA SET nombre = ? , "               

                + "telefono = ? "                

                + "WHERE dni = ?";

 

        try (Connection conn = this.connect();

                PreparedStatement pstmt = conn.prepareStatement(sql)) {

 

            // set the corresponding param

            pstmt.setString(1, nombre);

            pstmt.setString(2, telefono);

            pstmt.setString(3, dni);

            // update 

            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    

    public void delete(int id) {

        String sql = "DELETE FROM ALUMNO WHERE id = ?";

 

        try (Connection conn = this.connect();

                PreparedStatement pstmt = conn.prepareStatement(sql)) {

 

            // set the corresponding param

            pstmt.setInt(1, id);

            // execute the delete statement

            pstmt.executeUpdate();

 

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public void delete2(String dni) {

        String sql = "DELETE FROM ESCUELA WHERE dni = ?";

 

        try (Connection conn = this.connect();

                PreparedStatement pstmt = conn.prepareStatement(sql)) {

 

            // set the corresponding param

            pstmt.setString(1, dni);

            // execute the delete statement

            pstmt.executeUpdate();

 

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public String getCif(String id){

        String sql = "SELECT dni FROM ALUMNO where id ="+id;

        String dato = "";

        try (Connection conn = this.connect();

             Statement stmt  = conn.createStatement();

             ResultSet rs    = stmt.executeQuery(sql)){

            

            dato = rs.getString("dni");

            

        } catch (SQLException ex) {

           Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);

       }

     

       return dato;

    }

    

    public int getId(){

        //como el id se genera automáticamente tenemos que recuperarlo 

        //para introducirlo en la tabla

        String sql = "SELECT max(id) from ALUMNO";

        int rowID = 0;

        try (Connection conn = this.connect();

             Statement stmt  = conn.createStatement();

             ResultSet rs    = stmt.executeQuery(sql)){            

             rowID = rs.getInt("max(id)");

            

        } catch (SQLException ex) {

           Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);

       }

     

       return rowID;

}

    

    public ArrayList<Object[]> tablas(){

       ArrayList<Object[]> tablas  = new ArrayList<>();

       String sql = "SELECT id, nombre, apellido, dni FROM ALUMNO";

        

        try (Connection conn = this.connect();

             Statement stmt  = conn.createStatement();

             ResultSet rs    = stmt.executeQuery(sql)){

        while (rs.next()) {

                Object[] datos = new Object[4];

                datos[0] = Integer.toString(rs.getInt("id"));

                datos[1] = rs.getString("nombre");

                datos[2] = rs.getString("apellido");

                datos[3] = rs.getString("dni");

                

                tablas.add(datos);

        }        

   

    }  catch (SQLException ex) {

           Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);

       }

     

        return tablas;

        }

    public ArrayList<Object[]> tablaE(){

       ArrayList<Object[]> tablaE  = new ArrayList<>();

       String sql = "SELECT dni, nombre, telefono FROM ESCUELA";

        

        try (Connection conn = this.connect();

             Statement stmt  = conn.createStatement();

             ResultSet rs    = stmt.executeQuery(sql)){

        while (rs.next()) {

                Object[] datos = new Object[3];               

                datos[0] = rs.getString("dni");

                datos[1] = rs.getString("nombre");

                datos[2] = rs.getString("telefono");

                

                tablaE.add(datos);

        }        

   

    }  catch (SQLException ex) {

           Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);

       }

     

        return tablaE;

        }

    

    

}

