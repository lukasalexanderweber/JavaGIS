/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.database;

/**
 *
 * @author keneuoe
 */
class Shapes {
    private int gid;
    private String name, geom;
    
    public Shapes(int gid, String name, String geom){
        this.gid = gid;
        this.name = name;
        this.geom =geom;
    }
    public int getgid(){
        return gid;
    }
    
    public String getname() {
        return name;
    }
    
    public String getgeom() {
        return name;
    }
}
