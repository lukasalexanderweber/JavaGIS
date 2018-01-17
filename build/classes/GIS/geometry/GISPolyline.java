package GIS.geometry;

import java.awt.geom.Path2D;
import java.util.ArrayList;


/** 
 *  Polyline Class, inherits from Geometry.
 *  Points can be added to the empty line with the Method "addPoint()", that will add a Point Object to the Point ArrayList.
 *  To write Feature in DB or CSV the coordinates can be transformed to the right format using "writeAsText()". 
 *  To read it from a DB or CSV use "textToPoint()"
 *  To get a drawable geometry for Graphics2D use "getGeometry()"
 * @author Lukas
 */
public class GISPolyline extends Geometry {
  /* {author=Lukas, version=1.0}*/

  // ATTRIBUTES
  /** 
   *  An ArrayList of Point objects which define the geometry of the Polyline
   */
  public ArrayList<GISPoint> pointlist = new ArrayList();
  /** 
   *  the length of the line
   */
  public float length;
    /**
     *  the length of the line
     */
  public int NoPoints = 0;

  
  // METHODS
  /** 
   *  Creates an empty Polyline Object.
   *  Constructor calls the constructor of the super class (Geometry)
   *  and sets the type
   */
    public GISPolyline() {
        super("Polyline");
    }
    
   /** 
   *  The class GISPolygon inherits from GISPolyline and must be able to change 
   *  the type of the Geometry class to "Polygon". This method provides that
   *  functionality.
   */
    public void setTypeToPolygon(){
        type = "Polygon";
    }

    /** 
     *  Adds a point object to the attribute pointlist
     *  return true when processed sucessfull
     * @param point an instance of the class GISPoint
     */
    public void addPoint(GISPoint point) {
    /* {author=Name, version=1.0}*/
        pointlist.add(point);
        NoPoints = pointlist.size();
    }
  
  /** 
   *  write the points of a line so that they can be stored as a string in a DB/CSV and be reconstructed to a Point when loaded.
   *  should be something like that:
   *  
   *  x1 = 1.234
   *  y1 = 5.678
   *  x2 = 4.321
   *  y2 = 8.765
   *  
   *  getGeometryAsText()
   *  
   *  "1.234 5.678,4.321 8.765"
   *  - 2 seperators: 
   *  "," for different points
   *  " " for the two coordinates of a point
     * @return String with the Geometry as readable Text
   */
  public String getGeometryAsText() {
  /* {author=Name, version=1.0}*/
    String geom = new String();
    // for each point in pointlist call it's writeGeometryAsText() method and 
    // concatenate with a comma to seperate the points 
    for (GISPoint p : pointlist) { 
        geom += p.getGeometryAsText() + ",";
    }
    // remove last comma if pointlist is not empty (if empty overflow error would be thrown)
    if (!pointlist.isEmpty()){
        geom = geom.substring(0, geom.length() - 1);
    }
    else{
        System.err.println("Error in writeGeometryAsText(): no Points in Pointlist");
    }
    
    return geom;
  }

  /** 
   *  when receiving a feature geometry from a DB/CSV it will be stored in the string:
   *  "1.234 5.678,4.321 8.765"
   *  
   *  textToPoint("1.234 5.678,4.321 8.765") will split the string at the "," to receive the different points. " " spereates their x and y coordinates. For each coordinate pair a Point object will be generated and stored in the ArrayList "pointlist". 
   *  
   *  return true when processed sucessfull
     * @param geometry a String received from writeGeometryAsText()
     * @return true when method was sucessfull, false for errors
   */
  public boolean setGeometryFromText(String geometry) {
  /* {author=Name, version=1.0}*/
    try {
        // if there are no Points in pointlist
        if (pointlist.isEmpty()){
            // split at "," to get an array of points
            String[] points = geometry.split(",");
            // loop through all points
            for (String p : points) { 
                // split at " " to seperate x and y
                String[] coords = p.split(" ");
                // create new GISPoint
                GISPoint GISp = new GISPoint();
                // assign the coordinates to the GISPoint
                GISp.x = Double.parseDouble(coords[0]);
                GISp.y = Double.parseDouble(coords[1]);
                // add point to pointlist
                pointlist.add(GISp);
            }
            NoPoints = pointlist.size();
            return true;
        }
        else{
            System.err.println("Error in setGeometryFromText: GISPolyline not empty");
            return false;
        }
    }
    catch (NumberFormatException e) {
        System.err.println("Error while parsing Geometry String to Point\n" + e);
        return false;
    }
  }
  
  public Path2D getGeometry() {
    // create new Path2D
    Path2D Poly;
    Poly = new Path2D.Double();
        
    // move Path2D to location of first point  
    GISPoint firstPoint = pointlist.get(0);
    Poly.moveTo(firstPoint.getX(), firstPoint.getY());
    
    // subset all points but first, if more than one point in list
    if (pointlist.size() > 1){
        ArrayList<GISPoint> pointlist2 = new ArrayList<>(pointlist.subList(1, NoPoints));
        // and add them to the Path2D
        for (GISPoint p : pointlist2) {
            Poly.lineTo(p.getX(), p.getY());
        }        
    }
    
    return Poly;
      
  }                      

  /** 
   *  calculate the length of a Polyline, stored in Attribute length
   *  (not realized yet, could be done using the Path2D object)
   */
  public void calculateLength() {
  /* {author=Name, version=1.0}*/

  }

  /** 
   *  returns the value of attribute length of a Polyline.
   *  (if the attribute is not filled yet, tell the user to use calculateLength)
   *  (not realized yet, could be done using the Path2D object)
     * @return length (float)
   */
  public float getLength() {
  /* {author=Name, version=1.0}*/

  return (float) 0.0;
  }

}