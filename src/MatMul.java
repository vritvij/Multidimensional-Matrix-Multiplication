import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class MatMul {
    public static void main(String args[]) {
        try {
            Scanner get = new Scanner(System.in);

            System.out.print("Enter Dimensions of Matrix A: ");
            Matrix a = new Matrix(get.nextLine().trim());
            System.out.print("Enter Dimensions of Matrix B: ");
            Matrix b = new Matrix(get.nextLine().trim());

            System.out.println("Matrix A : " + a);
            System.out.println("Matrix B : " + b);

            System.out.println("Which dimensions to multiply? ");
            Matrix c = a.multiply(b, get.nextInt(), get.nextInt());
//            Matrix c = a.multiply(b);
            
            System.out.println("Dimension of resultant Matrix C : " + Arrays.toString(c.getDimensions()));
            System.out.println("Matrix C : " + c);

        } catch (Matrix.MalformedMatrixException ex) {
            System.out.println("ERROR : Invalid Matrix Dimensions!");
        } catch (Matrix.InvalidMultiplicationDimensionException ex) {
            System.out.println("ERROR : Cannot Multiply along these Dimensions!");
        } catch (Matrix.MatrixNotMultipliableException ex) {
            System.out.println("ERROR : The above Matrices cannot be Multiplied!");
        } catch (InputMismatchException ex) {
            System.out.println("ERROR : Invalid Input!");
        }
    }
}