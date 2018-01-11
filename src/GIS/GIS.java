package GIS;

import GIS.filehandling.DB_connection;
import GIS.geometry.*;
import GIS.drawing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputAdapter;

public class GIS extends JFrame implements ActionListener  {
    
    // The GIS object

    /** The Main object which is invoked in the main method
     *
     */
    public static GIS gis;

    /** The Content of the GIS which needs to be public for DB storage
     *
     */
    public Content c;
    

    /** The Drawing Panel of the GIS where Geometries are drawn
     *
     */
    public DrawingPanel p;
    
    
    //variable declaration
    
    // Layout
    JButton buttonPlus; 
    JButton buttonMinus;
    JButton AddFile;
    JButton FE;
    JButton PanB; 
    JButton Select; 
    JButton delete;
    JButton DB;
    JButton buttonRight;
    JButton buttonLeft;
    JButton buttonTop;
    JButton buttonBottom;
    JButton createPoint;
    JButton createPolyline;
    JButton createPolygon;
    JLabel x;
    JLabel y;
    JLabel Xv;
    JLabel Yv;
    JToolBar toolV;
    JToolBar toolE;
    JToolBar toolDb;
    
    
    public static DB_connection DBconnect;

    // variables for zooming and paning
    double factor = 1;
    double horizontal = 0;
    double vertical = 0;

    // mouse coordinates
    double mouseX;
    double mouseY;

    // old and new mouse coordinates (for paning and selection rectangle drawing)
    double oldMouseX;
    double oldMouseY;
    double newMouseX;
    double newMouseY;

    // drawing
    String ActualState = "Pan";
    boolean drawPolygonStarted = false;
    boolean drawPolylineStarted = false;
    boolean drawSelectionRectangleStarted = false;
    int numberOfPoints = 0;
    GISPoint Point;
    GISPolyline Polyline;
    GISPolygon Polygon;
    
    // --------------------------------------------------
    // Constructor which initiates GIS functionality
    // --------------------------------------------------
    public GIS(){
        super("GIS"); //heading

        // create new Content
        c = new Content();
        
        // create new Drawing Panel
        p = new DrawingPanel();
        p.setBackground(Color.GRAY);
      
        // set the zoom and x,y offset (default 1,0,0)
        p.setPan(factor, horizontal, vertical);

        // when starting the programm Pan mode is active -> HAND_CURSOR
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
        // !!!! get and transform coordinates!!!
        // MouseMotion to get the actual x,y-coordinates of mouse
        p.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me){
                // !!!! GET AND DISPLAY MOUSE COORIDATES WITH THE CORRECT TRANSFORMATION!!!
                mouseX = (me.getX());
                mouseY = (me.getY());
		// create a 2D Point object from the coordinates
                Point2D.Double point = new Point2D.Double(mouseX, mouseY); 
		// get the current Transformation of the DrawingPanel
                AffineTransform tx = p.getCurrentTransform();		   
                try {
                    // backtransform them to display the correct X and Y
                    mouseX = tx.inverseTransform(point, null).getX();	   
                    mouseY = tx.inverseTransform(point, null).getY();
                } catch (NoninvertibleTransformException ex) {
                    Logger.getLogger(GIS.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // display x,y in the JLabels x,y
                x.setText(String.valueOf(Math.round(mouseX)));
                y.setText(String.valueOf(Math.round(mouseY)));
                
                
                
                // !!!! LIVE DISPLAY OF POLYGON, POLYLINE OR RECTANGLE IF DRAW HAS BEEN STARTED!!!
                Point = new GISPoint();
                Point.createPoint(mouseX, mouseY);
                
                if (drawPolylineStarted == true){
                    // remove last live point of polyline
                    if (Polyline.pointlist.size() >= 2){
                        Polyline.pointlist.remove(Polyline.pointlist.size()-1);                        
                    }
                    // and add actual live point
                    Polyline.addPoint(Point);
                    // refresh the draw
                    c.refreshPolyline(Polyline);
                    p.updateContent(c);
                    p.repaint();
                }
                if (drawPolygonStarted == true){
                    // remove last live point of polyline, do not remove very first point
                    if (Polygon.pointlist.size() >= 2){
                        Polygon.pointlist.remove(Polygon.pointlist.size()-1);                        
                    }
                    // and add actual live point
                    Polygon.addPoint(Point);
                    // refresh the draw
                    c.refreshPolygon(Polygon);
                    p.updateContent(c);
                    p.repaint();
                }
                if(drawSelectionRectangleStarted == true){
                    newMouseX = Point.getX();
                    newMouseY = Point.getY();

                    // width and height for rectangle drawing
                    double width = Math.abs(oldMouseX-newMouseX);
                    double height = Math.abs(oldMouseY-newMouseY);
                    double leftpoint;
                    // the smaller X coordinate will be the X starting point of the rectangle
                    if (newMouseX > oldMouseX){
                        leftpoint = oldMouseX;
                    }
                    else{
                        leftpoint = newMouseX;
                    }
                    double bottompoint;
                    // the smaller Y coordinate will be the Y starting point of the rectangle
                    if (newMouseY > oldMouseY){
                        bottompoint = oldMouseY;
                    }
                    else{
                        bottompoint = newMouseY;
                    }

                    // create a rectangle with the size defined above
                    Rectangle2D selectR;
                    selectR = new Rectangle2D.Double();
                    selectR.setRect(leftpoint, bottompoint, width, height);

                    // and paint it
                    p.setSelectionRectangle(selectR);
                    p.repaint();
                }
            }
        });

