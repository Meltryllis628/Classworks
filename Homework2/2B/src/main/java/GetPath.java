import java.util.ArrayList;

public class GetPath {
    //Fill in your answer for this method
    public static boolean getPath (int r, int c, ArrayList<Point> path, final int [][] grid) {
        if(c<0||r<0||grid[r][c]==1){return false;}
        Point current = new Point(r,c);
        if ((r==0&&c==0)|| getPath(r,c-1,path,grid) || getPath(r-1,c,path,grid)) {
            path.add(current);

            return true;
        }
        return false;
    }
}

//You do not need to change any code below ---------
class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x=x;
        this.y=y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
//--------------------------------------------------

/* HINT:
Your solution ought to work with a grid that is not square.
Here is the grid for Test Case 8:

            final int[][] grid = {
                    {0,0,0,1},
                    {0,1,0,0},
                    {0,1,1,1},
                    {0,0,0,1},
                    {1,1,0,0},
                    {1,1,1,0}
            };

If the destination is r = 5, c = 3, then getPath() returns true.
If the destination is r = 2, c = 3, then getPath() returns false.

*/

