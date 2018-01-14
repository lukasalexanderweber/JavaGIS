
package GIS.filehandling;

import GIS.drawing.Content;
import GIS.geometry.GISPoint;
import GIS.geometry.GISPolygon;
import GIS.geometry.GISPolyline;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CSV {
    String path;
    public CSV (String pathtocsv){
        this.path = pathtocsv;
    }
    
    /**
     * The loadContent object
     * @return it returns an object of type Content, c;
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Content loadContent() throws FileNotFoundException, IOException{
        
        Content c = new Content();
        
        boolean header = true;
        String line = "";
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {


            // loop until all lines are read
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                
                // skip first line
                if (header == true){
                    header = false;
                    continue;
                }
                
                String[] row = line.split(";");

                String type = row[0];
                String geom = row[1];

                switch (type) {

                    // if type is a point 
                    case "Point":
                        // create empty point
                        GISPoint p = new GISPoint();
                        // set Geometry of empty point to geom
                        p.setGeometryFromText(geom);
                        // add point to pointlist
                        c.addPoint(p);
                        break;

                    // if type is a polyline
                    case "Polyline":
                        // create empty polyline
                        GISPolyline polyl = new GISPolyline();
                        // set Geometry of empty polyline to geom
                        polyl.setGeometryFromText(geom);
                        // add point to polylinelist
                        c.addPolyline(polyl);
                        break;

                    // if type is a polyline
                    case "Polygon":
                        // create empty point
                        GISPolygon polyg = new GISPolygon();
                        // set Geometry of empty point to geom
                        polyg.setGeometryFromText(geom);
                        // add point to pointlist
                        c.addPolygon(polyg);
                        break;
                }
            }
        }
        return c;

    }
}
