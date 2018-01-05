package GIS.database;

import GIS.drawing.*;
import GIS.geometry.GISPolygon;
import GIS.geometry.GISPolyline;
import GIS.geometry.GISPoint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 */
/** 
 *  An Object Database can be instanced by handing over the needed connection 
 *  parameters (Database name, Database port, Database user, user password and table name). 
 *  With a Java Driver a connection to a Database can then be build up (method dbConnect). 
 *  A Database table needs to have these three columns with the respective datatypes: 
 *  gid (INTEGER AUTO INCREMENT PRIMARY KEY), type (VARCHAR(10)), geom (LONGTEXT).
 *  
 *  
 */
public class Database {

   /** 
   *  the Database Management System
   */
  public String DBMS;
    
   /** 
   *  the Database name
   */
  public String dbName;

   /** 
   *  The Database server port
   */
  public String dbHost;

  /** 
   *  The Database server port
   */
  public String dbPort;

  /** 
   *  the Database user name
   */
  public String dbUser;

  /** 
   *  The password of the given Database user
   */
  public String dbPassword;

  /** 
   *  the table name inside the DB
   */
  public String tableName;
   
  
  /** 
   *  creates a db connection using the connection parameters
     * @return Connection object when sucessfull, SQLExeption if not
     * @throws java.sql.SQLException 
   */
  public Connection dbConnect() throws SQLException {
  /* {author=Name, version=1.0}*/
    String connection = "jdbc:"+ DBMS +"://" + dbHost + ":" + dbPort + "/" + dbName + "?autoReconnect=true&useSSL=false";    
    Connection dbmsconn = DriverManager.getConnection(connection, dbUser, dbPassword);
    return dbmsconn;
  }


// maybe create a usable table automaticly if user wants it??
//  CREATE TABLE `data`.`shapes` (
//  `gid` INT NOT NULL AUTO_INCREMENT,
//  `type` VARCHAR(10) NULL,
//  `geom` LONGTEXT NULL,
//  PRIMARY KEY (`gid`));
  
  
  /** 
   *  get attribute id and type from the Point Object. Call the writeGeometryAsText method to get the geometry in something that you could call wellknowntext.
   *  Insert id, type and geometry in the respective DB columns
   *  
   *  return true when processed sucessfull
     * @param c Content with the actually drawn geometries
     * @throws java.sql.SQLException
   */
  public void insertContent(Content c) throws SQLException {
  /* {author=Name, version=1.0}*/
  
    // get a connection
    Connection con = dbConnect();
    
    // the strings to put into the columns
    String type;
    String geom;
    
    // insert all Points into the DB
    for (GISPoint p : c.pointlist) {
        type = p.getType();
        geom = p.getGeometryAsText();
        PreparedStatement posted = con.prepareStatement("INSERT INTO shapes (type, geom) VALUES('"+type+"','"+geom+"')");
        posted.executeUpdate();
    }
    
    // insert all Polylines into the DB
    for (GISPolyline p : c.polylinelist) {
        type = p.getType();
        geom = p.getGeometryAsText();
        PreparedStatement posted = con.prepareStatement("INSERT INTO shapes (type, geom) VALUES('"+type+"','"+geom+"')");
        posted.executeUpdate();
    }  
    
    // insert all Polygons into the DB
    for (GISPolygon p : c.polygonlist) {
        type = p.getType();
        geom = p.getGeometryAsText();
        PreparedStatement posted = con.prepareStatement("INSERT INTO shapes (type, geom) VALUES('"+type+"','"+geom+"')");
        posted.executeUpdate();
    }  
  }

  /** 
   *  load all rows stored in the DB where type="point".
   *  For each row 
   *  1. create a Point object using the column values of ID and type.
   *  2. Assign the coordinates using the method geometryAsTextToPoint, 
   *  which will use as input parameter the string where the geometry as 
   *  wellknowntext is saved.
   *  
   *  Store the Points in an Array List of the type Point.
     * @return Content object with the loaded data from the DB
     * @throws java.sql.SQLException 
   */
  public Content loadContent() throws SQLException {
  /* {author=Name, version=1.0}*/
  
    //create new Content object to return later
    Content c = new Content();
  
    try {
        //get a connection
        Connection con = dbConnect();        
        
        //declare a ResultSet and an SQL Statement
        java.sql.ResultSet resultSet;
        PreparedStatement display;

        // execute Statement and store in resultSet
        display = con.prepareStatement("SELECT * FROM shapes");
        resultSet = display.executeQuery();
        
        while (resultSet.next()){
            // load type and geom from the database
            String type = resultSet.getString("type");
            String geom = resultSet.getString("geom");
            switch (type) {

                // if type is a point 
                case "Point":
                    // create empty point
                    GISPoint p = new GISPoint();
                    // set Geometry of empty point to geom
                    p.setGeometryFromText(geom);
                    // add point to pointlist
                    c.addPoint(p);
                    break;
                    
                // if type is a polyline
                case "Polyline":
                    // create empty polyline
                    GISPolyline polyl = new GISPolyline();
                    // set Geometry of empty polyline to geom
                    polyl.setGeometryFromText(geom);
                    // add point to polylinelist
                    c.addPolyline(polyl);
                    break;
                    
                // if type is a polyline
                case "Polygon":
                    // create empty point
                    GISPolygon polyg = new GISPolygon();
                    // set Geometry of empty point to geom
                    polyg.setGeometryFromText(geom);
                    // add point to pointlist
                    c.addPolygon(polyg);
                    break;
            }
        }  
        
        resultSet.close();
        display.close();
    }

    catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    return c;
  }
}