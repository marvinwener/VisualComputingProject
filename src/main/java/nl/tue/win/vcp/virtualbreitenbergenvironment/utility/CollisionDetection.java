package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import java.awt.Point;

/**
 *
 * @author s141788
 */
public class CollisionDetection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Collision detection started");
        Point red1 = new Point(-2,7);
        Point red2 = new Point(2,4);
        Point red3 = new Point(-4,-4);
        Point red4 = new Point(-8,-1);
        Point blue1 = new Point(-1,-3);
        Point blue2 = new Point(3,0);
        Point blue3 = new Point(9,-8);
        Point blue4 = new Point(5,-11);
        
        int x = collision(red1, red2, red3, red4, blue1, blue2, blue3, blue4);
        
        if(x == -1)
            System.out.println("Collision detected...");
        else
            System.out.println("Safe!");
     
        
    }
    
    public static int collision(Point f11, Point f12, Point f13, Point f14, Point f21, Point f22, Point f23, Point f24){
        //variables for each column
        boolean column1 = false;
        boolean column2 = false;
        boolean column3 = false;
        boolean column4 = false;
        
        //vector Figure 1
        double vxf1 = f11.x - f14.x;
        //System.out.println("vector 1x: " + vxf1);
        double vyf1 = f12.y - f13.y;
        //System.out.println("vector 1y: " + vyf1);
        
        //vector Figure 2
        double vxf2 = f21.x - f24.x;
        System.out.println("vector 2x: " + vxf2);
        double vyf2 = f22.y - f23.y;
        System.out.println("vector 2y: " + vyf2);
        System.out.println("");
        //First column
	//dot product Figure 1				//dot product Figure 2
	double col11 = f11.x * vxf1 + f11.y * vyf1;    double col21 = f21.x * vxf1 + f21.y * vyf1;
	double col12 = f12.x * vxf1 + f12.y * vyf1;    double col22 = f22.x * vxf1 + f22.y * vyf1;
	double col13 = f13.x * vxf1 + f13.y * vyf1;    double col23 = f23.x * vxf1 + f23.y * vyf1;    
	double col14 = f14.x * vxf1 + f14.y * vyf1;    double col24 = f24.x * vxf1 + f24.y * vyf1;        

        //check if first column overlaps
        double max1 = Math.max(col11, Math.max(col12, Math.max(col13, col14)));        
        double min1 = Math.min(col11, Math.min(col12, Math.min(col13, col14)));
        double max2 = Math.max(col21, Math.max(col22, Math.max(col23, col24)));
        double min2 = Math.min(col21, Math.min(col22, Math.min(col23, col24)));
        if(max1 > min2 && max1 <= max2){column1 = true;}
        else if (min1 < max2 && min1 >= min2){column1 = true;}
        else if (max1 >= max2 && min1 <= min2){column1 = true;}
        System.out.println("Columna1 : " + column1);
        
         //Second column
	//dot product Figure 1                      //dot product Figure 2
	col11 = -f11.y * vxf1 + f11.x * vyf1;    col21 = -f21.y * vxf1 + f21.x * vyf1;                                               
     
	col12 = -f12.y * vxf1 + f12.x * vyf1;    col22 = -f22.y * vxf1 + f22.x * vyf1;                                               
        
	col13 = -f13.y * vxf1 + f13.x * vyf1;    col23 = -f23.y * vxf1 + f23.x * vyf1;                                               
        
	col14 = -f14.y * vxf1 + f14.x * vyf1;    col24 = -f24.y * vxf1 + f24.x * vyf1;                                               
        
                                                
        
          //check if first column overlaps
        max1 = Math.max(col11, Math.max(col12, Math.max(col13, col14)));        
        min1 = Math.min(col11, Math.min(col12, Math.min(col13, col14)));
        max2 = Math.max(col21, Math.max(col22, Math.max(col23, col24)));
        min2 = Math.min(col21, Math.min(col22, Math.min(col23, col24)));
        if(max1 > min2 && max1 <= max2){column2 = true;}
        else if (min1 < max2 && min1 >= min2){column2 = true;}
        else if (max1 >= max2 && min1 <= min2){column2 = true;}       
        System.out.println("Columna2 : " + column2);
        
          //Third column
	//dot product Figure 1                      //dot product Figure 2
	col11 = f11.x * vxf2 + f11.y * vyf2;    col21 = f21.x * vxf2 + f21.y * vyf2;
        
	col12 = f12.x * vxf2 + f12.y * vyf2;    col22 = f22.x * vxf2 + f22.y * vyf2;
        
	col13 = f13.x * vxf2 + f13.y * vyf2;    col23 = f23.x * vxf2 + f23.y * vyf2;
      
	col14 = f14.x * vxf2 + f14.y * vyf2;    col24 = f24.x * vxf2 + f24.y * vyf2;
        
        
          //check if first column overlaps
        max1 = Math.max(col11, Math.max(col12, Math.max(col13, col14)));
        min1 = Math.min(col11, Math.min(col12, Math.min(col13, col14)));
        max2 = Math.max(col21, Math.max(col22, Math.max(col23, col24)));
        min2 = Math.min(col21, Math.min(col22, Math.min(col23, col24)));
        if(max1 > min2 && max1 <= max2){column3 = true;}
        else if (min1 < max2 && min1 >= min2){column3 = true;}
        else if (max1 >= max2 && min1 <= min2){column3 = true;}
        System.out.println("Columna3 : " + column3);
        
             //Fourth column
	//dot product Figure 1                      //dot product Figure 2
	col11 = -f11.y * vxf2 + f11.x * vyf2;    col21 = -f21.y * vxf2 + f21.x * vyf2;
	col12 = -f12.y * vxf2 + f12.x * vyf2;    col22 = -f22.y * vxf2 + f22.x * vyf2;
	col13 = -f13.y * vxf2 + f13.x * vyf2;    col23 = -f23.y * vxf2 + f23.x * vyf2;
	col14 = -f14.y * vxf2 + f14.x * vyf2;    col24 = -f24.y * vxf2 + f24.x * vyf2;
        
          //check if first column overlaps
        max1 = Math.max(col11, Math.max(col12, Math.max(col13, col14)));
        min1 = Math.min(col11, Math.min(col12, Math.min(col13, col14)));
        max2 = Math.max(col21, Math.max(col22, Math.max(col23, col24)));
        min2 = Math.min(col21, Math.min(col22, Math.min(col23, col24)));
        if(max1 > min2 && max1 <= max2){column4 = true;}
        else if (min1 < max2 && min1 >= min2){column4 = true;}
        else if (max1 >= max2 && min1 <= min2){column4 = true;}
        System.out.println("Columna4 : " + column4);
        
        if(column1 && column2 && column3 && column4)
            return -1;
        else
            return 0;
    }
}