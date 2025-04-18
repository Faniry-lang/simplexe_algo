package utils;

public class Helper {

    public static int MaxPositive(double[] array) {
        int index = -1;
        double maxVal = Double.NEGATIVE_INFINITY;

        for(int i = 0; i < array.length; i++) {
            if(array[i] > maxVal && array[i] > 0) {
                index = i;
                maxVal = array[i];
            }
        }

        return index;
    }

    public static int Max(double[] array) {
        int index = -1;
        double maxVal = Double.NEGATIVE_INFINITY;

        for(int i = 0; i < array.length; i++) {
            if(array[i] > maxVal) {
                index = i;
                maxVal = array[i];
            }
        }

        return index;
    }

    public static int Min(double[] array) {
        int index = -1;
        double minVal = Double.POSITIVE_INFINITY;

        for(int i = 0; i < array.length; i++) {
            if(array[i] < minVal) {
                index = i;
                minVal = array[i];
            }
        }

        return index;
    }

    public static double[] GetVectorAtCol(int colIndex, double[][] matrix) {
        int rowLength = matrix.length;
        double[] vector = new double[rowLength];

        for(int i = 0; i < matrix.length; i++) {
            vector[i] = matrix[i][colIndex];
        }

        return vector;
    }

    public static int GetSignOfFunction(double[] function) {
        boolean hasPositive = false;
        boolean hasNegative = false;
    
        for (double v : function) {
            if (v > 0) hasPositive = true;
            else if (v < 0) hasNegative = true;
        }
    
        if (hasPositive && hasNegative) return 0;  
        if (hasPositive) return 1;                 
        if (hasNegative) return -1;                
        return 0;                                 
    }

    public static void Scale(double[] row, double sc) throws Exception {
        if(sc == 0) {
            throw new Exception("Scalar can't be null");
        }
        for(int i = 0 ; i < row.length; i++) {
            row[i] *= sc;
        }
    }

    public static void AddScale(double[] row, double[] scaledRow, double sc) throws Exception {
        if(sc == 0) {
            throw new Exception("Scalar can't be null");
        }

        for(int i = 0 ; i < row.length; i++) {
            row[i] += scaledRow[i]*sc;
        }
    }
}
