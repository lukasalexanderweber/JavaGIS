package GIS.drawing;

import GIS.geometry.GISPolygon;
import GIS.geometry.GISPolyline;
import GIS.geometry.GISPoint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/*
 */
public class Content {

  public ArrayList<GISPoint> pointlist = new ArrayList();
  public ArrayList<GISPolyline> polylinelist = new ArrayList();
  public ArrayList<GISPolygon> polygonlist = new ArrayList();
  
  public ArrayList<GISPoint> SelectedPointlist = new ArrayList();
  public ArrayList<GISPolyline> SelectedPolylinelist = new ArrayList();
  public ArrayList<GISPolygon> SelectedPolygonlist = new ArrayList();

  
  public void addPoint(GISPoint point) {
  /* {author=Name, version=1.0}*/
    pointlist.add(point);
  }

  public void addPolyline(GISPolyline polyline) {
  /* {author=Name, version=1.0}*/
    polylinelist.add(polyline);
  }
  /**
  * refreshes the last element of the arraylist polyline while drawing
     * @param polyline the updated polyline
  */
  public void refreshPolyline(GISPolyline polyline) {
  /* {author=Name, version=1.0}*/
    polylinelist.set(polylinelist.size() - 1, polyline);
  }

  public void addPolygon(GISPolygon polygon) {
  /* {author=Name, version=1.0}*/
    polygonlist.add(polygon);
  }
  
  public void refreshPolygon(GISPolygon polygon) {
  /* {author=Name, version=1.0}*/
    polygonlist.set(polygonlist.size() - 1, polygon);
  }

  public void removePoint(int id) {
  /* {author=Name, version=1.0}*/
    
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