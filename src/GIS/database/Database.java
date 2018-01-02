package GIS.database;

import GIS.geometry.GISPolygon;
import GIS.geometry.GISPolyline;
import GIS.geometry.GISPoint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 */
/** 
 *  An Object Database can be instanced by handing over the needed connection parameters (Database name, Database port, Database user, user password and table name). With a Java Driver a connection to a Database can then be build up (method dbConnect). A Database table needs to have these three columns with the respective datatypes: ID (INTEGER AUTO INCREMENT PRIMARY KEY), type (TEXT), geometry (TEXT).
 *  
 *  Features in the DB can be inserted, loaded or deleted.
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
   *  return true when processed sucessfull
     * @return 
     * @throws java.sql.SQLException 
   */
  public Connection dbConnect() throws SQLException {
  /* {author=Name, version=1.0}*/
    String connection = "jdbc:"+ DBMS +"://" + dbHost + ":" + dbPort + "/" + dbName + "?autoReconnect=true&useSSL=false";    
    Connection dbmsconn = DriverManager.getConnection(connection, dbUser, dbPassword);
    return dbmsconn;
  }

  /** 
   *  get attribute id and type from the Point Object. Call the writeGeometryAsText method to get the geometry in something that you could call wellknowntext.
   *  Insert id, type and geometry in the respective DB columns
   *  
   *  return true when processed sucessfull
   */
  public boolean insertPoint(GISPoint point) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  get attribute id and type from the Polyline Object. Call the writeGeometryAsText method to get the geometry in something that you could call wellknowntext.
   *  Insert id, type and geometry in the respective DB columns
   *  
   *  return true when processed sucessfull
   */
  public boolean insertPolyline(GISPolyline polyline) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  get attribute id and type from the Polygon Object. Call the writeGeometryAsText method to get the geometry in something that you could call wellknowntext.
   *  Insert id, type and geometry in the respective DB columns
   *  
   *  return true when processed sucessfull
   */
  public boolean insertPolygon(GISPolygon polygon) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  load all rows stored in the DB where type="point".
   *  For each row 
   *  1. create a Point object using the column values of ID and type.
   *  2. Assign the coordinates using the method geometryAsTextToPoint, which will use as input parameter the string where the geometry as wellknowntext is saved.
   *  
   *  Store the Points in an Array List of the type Point.
   *  return the Array List.
   */
  public ArrayList loadPoints() {
  /* {author=Name, version=1.0}*/

  return null;
  }

  /** 
   *  load all rows stored in the DB where type="polyline".
   *  For each row 
   *  1. create a Polyline object using the column values of ID and type.
   *  2. Assign the coordinates using the method geometryAsTextToPolyline, which will use as input parameter the string where the geometry as wellknowntext is saved.
   *  
   *  Store the Polylines in an Array List of the type Polyline.
   *  return the Array List.
   */
  public ArrayList loadPolylines() {
  /* {author=Name, version=1.0}*/

  return null;
  }

  /** 
   *  load all rows stored in the DB where type="polygon".
   *  For each row 
   *  1. create a Polygon object using the column values of ID and type.
   *  2. Assign the coordinates using the method geometryAsTextToPolygon, which will use as input parameter the string where the geometry as wellknowntext is saved.
   *  
   *  Store the Polygons in an Array List of the type Polygon.
   *  return the Array List.
   */
  public ArrayList loadPolygons() {
  /* {author=Name, version=1.0}*/

  return null;
  }

  /** 
   *  Delete a Feature with the ID the user has defined:
   *  DELETE * FROM table WHERE id =featureid;
   */
  public boolean deleteGeometry(int id) {
  /* {author=Name, version=1.0}*/

  return false;
  }

}