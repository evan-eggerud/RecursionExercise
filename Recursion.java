//Name: Evan Eggerud & Ashling Cruz
//Class: CS 145
//Source: Deitel, Deitel
//Purpose: This program shows the 4 different exercises provided on the canvas page. It allows the user to select which
//exercise they want to view, 1, 2, or either of the 3rd options. They are then asked to input an integer corresponding to
//what is needed for the particular exercise. It will then print the output for each exercise as directed. The user can do this
//until they enter 5.
import java.util.Scanner;
public class Recursion{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
        menu();
        choice = scanner.nextInt();
        if (choice > 0 && choice <=5){
        switch(choice){
        case 1:
        //exercise 1 section:
        Recursion recursion1 = new Recursion();
        System.out.println("Enter an integer: ");
        int exc1num1 = scanner.nextInt();
        System.out.println("Enter a second integer: ");
        int exc1num2 = scanner.nextInt();
        int result = recursion1.mystery(exc1num1, exc1num2);
        System.out.println("Exercise 1 result: ");
        System.out.println(result);
        break;
        case 2:
        //exercise 2:
        System.out.println("Please enter an integer:");
        int exc2 = scanner.nextInt();
        Recursion recursion2 = new Recursion(); 
        int result2 = recursion2.sum(exc2);
        System.out.println("Exercise 2:"); 
        System.out.println(result2);
        break;
        case 3:
        //exercise 3 pascal's triangle:
        System.out.println("Please enter how many rows you want to show (between 1 and 10):");
        int rows = scanner.nextInt(); // Change the number of rows as needed
        System.out.println("Exercise 3a: Pascal's Triangle");
        printPascalTriangle(rows);
        break;
        case 4:
        //exercise 3b: 
        System.out.println("Enter the first numerator: ");
        int numerator1 = scanner.nextInt();
        System.out.println("Enter the first denominator: ");
        int denominator1 = scanner.nextInt();
        System.out.println("Enter the second numerator: ");
        int numerator2 = scanner.nextInt();
        System.out.println("Enter the second denominator: ");
        int denominator2 = scanner.nextInt();

        int lcd = calculateLCM(denominator1, denominator2);
        int newNumerator1 = newNumerator1(numerator1, denominator1, numerator2, denominator2);
        int newNumerator2 = newNumerator2(newNumerator1, denominator1, numerator2, denominator2);
        System.out.println("Lowest common denominator:");
        System.out.println("Fraction 1: " + newNumerator1 + "/" + lcd);
        System.out.println("Fraction 2: " + newNumerator2 + "/" + lcd);
        break;
        }
    }else {
        System.out.println("That is not an eligible choice. Please enter a number between 1 and 5.");
    }
}
    while (choice !=5);
    System.out.println("End exercise.");
    scanner.close();
    }

//exercise 1:
    public int mystery(int a, int b) {
        if (b == 1) {
        return a;
        }
        else {
        return a + mystery(a, b - 1);
           }
        }
/*exercise 1 comment: with a sysout when I print this, it gives the multiplication of a and b unless b = 1
It accomplishes this through recursion because it will take, let's say 5 and 5. It starts with 5 + mystery(5, 4)
which then iterates back to mystery so it becomes 10 + mystery(5, 3) and this continues until you have gotten b=1 which will return a
one last time, so this would equal 25
*/

//exercise 2:
        public int sum(int n) { 
            if (n == 0) {            
                return n;          
            } else {    
                return n + sum(n - 1);   
            }   
        }
//The problem here was that in the else case it would just infinitely recur unless n=0. If you put in 5 for example, it will say return
//n + sum(5) so it would run the method again returning 10 + sum(5) and so on until it crashes the program. So I added -1 and this 
//will run until it reaches 0.


