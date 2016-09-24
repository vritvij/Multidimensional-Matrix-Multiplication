import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Matrix {

    Object a;
    private final int[] dim;

    private static Object createMultiDimArray(int[] dimensionLengths) throws MalformedMatrixException {
        return createMultiDimArray(dimensionLengths, 0);
    }

    private static Object createMultiDimArray(int[] dimensionLengths, int depth) throws MalformedMatrixException {
        if (dimensionLengths[depth] <= 0) {
            throw new MalformedMatrixException();
        }
        Object[] dimension = new Object[dimensionLengths[depth]];
        if (depth + 1 < dimensionLengths.length) {
            for (int i = 0; i < dimensionLengths[depth]; i++) {
                dimension[i] = createMultiDimArray(dimensionLengths, depth + 1);
            }
        } else {
            Scanner get = new Scanner(System.in);
            for (int i = 0; i < dimensionLengths[depth]; i++) {
                dimension[i] = get.nextInt();
                //dimension[i] = (int)(Math.random()*10);
            }
        }
        return dimension;
    }

    public int get(int[] pos) throws InvalidPositionException {
        int t;
        if (pos.length != this.dim.length) {
            throw new InvalidPositionException();
        }

        Object x[] = (Object[]) this.a;

        for (int i = 0; i < pos.length - 1; i++) {
            x = (Object[]) x[pos[i]];
        }

        t = (int) x[pos[pos.length - 1]];

        return t;
    }

    public int[] getDimensions() {
        return dim;
    }

    private Matrix MatrixMultiply(Matrix A, Matrix B, int x, int y, int[] dimensionLengths) {
        Matrix t = new Matrix(dimensionLengths);
        int[] indices = new int[dimensionLengths.length];
        try {
            t.a = MatrixMultiply(A, B, dimensionLengths, x, y, indices, 0);
        } catch (InvalidPositionException ex) {

        }
        return t;
    }

    private Object[] MatrixMultiply(Matrix A, Matrix B, int[] dimensionLengths, int x, int y, int[] indices, int depth) throws InvalidPositionException { //indices is the current index for the new matrix
        Object[] dimension = new Object[dimensionLengths[depth]];

        if (depth + 1 < dimensionLengths.length) {
            for (int i = 0; i < dimensionLengths[depth]; i++) {
                indices[depth] = i;
                dimension[i] = MatrixMultiply(A, B, dimensionLengths, x, y, indices, depth + 1);
            }
        } else {
            for (int i = 0; i < dimensionLengths[depth]; i++) {
                indices[depth] = i;

                if (dimension[i] == null) {
                    dimension[i] = 0;
                }

                int[] Adim = new int[A.dim.length],
                        Bdim = new int[B.dim.length];

                for (int j = 0; j < A.dim[x - 1]; j++) {
                    //Generate position index for A
                    for (int k = 0; k < indices.length; k++) {
                        if (k != x - 1) {
                            Adim[k] = indices[k];
                        } else {
                            Adim[k] = j;
                        }
                    }
                    //Generate position index for B
                    for (int k = 0; k < indices.length; k++) {
                        if (k != y - 1) {
                            Bdim[k] = indices[k];
                        } else {
                            Bdim[k] = j;
                        }
                    }

                    dimension[i] = (int) dimension[i] + A.get(Adim) * B.get(Bdim);
                }
            }
        }
        return dimension;
    }

    private int[] convert(String x) {
        List t = Arrays.asList(x.split(" "));
        int[] m = new int[t.size()];
        int i = 0;
        for (Object str : t) {
            m[i++] = Integer.parseInt((String) str);
        }
        return m;
    }

    public Matrix(String dim) throws MalformedMatrixException {
        dim = dim.replaceAll("\\s+", " ");

        if (dim.equals("")) {
            throw new MalformedMatrixException();
        }

        this.dim = convert(dim);

        System.out.println("Enter values of " + this.dim.length + "-D matrix from innermost dimension to outermost:");
        a = createMultiDimArray(this.dim);
    }

    private Matrix(int[] dim) {
        this.dim = dim;
    }

    private boolean isMultiplicable(int[] dim1, int[] dim2, int x, int y) throws InvalidMultiplicationDimensionException {
        //The dimensions to be multiplied must be distinct
        if (x == y) {
            throw new InvalidMultiplicationDimensionException();
        }
        //The dimensions to be multiplied must be within dimension limits
        if (x > dim1.length || y > dim2.length || x <= 0 || y <= 0) {
            throw new InvalidMultiplicationDimensionException();
        }
        //The matrices must have same dimensionality
        if (dim1.length != dim2.length){
            return false;
        }
        //The dimensions to be multiplied must have same modulus(value)
        if (dim1[x - 1] != dim2[y - 1]) {
            return false;
        }
        //All dimensions other than the one's to be multiplied must have same modulus(value)
        try {
            for (int i = 0; i < Math.max(dim1.length, dim2.length); i++) {
                if (i != x - 1 && i != y - 1) {
                    if (dim1[i] != dim2[i]) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private int[] resultDimension(int[] dim1, int[] dim2, int x, int y) {
        int[] newDim;

        int t = 0;

        for (int i = 0; i < Math.max(dim1.length, dim2.length); i++) {
            if (i != x - 1 && i != y - 1) {
                if (dim1[i] == dim2[i]) {
                    t++;
                } else {
                    t += 2;
                }
            } else {
                t++;
            }
        }

        newDim = new int[t];

        for (int i = 0, j = 0; i < Math.max(dim1.length, dim2.length); i++) {
            if (i == x - 1) {
                newDim[j++] = dim2[i];
            } else if (i == y - 1) {
                newDim[j++] = dim1[i];
            } else {
                newDim[j++] = dim1[i];
            }
        }

        return newDim;
    }

    public Matrix multiply(Matrix B, int x, int y) throws MatrixNotMultipliableException, InvalidMultiplicationDimensionException {
        Matrix A = this,
                C;
        if (isMultiplicable(A.dim, B.dim, x, y)) {
            //Instantiate Dimension array for Resultant Matrix
            int[] newDim = resultDimension(A.dim, B.dim, x, y);

            //Create Resultant Matrix
            C = MatrixMultiply(A, B, x, y, newDim);

        } else {
            throw new MatrixNotMultipliableException();
        }
        return C;
    }

    public Matrix multiply(Matrix B) throws MatrixNotMultipliableException, InvalidMultiplicationDimensionException {
        return multiply(B, 2, 1);
    }

    @Override
    public String toString() {
        //check to see if matrix is invalid i.e contains null
        return Arrays.deepToString((Object[]) a);
    }

    public static class InvalidPositionException extends Exception {

        public InvalidPositionException() {
            super();
        }
    }

    public static class MatrixNotMultipliableException extends Exception {

        public MatrixNotMultipliableException() {
            super();
        }
    }

    public static class MalformedMatrixException extends Exception {

        public MalformedMatrixException() {
            super();
        }
    }

    public static class InvalidMultiplicationDimensionException extends Exception {

        public InvalidMultiplicationDimensionException() {
            super();
        }
    }
}
