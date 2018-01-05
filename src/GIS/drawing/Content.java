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
  }

  public void removePolyline(int id) {
  /* {author=Name, version=1.0}*/
  }

  public void removePolygon(int id) {
  /* {author=Name, version=1.0}*/
  }
  
  public void selectGeometries(Rectangle2D rect) {
  /* {author=Name, version=1.0}*/
    // overwrite existing selection:
    SelectedPointlist = new ArrayList();
    SelectedPolylinelist = new ArrayList();
    SelectedPolygonlist = new ArrayList();
    
    pointlist.forEach((GISPoint point) -> {
        Ellipse2D geom = point.getGeometry();
        // calculate bounding rectangle
        Rectangle2D boundingRec = geom.getBounds2D();
        // contain query between selection rectangle and bounding rectangle
        if (rect.contains(boundingRec) == true){
            SelectedPointlist.add(point);
            System.out.println(point.getID());
        }
    }); 
    
    polylinelist.forEach((GISPolyline polyline) -> {
        Path2D geom = polyline.getGeometry();
        // calculate bounding rectangle
        Rectangle2D boundingRec = geom.getBounds2D();
        // contain query between selection rectangle and bounding rectangle
        if (rect.contains(boundingRec) == true){
            SelectedPolylinelist.add(polyline);
            System.out.println(polyline.getID());
        }
    }); 
    
    polygonlist.forEach((GISPolygon polygon) -> {
        Path2D geom = polygon.getGeometry();
        Rectangle2D boundingRec = geom.getBounds2D();
        if (rect.contains(boundingRec) == true){
            SelectedPolygonlist.add(polygon);
            System.out.println(polygon.getID());
        }
    }); 
    
  }

}