package GIS.geometry;

import static GIS.GIS.gis;
import GIS.drawing.Content;
import java.util.ArrayList;
import java.util.Collections;

/** 
 *  Parent class for the three geometry types.
 */
public class Geometry {
  /* {author=Lukas, version=1.0}*/

  // ATTRIBUTES
  /** 
   *  unique ID of the Geometrie
   *  (we have to think about how to assign them)
   */
  public int id = setUniqueID(gis.c);

  /** 
   *  type: One of Point, Polygon or Polyline
   *  will be assigned in the constructor of the subclasses
   */
  public String type;
  
  // METHODS

   /** 
   *  Constructor sets the value of attributes id and type
   *  Type is specific from the created Geometry and is passed by super("Point")
     * @param type one of "Point", "Polyline" or "Polygon"
   */
  public Geometry(String type){
      // TO-DO: get existing ID's and create a unique one!!
      this.type = type;
  }
  
  /** 
   *  returns the value of the attribute id
     * @return ID of the object
   */
  public int getID() {
       return this.id;
  }

  /** 
   *  returns the value of the attribute type
     * @return Type of the object
   */
  public String getType() {
       return this.type;
  }
  
  public int setUniqueID(Content c){
    ArrayList<Integer> ids = new ArrayList();
    c.pointlist.forEach((GISPoint point) -> {
        ids.add(point.getID());
    }); 
    c.polylinelist.forEach((GISPolyline polyline) -> {
        ids.add(polyline.getID());
    }); 
    c.polygonlist.forEach((GISPolygon polygon) -> {
        ids.add(polygon.getID());
    }); 
    c.SelectedPointlist.forEach((GISPoint point) -> {
        ids.add(point.getID());
    }); 
    c.SelectedPolylinelist.forEach((GISPolyline polyline) -> {
        ids.add(polyline.getID());
    }); 
    c.SelectedPolygonlist.forEach((GISPolygon polygon) -> {
        ids.add(polygon.getID());
    }); 
      
    int maxid;
    
    if (ids.isEmpty()){
      maxid = 1;
    }
    else{
        maxid = Collections.max(ids);
    }   
    
    System.out.println(maxid+1);
    return maxid + 1;
  }
  
}