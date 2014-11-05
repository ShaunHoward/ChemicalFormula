import java.io.*;

/**
 * Analyzes a string input and determines whether the string is
 * a chemical formula. T is printed if it is a syntactically valid
 * chemical formula. F is printed otherwise.
 *
 * The program has extensive error handling and a validation class for
 * validating whether a string is valid chemical formula, without
 * considering the underlying chemistry.
 *
 * @author Shaun Howard
 */
public class ChemicalFormula {

    /**
     * Reads in the input file containing two strings to compare.
     * The first argument is the file path, the second is the char-set.
     *
     * @param args - the input arguments
     */
    public static void main(String[] args) {
        String formula = "";
        try (FileInputStream fis = new FileInputStream(new File(args[0]));
             BufferedReader br = new BufferedReader(new InputStreamReader(fis, args[1]))) {
            String appendage;

            // Read in the input from std in
            while ((appendage = br.readLine()) != null) {
                formula = formula + "\n" + appendage;
            }

            //Validate the syntactical context of the formula.
            //validateFormula(formula);

        } catch (IOException ioe) {
            System.out.println("I/O Exception occurred.");
        }

    }
}