        // !!! Geometry drawing !!!
            // MouseAdapter in the DrawingPanel to draw Point, Line or Polygon
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                // create point at the actual position
                Point = new GISPoint();
                Point.createPoint(mouseX, mouseY);
                
                switch (ActualState) {
                    
                    // if drawPoint mode in active
                    case "drawPoint":
                        // add GISPoint to content and repaint
                        c.addPoint(Point);
                        p.updateContent(c);
                        p.repaint();
                        System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                String.valueOf(Math.round(mouseX)));
                        break; 

                    // if drawPolyline mode in active
                    case "drawPolyline":
                        // if no Polyline draw has started yet
                        if (drawPolylineStarted == false){
                            // create new GISPolyline and add first point
                            Polyline = new GISPolyline();
                            Polyline.addPoint(Point);
                            // add GISPolyline to content
                            c.addPolyline(Polyline);
                            p.updateContent(c);
                            p.repaint();
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseY)));
                            drawPolylineStarted = true;
                            break;
                        }
                        // finish plot when doubleclick (ClickCount()==2)
                        if (e.getClickCount() == 2 && drawPolylineStarted == true) {
                            System.out.println("Doubleclick");
                            // add the last point and refresh the polyline in the content
                            Polyline.addPoint(Point);
                            c.refreshPolyline(Polyline);
                            p.updateContent(c);
                            p.repaint();
                            // indicate that Polygon draw is finished
                            drawPolylineStarted = false;
                        }
                        // adding new points to Polyline
                        else{
                            // add the next point and refresh the polyline in the content
                            Polyline.addPoint(Point);
                            c.refreshPolyline(Polyline);
                            p.updateContent(c);
                            p.repaint();
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseY)));
                        }                        
                        break; 
                        
                    case "drawPolygon":
                        // if no Polygon draw has started yet
                        if (drawPolygonStarted == false){
                            // create new GISPoint and add first point
                            Polygon = new GISPolygon();
                            Polygon.addPoint(Point);
                            // add GISPolygon to content
                            c.addPolygon(Polygon);
                            p.updateContent(c);
                            p.repaint();
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseY)));
                            drawPolygonStarted = true;
                            break;
                        }
                        // finish plot when doubleclick (ClickCount()==2)
                        if (e.getClickCount() == 2 && drawPolygonStarted == true) {
                            System.out.println("Doubleclick");
                            // add the last point and refresh the polyline in the content
                            Polygon.addPoint(Point);
                            c.refreshPolygon(Polygon);
                            p.updateContent(c);
                            p.repaint();
                            // indicate that Polygon draw is finished
                            drawPolygonStarted = false;
                        }
                        // adding new points to Polygon
                        else{
                            // add the next point and refresh the polyline in the content
                            Polygon.addPoint(Point);
                            c.refreshPolygon(Polygon);
                            p.updateContent(c);
                            p.repaint();
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseY)));
                        }                        
                        break;  
                        
                    case "selectFeatures":
                        // if no Rectangle draw has started yet
                        if (drawSelectionRectangleStarted == false){
                            // remove eventual selection rectangle and selected geometries
                            p.setSelectionRectangle(null);
                            c.SelectedPointlist = new ArrayList<>();
                            c.SelectedPolylinelist = new ArrayList<>();
                            c.SelectedPolygonlist = new ArrayList<>();
                            p.updateContent(c);
                            p.repaint();
                            
                            // save first click coordinates
                            oldMouseX = Point.getX();
                            oldMouseY = Point.getY();
                            drawSelectionRectangleStarted = true;
                            break;
                        }

                        else{
                            // get second click coordinates
                            newMouseX = Point.getX();
                            newMouseY = Point.getY();
                            
                            // width and height for rectangle drawing
                            double width = Math.abs(oldMouseX-newMouseX);
                            double height = Math.abs(oldMouseY-newMouseY);
                            
                            double leftpoint;
                            // the smaller X coordinate will be the X starting point of the rectangle
                            if (newMouseX > oldMouseX){
                                leftpoint = oldMouseX;
                            }
                            else{
                                leftpoint = newMouseX;
                            }
                            
                            double bottompoint;
                            // the smaller Y coordinate will be the Y starting point of the rectangle
                            if (newMouseY > oldMouseY){
                                bottompoint = oldMouseY;
                            }
                            else{
                                bottompoint = newMouseY;
                            }
                            
                            // create a rectangle with the size defined above
                            Rectangle2D selectR;
                            selectR = new Rectangle2D.Double();
                            selectR.setRect(leftpoint, bottompoint, width, height);
                            
                            // and paint it
                            p.setSelectionRectangle(selectR);
                            p.repaint();
                            
                            // select and recolor geometries contained/intersected by the rectangle
                            c.selectGeometries(selectR);
                            p.updateContent(c);
                            p.repaint();
                            
                            drawSelectionRectangleStarted = false;
                        }                        
                        break;
                    
                    
                    // do nothing if the state is none of those 3
                    default:
                        break;
                }
            }     
        });
            
        // !!!! PAN !!!!
        // MouseInput to pan
        p.addMouseListener(new MouseInputAdapter() { 
            @Override
            public void mousePressed(MouseEvent e) {
                if (ActualState.equals("Pan")) {
                    System.out.println("pressed");
                    oldMouseX = e.getX();
                    oldMouseY = e.getY();
                }
                // -> will be processed in mouseReleased listener
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                if (ActualState.equals("Pan")){
                    System.out.println("released");
                    newMouseX = e.getX();
                    newMouseY = e.getY();

                    // Pan to the position where mouse is released
                    horizontal = horizontal + newMouseX - oldMouseX;
                    vertical = vertical + newMouseY - oldMouseY;
                    p.setPan(factor, horizontal, vertical);
                    p.repaint();
                }
            }
        });
        
        // !!!! Zoom !!!!
        // MouseWheel to zoom in and out
        p.addMouseWheelListener((MouseWheelEvent e) -> {
            if (ActualState.equals("Pan")) {
                if (e.getWheelRotation() < 0) {
                    System.out.println("mouse wheel Up");
                    // zoom in when wheel up
                    factor += 0.01;
                    p.setPan(factor, horizontal, vertical);
                    p.repaint();
                    
                } else {
                    System.out.println("mouse wheel Down");
                    // zoom out when wheel down
                    factor -= 0.01;
                    p.setPan(factor, horizontal, vertical);
                    p.repaint();
                }
            }
        });
    }   
    // -------------------
    // End of Constructor 
    // -------------------   
    
    // --------------------
    // DB Connection Window
    // -------------------- 
    public void createDBconnectionWindow(){
        DBconnect = new DB_connection();
        DBconnect.setTitle("Connect to DB");
        // in center of screen
        DBconnect.setLocationRelativeTo(null);
        // just set unvisible when clicked on exit (DISPOSE)
        DBconnect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DBconnect.setVisible(true);
    }
    
    // ---------------------------------
    // Set Content after DB, CSV import
    // ---------------------------------
    public void setContent(Content cDB) {
        this.c = cDB;
        p.setPan(factor, horizontal, vertical);
        p.updateContent(c);
        p.repaint();
    }
    //Image Icon 
      
    // -----------------
    // Layout definition 
    // -----------------
    public void setLayout(){   
        //icon of button image
        URL ImgZoomI = GIS.class.getClassLoader().getResource("seicon/zoomin.png");
        ImageIcon zoomI = new ImageIcon(ImgZoomI);
        URL ImgZoomO = GIS.class.getClassLoader().getResource("seicon/zoomout.png");
        ImageIcon zoomO = new ImageIcon(ImgZoomO);
        URL ImgPenPo = GIS.class.getClassLoader().getResource("seicon/point.png");
        ImageIcon PenPo = new ImageIcon(ImgPenPo);
        URL ImgPenPl = GIS.class.getClassLoader().getResource("seicon/line.png");
        ImageIcon PenPl = new ImageIcon(ImgPenPl);
        URL ImgPenPy = GIS.class.getClassLoader().getResource("seicon/polygon.png");
        ImageIcon PenPy = new ImageIcon(ImgPenPy);
        URL ImgFuEt = GIS.class.getClassLoader().getResource("seicon/fullext.png");
        ImageIcon FuEt = new ImageIcon(ImgFuEt);
        URL ImgPan = GIS.class.getClassLoader().getResource("seicon/pan.jpg");
        ImageIcon Pan = new ImageIcon(ImgPan);
        URL ImgOpenF = GIS.class.getClassLoader().getResource("seicon/select.png");
        ImageIcon OpenF = new ImageIcon(ImgOpenF);
        URL ImgDBI = GIS.class.getClassLoader().getResource("seicon/dbc.png");
        ImageIcon DBI = new ImageIcon(ImgDBI);
        URL ImgDel = GIS.class.getClassLoader().getResource("seicon/delet.png");
        ImageIcon Del = new ImageIcon(ImgDel);

        // Layout declaration
        delete = new JButton("Delete", Del);
        delete.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        delete.setBackground (Color.white);
        buttonMinus = new JButton("Zoom Out",zoomO);
        buttonMinus.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        buttonMinus.setBackground(Color.white);
        buttonPlus = new JButton("Zoom In",zoomI);
        buttonPlus.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        buttonPlus.setBackground(Color.white);
        createPoint = new JButton("Point",PenPo);
        createPoint.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        createPoint.setBackground(Color.white);
        createPolyline = new JButton("Polyline",PenPl);
        createPolyline.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        createPolyline.setBackground(Color.white);
        createPolygon = new JButton("Polygon", PenPy);
        createPolygon.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        createPolygon.setBackground(Color.white);
        FE = new JButton("Full Extent", FuEt);
        FE.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        FE.setBackground(Color.white);
        PanB = new JButton("Pan",Pan);
        PanB.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        PanB.setBackground(Color.white);
        Select = new JButton("Select", OpenF);
        Select.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        Select.setBackground(Color.white);
        DB = new JButton("File Handler", DBI);
        DB.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        DB.setBackground(Color.white);
        x = new JLabel();
        y = new JLabel();
        Xv = new JLabel("X: ");
        Yv = new JLabel("Y: ");
        toolV = new JToolBar("Navigation");
        toolE = new JToolBar("Edit");
        toolDb = new JToolBar("Data and File Handler");

        // adding ActionListener
        buttonMinus.addActionListener(this);
        buttonPlus.addActionListener(this);
        createPoint.addActionListener(this);
        createPolyline.addActionListener(this);
        createPolygon.addActionListener(this);
        FE.addActionListener(this);
        PanB.addActionListener(this);
        Select.addActionListener(this);
        DB.addActionListener(this);
        delete.addActionListener(this);

        // define Layout

        toolE.add(createPoint);
        toolE.add(createPolyline);
        toolE.add(createPolygon);
        toolV.add(buttonPlus);
        toolV.add(buttonMinus); 
        toolV.add(FE);
        toolV.add(PanB);
        toolDb.add(Select);
        toolDb.add(delete);
        toolDb.add(DB);
        toolV.setFloatable(true);
        toolE.setFloatable(true);
        toolDb.setFloatable(true);

        //box in four side
        Box box = Box.createHorizontalBox();
        Box boxT = Box.createHorizontalBox();       
        //box contain button and others
        boxT.add(Box.createHorizontalStrut(0));
        boxT.add(toolV);
        boxT.add(toolE);
        boxT.add(toolDb);
        box.add(Xv);
        box.add(x);
        box.add(Box.createHorizontalStrut(10));
        box.add(Yv);
        box.add(y);
        add(box, BorderLayout.SOUTH);
        add(boxT, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);


        setSize(1200,650);
        setVisible(true);
    }
    // ------------------------
    // End of Layout definition 
    // ------------------------
    
    //--------------------------------
    // Action handling for all buttons
    //--------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        // zoom in (coodinated will be multiplied with the factor)
        if(e.getSource() == buttonPlus) {
            factor += 0.1;
        }
        // full extent
        else if(e.getSource() == FE) {
            factor = 1;
            horizontal = 0;
            vertical = 0;
        }
        //pan button 
        else if (e.getSource() == PanB){
            p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            PanB.setBackground(Color.GRAY);
            createPolygon.setBackground(Color.WHITE); //default color
            createPolyline.setBackground(Color.WHITE); //default color
            createPoint.setBackground(Color.WHITE); //default color
            ActualState = "Pan";
        }
        // zoom out (coodinated will be multiplied with the factor)
        else if(e.getSource() == buttonMinus) {
            factor -= 0.1;
        }
        // move rigth (x coordinates will be added with the factor)
        else if(e.getSource() == buttonRight) {
            horizontal += 10;
        }
        // move left (x coordinates will be added with the factor)
        else if(e.getSource() == buttonLeft) {
            horizontal -= 10;
        }
        // move up (y coordinates will be added with the factor)
        else if(e.getSource() == buttonTop) {
            vertical -= 10;
        }
        // move down (y coordinates will be added with the factor)
        else if(e.getSource() == buttonBottom) {
            vertical += 10;
        }
        else if(e.getSource() == DB) {
            createDBconnectionWindow();
        }
        else if(e.getSource() == createPoint) {
            if (!ActualState.equals("drawPoint")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPoint.setBackground(Color.GRAY);
                createPolyline.setBackground(Color.WHITE); //default color
                createPolygon.setBackground(Color.WHITE); //default color
                Select.setBackground(Color.WHITE); //default color
                PanB.setBackground(Color.WHITE); //default color
                // remove eventual selection rectangle and selected geometries
                p.setSelectionRectangle(null);
                c.SelectedPointlist = new ArrayList<>();
                c.SelectedPolylinelist = new ArrayList<>();
                c.SelectedPolygonlist = new ArrayList<>();
                p.updateContent(c);
                p.repaint();
                ActualState = "drawPoint";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPoint.setBackground(Color.WHITE); //default color
                ActualState = "Pan";
                PanB.setBackground(Color.GRAY);
            }
        }
        else if(e.getSource() == createPolyline) {
            if (!ActualState.equals("drawPolyline")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolyline.setBackground(Color.GRAY);
                createPoint.setBackground(Color.WHITE); //default color
                createPolygon.setBackground(Color.WHITE); //default color
                Select.setBackground(Color.WHITE); //default color
                PanB.setBackground(Color.WHITE); //default color
                // remove eventual selection rectangle and selected geometries
                p.setSelectionRectangle(null);
                c.SelectedPointlist = new ArrayList<>();
                c.SelectedPolylinelist = new ArrayList<>();
                c.SelectedPolygonlist = new ArrayList<>();
                p.updateContent(c);
                p.repaint();
                ActualState = "drawPolyline";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolyline.setBackground(Color.WHITE); //default color
                ActualState = "Pan";
                PanB.setBackground(Color.GRAY);
            }
        }
        else if(e.getSource() == createPolygon) {
            if (!ActualState.equals("drawPolygon")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolygon.setBackground(Color.GRAY);
                createPolyline.setBackground(Color.WHITE); //default color
                createPoint.setBackground(Color.WHITE); //default color
                PanB.setBackground(Color.WHITE); //default color
                Select.setBackground(Color.WHITE); //default color
                // remove eventual selection rectangle and selected geometries
                p.setSelectionRectangle(null);
                c.SelectedPointlist = new ArrayList<>();
                c.SelectedPolylinelist = new ArrayList<>();
                c.SelectedPolygonlist = new ArrayList<>();
                p.updateContent(c);
                p.repaint();
                ActualState = "drawPolygon";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolygon.setBackground(Color.WHITE); //default color
                ActualState = "Pan";
                PanB.setBackground(Color.GRAY);
            }
        }
        else if(e.getSource() == Select) {
            if (!ActualState.equals("selectFeatures")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Select.setBackground(Color.GRAY);
                createPolygon.setBackground(Color.WHITE); //default color
                createPolyline.setBackground(Color.WHITE); //default color
                createPoint.setBackground(Color.WHITE); //default color
                PanB.setBackground(Color.WHITE); //default color
                ActualState = "selectFeatures";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Select.setBackground(Color.WHITE); //default color
                ActualState = "Pan";
                // remove eventual selection rectangle and selected geometries
                p.setSelectionRectangle(null);
                c.SelectedPointlist = new ArrayList<>();
                c.SelectedPolylinelist = new ArrayList<>();
                c.SelectedPolygonlist = new ArrayList<>();
                p.updateContent(c);
                p.repaint();
                PanB.setBackground(Color.GRAY);
            }
        }
        else if(e.getSource() == delete) {
            c.deleteSelectedGeometries();
            // to remove the selection geometries
            c.SelectedPointlist = new ArrayList<>();
            c.SelectedPolylinelist = new ArrayList<>();
            c.SelectedPolygonlist = new ArrayList<>();
            p.updateContent(c);
            p.repaint();
        }

        // maximum zoom out: 3, maximum zoom in: 0.1
        // bottom will be disabled when max reached 
        // (works just for the bottons, not for scrolling)
        buttonMinus.setEnabled(factor > 0.1);
        buttonPlus.setEnabled(factor < 3);
        p.setPan(factor, horizontal, vertical);
        p.repaint();
    }

    public static void main(String[] args){
        gis = new GIS(); 
        gis.setLayout();
        gis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        gis.setTitle("G.I. J Gmbh");
        
        
//        // just some method testing:
//        
//        // GISPOINT
//        // create Point object and set coordinates
//        GISPoint p1 = new GISPoint();
//        double x = (double) 1.5;
//        double y = (double) 3;
//        p1.createPoint(x, y);
//        
//        // get the attributes
//        System.out.println(p1.getX());
//        System.out.println(p1.getY());
//        System.out.println(p1.getType());
//        System.out.println(p1.getID());
//        
//        // check writeGeometryAsText and geometryAsTextToPoint
//        System.out.println(p1.getGeometryAsText());
//        String geom = p1.getGeometryAsText();
//        GISPoint p2 = new GISPoint();
//        p2.setGeometryFromText(geom);
//        System.out.println(p2.getX());
//        
//        // GISPOLYLINE
//        // create another Point
//        GISPoint p3 = new GISPoint();
//        double x2 = (double) 4.76;
//        double y2 = (double) 1.23;
//        p3.createPoint(x2, y2);
//        
//        // create Polyline and check the attributes
//        GISPolyline polyl = new GISPolyline();
//        System.out.println(polyl.getType());       
//        
//        // add Points to Polyline
//        polyl.addPoint(p1);
//        polyl.addPoint(p3);
//        
//        // check writeGeometryAsText and geometryAsTextToPoint
//        String geom2 = polyl.getGeometryAsText();
//        System.out.println(geom2);
//        GISPolyline polyl2 = new GISPolyline();
//        polyl2.setGeometryFromText(geom2);
//        System.out.println(polyl2.getGeometryAsText());
//        
//        // GISPOLYGON
//        GISPolygon polyg = new GISPolygon();
//        System.out.println(polyg.getType());
//        polyg.addPoint(p1);
//        polyg.addPoint(p3);
//        polyg.closePolygon();
//        String geom3 = polyg.getGeometryAsText();
//        System.out.println(polyg.getGeometryAsText());
//
//        GISPolygon polyg2 = new GISPolygon();
//        polyg2.setGeometryFromText(geom3);
//        System.out.println(polyg2.getGeometryAsText());
//        System.out.println(polyg2.NoPoints);
    }
}