//exercise 3 pascal's triangle:
//This is the main print method which uses the other methods to print out the entire triangle
public static void printPascalTriangle(int numRows) {
    int maxDigits = Integer.toString(calculatePascalValue(numRows - 1, numRows / 2)).length();
    int totalWidth = maxDigits * numRows + (numRows - 1); // Total width of the triangle
    printPascalTriangleRecursive(0, numRows, maxDigits, totalWidth);
}
//This method recursively prints Pascal's Triangle with centered rows 
//ensuring that each row is properly centered within the total width of the triangle
public static void printPascalTriangleRecursive(int row, int numRows, int maxDigits, int totalWidth) {
    if (row >= numRows) {
        return; 
    }
    int leadingSpaces = (totalWidth - maxDigits * (row + 1)) / 2 + (int) Math.ceil((double) (numRows - row - 1) / 2); 
    printLeadingSpaces(leadingSpaces);
    printRow(0, row, maxDigits);
    System.out.println(); 
    printPascalTriangleRecursive(row + 1, numRows, maxDigits, totalWidth); 
}
//This method uses the row, columns, and maxDigits to print out values and will be used in other methods
public static void printRow(int col, int row, int maxDigits) {
    if (col > row) {
        return; 
    }
    int value = calculatePascalValue(row, col);
    System.out.printf("%-" + maxDigits + "d", value);
    System.out.print(" ");
    printRow(col + 1, row, maxDigits); 
}
//this method prints out the leading space in front of the starting value so it will be aligned correctly.
public static void printLeadingSpaces(int numSpaces) {
    if (numSpaces <= 0) {
        return; 
    }
    System.out.print(" ");
    printLeadingSpaces(numSpaces - 1); 
}
//this method uses recursion to determine what values are on each line of the triangle.
public static int calculatePascalValue(int row, int col) {
    if (col == 0 || col == row) {
        return 1;
    } else {
        // Recursive case: Calculate the value using the sum of the two values above
        return calculatePascalValue(row - 1, col - 1) + calculatePascalValue(row - 1, col);
    }
}

//exercise 3b lowest common demoninator:
//These two methods will return the new numerator value for each fraction given the input based on what the lowest
//common denominator is. 
public static int newNumerator1(int numerator1, int denominator1, int numerator2, int denominator2){
    int lcm = calculateLCM(denominator1, denominator2);
    int newNumerator1 = numerator1 * (lcm / denominator1);
    return newNumerator1;
}
public static int newNumerator2(int numerator1, int denominator1, int numerator2, int denominator2){
    int lcm = calculateLCM(denominator1, denominator2);
    int newNumerator2 = numerator2 * (lcm / denominator2);
    return newNumerator2;
}
//This method returns the greatest common denominator to be used in the calculateLCM method.
public static int calculateGCD(int num1, int num2) {
    // Base case: If the second number is 0, return the first number
    if (num2 == 0) {
        return Math.abs(num1);
    }
    // Recursive case: Calculate GCD using Euclid's algorithm
    else {
        return calculateGCD(num2, num1 % num2);
    }
}
//This method uses recursion to calculate the lowest common denominator of the fractions given and returns the value. 
public static int calculateLCM(int num1, int num2) {
    // Calculate the absolute values of the numbers
    num1 = Math.abs(num1);
    num2 = Math.abs(num2);

    // Base case: If one of the numbers is 0, return 0
    if (num1 == 0 || num2 == 0) {
        return 0;
    }
    // Recursive case: Calculate LCM using the formula: LCM(a, b) = |a * b| / GCD(a, b)
    else {
        int product = Math.abs(num1 * num2);
        int gcd = calculateGCD(num1, num2);
        return product / gcd;
    }
}

//menu method
public static void menu(){

    System.out.println("Recursion exercise!");
    System.out.println("Please pick a number between 1 and 5!");
    System.out.println("1. Exercise 1");
    System.out.println("2. Exercise 2");
    System.out.println("3. Exercise 3a, Pascal's Triangle");
    System.out.println("4. Exercise 3b, lowest common denominator");
    System.out.println("5. Exit");
    System.out.println("Enter your choice:");
    }
}
