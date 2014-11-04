/* Author: Brett Cohen
 * net ID: brett7
 * Written: 10-13-14
 * Section: B (Monday 7-8:50pm)
 *
 *
 * This takes a user passed argument for the number of times the
 * program is suppose to recurse. The program draws an intial boundry
 * triangle and then draws and fills in an upside down trianle within
 * the boundry and then recursively draws more upside down triangles 
 * to the left, right, and top of the parent triangle.
 * --------------------------------------------------*/
public class Sierpinski{

// draws a triangle with vertex (x, y), side length s
    public static void filledTriangle(double x, double y, double s){
        int i = 3;
        double [] xpoints = new double[i];
        double [] ypoints = new double[i];

        xpoints[0] = x + (s / 2.0);
        xpoints[1] = x + s;
        xpoints[2] = x + ((3 * s) / 2.0);
        ypoints[0] = y + ((Math.sqrt(3.0) * s) / 2.0);
        ypoints[1] = y;
        ypoints[2] = y + ((Math.sqrt(3.0) * s) / 2.0);

        // draw solid polygon with the given array.
        StdDraw.filledPolygon(xpoints, ypoints);
    }


// draws the triangles recursively
    public static void sierpinski(int recNum, double x, double y, double s){
        if (recNum == 0) return;

        // filledTriangle(x, y, size)
        filledTriangle(x, y, s);

        // recursively draw triangles
        sierpinski(recNum - 1, (x + s), y, (s/2.0));
        sierpinski(recNum - 1, x, y, (s/2.0));
        sierpinski(recNum - 1, x+(s/2.0), y+((Math.sqrt(3)/2.0)*s), s/2.0);
    }


// main program - read in command line argument recNum for number
// of times recursion will happen
    public static void main(String[] args){
        int recNum = Integer.parseInt(args[0]);

        // variables for outter triangle
        double x0 = 0.0;
        double y0 = 0.0;
        double x1 = 1.0;
        double y1 = 0.0;
        double xv = 0.5;
        double yv = (Math.sqrt(3) / 2);

        // draw outter triangle
        StdDraw.line(x0, y0, x1, y1);
        StdDraw.line(x0, y0, xv, yv);
        StdDraw.line(x1, y1, xv, yv);

        // set up varibles for side length as initial accumulating 
        // variables - x and y
        double x = 0.0;
        double y = 0.0;
        double s = 0.5;

        // initiate sierpinski method
        sierpinski(recNum, x, y, s);

    }

}
