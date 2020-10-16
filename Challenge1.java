import java.io.*;
import java.util.*;
class polygon
{ 

	//Infinite 
	static int INF = 10000; 

	static class Point 
	{ 
		int x; 
		int y; 

		public Point(int x, int y) 
		{ 
			this.x = x; 
			this.y = y; 
		} 
	}; 
	
	/*Function to find minimum of x and y*/
    static int min(int x, int y) 
    { 
    return y ^ ((x ^ y) & -(x << y)); 
    } 
      
    /*Function to find maximum of x and y*/
    static int max(int x, int y) 
    { 
    return x ^ ((x ^ y) & -(x << y));  
    }
    


	static boolean onSegment(Point p, Point q, Point r) 
	{ 
		if (q.x <= max(p.x, r.x) && 
			q.x >= min(p.x, r.x) && 
			q.y <= max(p.y, r.y) && 
			q.y >= min(p.y, r.y)) 
		{ 
			return true; 
		} 
		return false; 
	} 

	
	static int orientation(Point p, Point q, Point r) 
	{ 
		int val = (q.y - p.y) * (r.x - q.x) 
				- (q.x - p.x) * (r.y - q.y); 

		if (val == 0) 
		{ 
			return 0; // colinear 
		} 
		return (val > 0) ? 1 : 2; // clock or counterclock wise 
	} 

	static boolean doIntersect(Point p1, Point q1, 
							Point p2, Point q2) 
	{ 
		// Find the four orientations needed for 
		// general and special cases 
		int o1 = orientation(p1, q1, p2); 
		int o2 = orientation(p1, q1, q2); 
		int o3 = orientation(p2, q2, p1); 
		int o4 = orientation(p2, q2, q1); 

		// General case 
		if (o1 != o2 && o3 != o4) 
		{ 
			return true; 
		} 

		
		if (o1 == 0 && onSegment(p1, p2, q1)) 
		{ 
			return true; 
		} 

		
		if (o2 == 0 && onSegment(p1, q2, q1)) 
		{ 
			return true; 
		} 

		
		if (o3 == 0 && onSegment(p2, p1, q2)) 
		{ 
			return true; 
		} 

		
		if (o4 == 0 && onSegment(p2, q1, q2)) 
		{ 
			return true; 
		} 

		
		return false; 
	} 

	// Returns true if the point p lies 
	// inside the polygon[] with n vertices 
	static boolean isInside(Point polygon[], int n, Point p) 
	{ 
		// There must be at least 3 vertices in polygon[] 
		if (n < 3) 
		{ 
			return false; 
		} 

		// Create a point for line segment from p to infinite 
		Point extreme = new Point(INF, p.y); 

		// Count intersections of the above line 
		// with sides of polygon 
		int count = 0, i = 0; 
		do
		{ 
			int next = (i + 1) % n; 

			// Check if the line segment from 'p' to 
			// 'extreme' intersects with the line 
			// segment from 'polygon[i]' to 'polygon[next]' 
			if (doIntersect(polygon[i], polygon[next], p, extreme)) 
			{ 
				// If the point 'p' is colinear with line 
				// segment 'i-next', then check if it lies 
				// on segment. If it lies, return true, otherwise false 
				if (orientation(polygon[i], p, polygon[next]) == 0) 
				{ 
					return onSegment(polygon[i], p, 
									polygon[next]); 
				} 

				count++; 
			} 
			i = next; 
		} while (i != 0); 

		// Return true if count is odd, false otherwise 
		return (count % 2 == 1); // Same as (count%2 == 1) 
	} 

	// Driver Code 
	public static void main(String[] args) 
	{ 
		Point polygon1[] = {new Point(1, 0), 
							new Point(8,3), 
							new Point(8,8), 
							new Point(1,5)}; 
		int n = polygon1.length; 
		Point p = new Point(3,5); 
		if (isInside(polygon1, n, p)) 
		{ 
			System.out.println("inside"); 
		} 
		else
		{ 
			System.out.println("outside"); 
		} 
		
	
	} 
	
} 


