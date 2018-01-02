package GIS.csv;

import GIS.geometry.Polygon;
import GIS.geometry.Polyline;
import GIS.geometry.Point;
import java.util.ArrayList;

/*
 */
/** 
 *  An Object CSVfile can be instanced by handing over the needed connection parameters (path, delimiter). With a Scanner the CSV-file can be acessed. A CSV-file needs to have these three columns: ID, type, geometry.
 */
public class CSVfile {

  /** 
   *  The path to the CSV file to import/store
   */
  public String path;

  /** 
   *  The delimeter seperating the columns
   *  (not allowed to be ",")
   */
  public String delimiter;

  public boolean openCSV() {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  get attribute id and type from the Point Object. Call the writeGeometryAsText method to get the geometry in something that you could call wellknowntext.
   *  Insert id, type and geometry as a new line in the CSV.
   *  
   *  return true when processed sucessfull
   */
  public boolean insertPoint(Point point) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  get attribute id and type from the Polyline Object. Call the writeGeometryAsText method to get the geometry in something that you could call wellknowntext.
   *  Insert id, type and geometry as a new line in the CSV.
   *  
   *  return true when processed sucessfull
   */
  public boolean insertPolyline(Polyline polyline) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  get attribute id and type from the Polygon Object. Call the writeGeometryAsText method to get the geometry in something that you could call wellknowntext.
   *  Insert id, type and geometry as a new line in the CSV.
   *  
   *  return true when processed sucessfull
   */
  public boolean insertPolygon(Polygon polygon) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  Input Parameter type needs to be one of this three strings: "Point" "Polyline" "Polygon"
   *  load all rows stored in the DB where type="polyline".
   *  For each row 
   *  1. create a Polyline object using the column values of ID and type.
   *  2. Assign the coordinates using the method geometryAsTextToPolyline, which will use as input parameter the string where the geometry as wellknowntext is saved.
   *  
   *  Store the Polylines in an Array List of the type Polyline.
   *  return the Array List.
   */
  public ArrayList loadGeometries(String type) {
  /* {author=Name, version=1.0}*/

  return null;
  }

  public boolean deleteGeometry(int id) {
  /* {author=Name, version=1.0}*/

  return false;
  }

}