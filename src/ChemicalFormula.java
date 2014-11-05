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

            System.out.println(analyzeFormulaPotential(formula));
        } catch (IOException ioe) {
            System.out.println("I/O Exception occurred while reading std in.");
        }

    }

    /**
     * Analyzes the syntactical soundness in terms of chemical formulas
     * for the given formula. Returns a string "T" if the formula
     * can be a chemical formula, otherwise, returns "F".
     *
     * @param formula - the formula to check for syntactical soundness
     * @return whether the formula is syntactically a chemical formula
     */
    private static String analyzeFormulaPotential(String formula) {
        FormulaValidator validator = new FormulaValidator();
        //Validate the syntactical context of the formula.
        boolean potentialFormula = validator.validateFormula(formula);
        String potential = "";

        if (potentialFormula){
            potential = analyzeFormulaSyntax(formula);
        } else {
            potential = "F";
        }

        return potential;
    }

    /**
     * Analyzes the syntax of the input formula string.
     * Returns a string "T" if the input formula is
     * syntactically valid, otherwise returns a string "F".
     *
     * @param formula - the formula to check the syntax of
     */
    private static String analyzeFormulaSyntax(String formula){
        return "";
    }
}
