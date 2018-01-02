/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.drawing;

import GIS.geometry.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;


// here the JPanel is created
public class DrawingPanel extends JPanel {
            
    public double factor;
    public double horizontal;
    public double vertical;

    /**
     * ghd
     * @param f fgd
     * @param h
     * @param v 
     */
    public void setPan(double f, double h, double v) { 
        this.factor = f; 
        this.horizontal = h;
        this.vertical = v;
    }
    
    public AffineTransform getCurrentTransform() {
        AffineTransform tx = new AffineTransform(); 
        tx.scale(factor, factor);
        tx.translate(horizontal, vertical);
        return tx;
    }
    
    // geometry arrays, for example DB import
    public ArrayList<GISPoint> points = new ArrayList<>();
    public ArrayList<GISPolyline> polylines = new ArrayList<>();
    public ArrayList<GISPolygon> polygons = new ArrayList<>();
    
    public void updateContent(Content c) {
        points = c.pointlist; 
        polylines = c.polylinelist;         
        polygons = c.polygonlist; 
    }
            
    
    // paintComponent for painting elements
    @Override
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.clearRect(0, 0, getWidth(), getHeight());
           
        AffineTransform tx = getCurrentTransform();
        g2.setTransform(tx);
                
        //paint imported/allready painted Geometries:
        Color color = new Color(1, 0, 0, 0.75f); //Red 
        g2.setPaint(color);
        
        points.forEach((point) -> {
            Ellipse2D geom = point.getGeometry();
            g2.fill(geom);
        });            

        polylines.forEach((polyline) -> {
            Path2D geom = polyline.getGeometry();
            g2.draw(geom);
        });
        polygons.forEach((polygon) -> {
            Path2D geom = polygon.getGeometry();
            g2.fill(geom);
        });
    }
}

