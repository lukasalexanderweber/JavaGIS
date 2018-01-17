package GIS.drawing;

import GIS.geometry.GISPolygon;
import GIS.geometry.GISPolyline;
import GIS.geometry.GISPoint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/** A class which can store the actual drawed GIS objects, enables adding and deleting 
 * of objects from it.
 * @author Lukas
 */
public class Content {

    /** List of Point Objects actually drawn
     *
     */
    public ArrayList<GISPoint> pointlist = new ArrayList();

    /** List of Polyline Objects actually drawn
     *
     */
    public ArrayList<GISPolyline> polylinelist = new ArrayList();

    /** List of Polygon Objects actually drawn
     *
     */
    public ArrayList<GISPolygon> polygonlist = new ArrayList();
  
    /** List of Point Objects actually selected
     *
     */
    public ArrayList<GISPoint> SelectedPointlist = new ArrayList();

    /** List of Polyline Objects actually selected
     *
     */
    public ArrayList<GISPolyline> SelectedPolylinelist = new ArrayList();

    /** List of Polygons Objects actually selected
     *
     */
    public ArrayList<GISPolygon> SelectedPolygonlist = new ArrayList();

    /**
     * Adds a new Point Object to the pointlist
     * @param point GISPoint
     */
    public void addPoint(GISPoint point) {
        pointlist.add(point);
    }

    /**
     * Adds a new Polyline Object to the polylinelist
     * @param polyline GISPolyline
     */
    public void addPolyline(GISPolyline polyline) {
        polylinelist.add(polyline);
    }
  
    /**
     * Refreshes the last element of the polylinelist while Polyline draw
     * @param polyline updated GISPolyline
     */
    public void refreshPolyline(GISPolyline polyline) {
        polylinelist.set(polylinelist.size() - 1, polyline);
    }

    /**
     * Adds a new Polygon Object to the polygonlist
     * @param polygon GISPolygon
     */
    public void addPolygon(GISPolygon polygon) {
        polygonlist.add(polygon);
    }
  
    /**
     * Refreshes the last element of the polygonlist while Polygon draw
     * @param polygon updated GISPolygon
     */
    public void refreshPolygon(GISPolygon polygon) {
        polygonlist.set(polygonlist.size() - 1, polygon);
    }

    /**
     * Removes point from pointlist based on its ID
     * @param id the ID of the point to delete
     */
    public void removePoint(int id) {
    
    // loop through the points and get the id  
    for (int i=0; i<pointlist.size(); i++){
        GISPoint point = pointlist.get(i);
        int pointID = point.getID();
        // if point id matches with selected id
        if (pointID == id){
            //remove this point from the pointarray
            pointlist.remove(i);
        }
    }
  }

    /**
     * Removes polyline from polylinelist based on its ID
     * @param id the ID of the polyline to delete
     */
    public void removePolyline(int id) {
  /* {author=Name, version=1.0}*/
  
    // loop through the polylines and get the id  
    for (int i=0; i<polylinelist.size(); i++){
        GISPolyline polyline = polylinelist.get(i);
        int polylineID = polyline.getID();
        // if polyline id matches with selected id
        if (polylineID == id){
            //remove this polyline from the polylinearray
            polylinelist.remove(i);
        }
        i += 1;
    }
  }

    /**
     * Removes polygon from polygonlist based on its ID
     * @param id the ID of the polygon to delete
     */
    public void removePolygon(int id) {
  /* {author=Name, version=1.0}*/
    
    // loop through the polygons and get the id  
    for (int i=0; i<polygonlist.size(); i++){
        GISPolyline polygon = polygonlist.get(i);
        int polygonID = polygon.getID();
        // if polygon id matches with selected id
        if (polygonID == id){
            //remove this point from the pointarray
            polygonlist.remove(i);
            break;
        }
        i += 1;
    }
  }
  
    /**
     * Select all Geometries of the Content which are contained by a user defined rectangle.
     * @param rect a user defined rectangle
     */
    public void selectGeometries(Rectangle2D rect) {
  /* {author=Name, version=1.0}*/
    // overwrite existing selection:
    SelectedPointlist = new ArrayList();
    SelectedPolylinelist = new ArrayList();
    SelectedPolygonlist = new ArrayList();
    
    pointlist.forEach((GISPoint point) -> {
        // get the 2D geometry
        Ellipse2D geom = point.getGeometry();
        // calculate bounding rectangle
        Rectangle2D boundingRec = geom.getBounds2D();
        // contain query between selection rectangle and bounding rectangle
        if (rect.contains(boundingRec) == true){
            SelectedPointlist.add(point);
            System.out.println("PointID " + point.getID());
        }
    }); 
    
    polylinelist.forEach((GISPolyline polyline) -> {
        // get the 2D geometry
        Path2D geom = polyline.getGeometry();
        // calculate bounding rectangle
        Rectangle2D boundingRec = geom.getBounds2D();
        // contain query between selection rectangle and bounding rectangle
        if (rect.contains(boundingRec) == true){
            SelectedPolylinelist.add(polyline);
            System.out.println("PolylineID " + polyline.getID());
        }
    }); 
    
    polygonlist.forEach((GISPolygon polygon) -> {
        // get the 2D geometry
        Path2D geom = polygon.getGeometry();
        // calculate bounding rectangle
        Rectangle2D boundingRec = geom.getBounds2D();
        // contain query between selection rectangle and bounding rectangle
        if (rect.contains(boundingRec) == true){
            SelectedPolygonlist.add(polygon);
            System.out.println("PolygonID " + polygon.getID());
        }
    }); 
    
  }
  
    /**
     * Delete selected objects defined by a user defined rectangle
     */
    public void deleteSelectedGeometries() {
      //DELETE POINTS:
      for (GISPoint point: SelectedPointlist){
          //get the ID
          int id = point.getID();
          //delete the ID from the pointlist
          removePoint(id);
      }
      //DELETE POLYLINES:
      SelectedPolylinelist.forEach((GISPolyline line) -> {
          //get the ID
          int id = line.getID();
          //delete the ID from the polylinelist
          removePolyline(id);
      });
      //DELETE POINTS:
      SelectedPolygonlist.forEach((GISPolygon polygon) -> {
          //get the ID
          int id = polygon.getID();
          //delete the ID from the polygonlist
          removePolygon(id);
      });
      
  }

}