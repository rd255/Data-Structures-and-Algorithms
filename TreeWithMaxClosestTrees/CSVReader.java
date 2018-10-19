import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap; 


public class CSVReader {

    public static void main(String[] args) {

        String csvFilex = "xsorted.csv";
        String csvFiley = "ysorted.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        int i = -1;
        double[][] xsorted = new double[683789][2];
        double[][] ysorted = new double[683789][2];
        try {

            br = new BufferedReader(new FileReader(csvFilex));
            line=br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] treedata = line.split(csvSplitBy);
                i++;
                xsorted[i][0] = Double.parseDouble(treedata[0]);
                xsorted[i][1] = Double.parseDouble(treedata[1]);
            }
            i=-1;
            br = new BufferedReader(new FileReader(csvFiley));
            line=br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] treedata = line.split(csvSplitBy);
                i++;
                ysorted[i][0] = Double.parseDouble(treedata[0]);
                ysorted[i][1] = Double.parseDouble(treedata[2]);
            }
            System.out.println("CSV loaded successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        printTreeWithMaximumTreeNearby(xsorted, ysorted);
    }
    
    
    public static void printTreeWithMaximumTreeNearby(double[][] x, double[][] y) {
      double[] xcost = new double[683789];
      double[] ycost = new double[683789];
      xcost[0] = 0;
      ycost[0] = 0;
      // compute cost in forward direction
      for(int i =1 ; i<683789; i++) {
        xcost[i] = xcost[i-1] + (i * (x[i][1] - x[i-1][1]));
        ycost[i] = ycost[i-1] + (i * (y[i][1] - y[i-1][1]));
      }
      // compute cost in reverse direction
      double xcostrev = 0;
      double ycostrev = 0;
      for(int i=683787; i>=0 ; i--) {
        int irev = 683788 - i;
        xcostrev += irev * (x[i+1][1] - x[i][1]);
        xcost[i] += xcostrev;
        ycostrev += irev * (y[i+1][1] - y[i][1]);
        ycost[i] += ycostrev;
      }
      // add x and y costs, find min cost
      double minindex = DOUBLE.MAX_VALUE;
      double mincost = DOUBLE.MAX_VALUE;
      HashMap<Double,Double> totalcost = new HashMap<>();
      for(int i=0; i<683789; i++ ) 
        totalcost.put(x[i][0], x[i][1]);
      for(int i=0; i<683789; i++ ) {
        double currentcost = totalcost.get(y[i][0]) + y[i][1];
        totalcost.put(y[i][0], currentcost);
        if(currentcost < mincost) {
          minindex = y[i][0];
          mincost = currentcost;
        }
      }
      // print result here
      System.out.println("required tree_id : " + (int)minindex);
    }
}
