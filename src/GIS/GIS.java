package GIS;

import GIS.geometry.*;
import GIS.drawing.*;
import GIS.database.*;
import GIS.csv.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.NoninvertibleTransformException;
import java.io.File;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileSystemView;

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
    

    //variable declaration
    DrawingPanel p;

    
    // Layout
    JButton buttonPlus, buttonMinus, AddFile, FE,PanB, fileOp, DB;
    JButton buttonRight;
    JButton buttonLeft;
    JButton buttonTop;
    JButton buttonBottom;
    JButton createPoint;
    JButton createPolyline;
    JButton createPolygon;
    JLabel x;
    JLabel y;
    JToolBar toolB;
    public static DB_connection DBconnect;

    // variables for zooming and paning
    double factor = 1;
    double horizontal = 0;
    double vertical = 0;

    // mouse coordinates
    double mouseX;
    double mouseY;

    // old and new mouse coordinates (for paning)
    double oldMouseX;
    double oldMouseY;
    double newMouseX;
    double newMouseY;

    // drawing
    String ActualState = "Pan";
    boolean drawPolygonStarted = false;
    boolean drawPolylineStarted = false;
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
      
        // set the zoom and x,y offset (default 1,0,0)
        p.setPan(factor, horizontal, vertical);

        // when starting the programm Pan mode is active -> HAND_CURSOR
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
        // !!!! get and transform coordinates!!!
        // MouseMotion to get the actual x,y-coordinates of mouse
        p.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me){
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
                            p.repaint();
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
                System.out.println("pressed");
                oldMouseX = e.getX();
                oldMouseY = e.getY();
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
            if ("Pan".equals(ActualState)) {
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
    
    // -----------------
    // Layout definition 
    // -----------------
    public void setLayout(){   
        // Layout declaration
        buttonMinus = new JButton("Zoom Out");
        buttonPlus = new JButton("Zoom In");
        buttonRight = new JButton("Right");
        buttonLeft = new JButton("Left");
        buttonTop = new JButton("Top");
        buttonBottom = new JButton("Bottom");
        createPoint = new JButton("Create Point");
        createPolyline = new JButton("Create Polyline");
        createPolygon = new JButton("Create Polygon");
        FE = new JButton("FE");
        PanB = new JButton("Pan");
        fileOp = new JButton("Open File");
        DB = new JButton("ConnectDB");
        x = new JLabel();
        y = new JLabel();
        toolB = new JToolBar();

        // adding ActionListener
        buttonMinus.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonRight.addActionListener(this);
        buttonLeft.addActionListener(this);
        buttonTop.addActionListener(this);
        buttonBottom.addActionListener(this);
        createPoint.addActionListener(this);
        createPolyline.addActionListener(this);
        createPolygon.addActionListener(this);
        FE.addActionListener(this);
        PanB.addActionListener(this);
        fileOp.addActionListener(this);
        DB.addActionListener(this);

        // define Layout

        toolB.add(createPoint);
        toolB.add(createPolyline);
        toolB.add(createPolygon);
        toolB.add(buttonPlus);
        toolB.add(buttonMinus); 
        toolB.add(FE);
        toolB.add(PanB);
        toolB.add(fileOp);
        toolB.add(DB);
        toolB.setFloatable(true);

        //box in four side
        Box box = Box.createHorizontalBox();
        Box boxT = Box.createHorizontalBox();
        Box boxE = Box.createVerticalBox();
        Box boxW = Box.createVerticalBox();
        //box contain button and others
        boxE.add(Box.createVerticalStrut(250));
        boxE.add(buttonRight);
        boxW.add(Box.createVerticalStrut(250));
        boxW.add(buttonLeft);
        boxT.add(Box.createHorizontalStrut(500));
        boxT.add(buttonTop);
        boxT.add(toolB);
        box.add(Box.createHorizontalStrut(500));
        box.add(buttonBottom);
        box.add(Box.createHorizontalStrut(290));
        box.add(x);
        box.add(Box.createHorizontalStrut(10));
        box.add(y);
        add(box, BorderLayout.SOUTH);
        add(boxT, BorderLayout.NORTH);
        add(boxE, BorderLayout.EAST);
        add(boxW, BorderLayout.WEST);
        add(p, BorderLayout.CENTER);


        setSize(1180,630);
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
            createPolygon.setBackground(new JButton().getBackground()); //default color
            createPolyline.setBackground(new JButton().getBackground()); //default color
            createPoint.setBackground(new JButton().getBackground()); //default color
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
        else if(e.getSource()==fileOp){
            JFileChooser Jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            Jfc.setDialogTitle("Open CSV or DBMS file");
            Jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int Jfcreturn = Jfc.showOpenDialog(null);
            if (Jfcreturn == JFileChooser.APPROVE_OPTION){
                File[] file = Jfc.getSelectedFiles();   
            }
        }
        else if(e.getSource() == DB) {
            createDBconnectionWindow();
        }
        else if(e.getSource() == createPoint) {
            if (!ActualState.equals("drawPoint")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPoint.setBackground(Color.GRAY);
                createPolyline.setBackground(new JButton().getBackground()); //default color
                createPolygon.setBackground(new JButton().getBackground()); //default color
                PanB.setBackground(new JButton().getBackground()); //default color
                ActualState = "drawPoint";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPoint.setBackground(new JButton().getBackground()); //default color
                ActualState = "Pan";
            }
        }
        else if(e.getSource() == createPolyline) {
            if (!ActualState.equals("drawPolyline")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolyline.setBackground(Color.GRAY);
                createPoint.setBackground(new JButton().getBackground()); //default color
                createPolygon.setBackground(new JButton().getBackground()); //default color
                PanB.setBackground(new JButton().getBackground()); //default color
                ActualState = "drawPolyline";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolyline.setBackground(new JButton().getBackground()); //default color
                ActualState = "Pan";
            }
        }
        else if(e.getSource() == createPolygon) {
            if (!ActualState.equals("drawPolygon")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolygon.setBackground(Color.GRAY);
                createPolyline.setBackground(new JButton().getBackground()); //default color
                createPoint.setBackground(new JButton().getBackground()); //default color
                PanB.setBackground(new JButton().getBackground()); //default color
                ActualState = "drawPolygon";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolygon.setBackground(new JButton().getBackground()); //default color
                ActualState = "Pan";
            }
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