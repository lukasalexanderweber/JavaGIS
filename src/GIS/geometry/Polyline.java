package GIS.geometry;

import java.util.ArrayList;

/** 
 *  Creates an Polyline Object.
 *  Points can be added to the empty line with the Method "addPoint()", that will add a Point Object to the Point ArrayList.
 *  To write Feature in DB or CSV the coordinates can be transformed to the right format using "writeAsText()". To read it from a DB or CSV use "textToPoint()"
 */
public class Polyline extends Geometry {
  /* {author=Name, version=1.0}*/


  /** 
   *  An array of Point objects which define the geometry of the Polyline
   */
  public ArrayList pointlist;

  /** 
   *  the length of the line
   */
  public float length;

  /** 
   *  write the points of a line so that they can be stored as a string in a DB/CSV and be reconstructed to a Point when loaded.
   *  should be something like that:
   *  
   *  x1 = 1.234
   *  y1 = 5.678
   *  x2 = 4.321
   *  y2 = 8.765
   *  
   *  writeAsText()
   *  
   *  "1.234|5.678;4.321|8.765"
   *  -> 2 seperators: 
   *  ";" for different points
   *  "|" for the two coordinates of a point
   */
  public String writeGeometryAsText() {
  /* {author=Name, version=1.0}*/

  return null;
  }

  /** 
   *  Adds a point object to the attribute pointlist
   *  return true when processed sucessfull
   */
  public boolean addPoint(Point point) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  when receiving a feature geometry from a DB/CSV it will be stored in the string:
   *  "1.234 5.678,4.321 8.765"
   *  
   *  textToPoint("1.234 5.678,4.321 8.765") will split the string at the "," to receive the different points. " " spereates their x and y coordinates. For each coordinate pair a Point object will be generated and stored in the ArrayList "pointlist". 
   *  
   *  return true when processed sucessfull
   */
  public boolean geometryAsTextToLine(String geometry) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  calculate the length of a Polyline, stored in Attribute length
   */
  public void calculateLength() {
  /* {author=Name, version=1.0}*/

  }

  /** 
   *  returns the value of attribute length of a Polyline
   *  (if the attribute is not filled yet, tell the user to use calculateLength)
   */
  public float getLength() {
  /* {author=Name, version=1.0}*/

  return (float) 0.0;
  }

}