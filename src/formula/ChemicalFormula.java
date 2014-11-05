package formula;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Pattern for identifying syntactically valid chemical formula.
    static Pattern FORMULA_PATTERN =
            Pattern.compile("(([A-Z][a-z]?[2-9]?)*" +
                    "([\\(]([A-Z][a-z]?[2-9]?){2,}[\\)][2-9])+" +
                    "([A-Z][a-z]?[2-9]?)*)" +
                    "|([A-Z][a-z]?[2-9]?)+");

    /**
     * Takes a string from the standard in and determines if it is
     * a syntactically valid chemical formula.
     * Prints "T" to standard out if it is valid, prints "F" otherwise.
     *
     * @param args - the input arguments
     */
    public static void main(String[] args) {
        String formula = "";

        try (FileInputStream fis = new FileInputStream(new File(args[0]));
             BufferedReader br = new BufferedReader(new InputStreamReader(fis, args[1]))) {
            String appendage;

            // Read the input from std in
            while ((appendage = br.readLine()) != null) {
                formula = formula + "\n" + appendage;
            }

            //Prints whether the chemical formula is syntactically valid.
            System.out.println(analyzeFormulaCompleteness(formula));
        } catch (IOException ioe) {
            System.out.println("I/O Exception occurred while reading std in.");
        }
    }

    /**
     * Analyzes the syntactical completeness in terms of chemical formulas
     * for the given formula. Returns a string "T" if the formula
     * can be a chemical formula, otherwise, returns "F".
     *
     * @param formula - the formula to check for syntactical completeness
     * @return whether the formula is a syntactically valid chemical formula
     */
    private static String analyzeFormulaCompleteness(String formula) {
        FormulaValidator validator = new FormulaValidator();
        //Validate the syntactical soundness of the formula.
        boolean potentialFormula = validator.validateFormula(formula);
        String potential = "";

        if (potentialFormula){
            potential = analyzeFormulaSoundness(formula);
        } else {
            potential = "F";
        }

        return potential;
    }

    /**
     * Analyzes the syntax soundness of the input formula string
     * with a formula regular expression.
     * Returns a string "T" if the input formula is
     * syntactically valid, otherwise returns a string "F".
     *
     * @param formula - the formula to check for syntactical soundness
     */
    private static String analyzeFormulaSoundness(String formula){
        String matches = "";
        Matcher matcher = FORMULA_PATTERN.matcher(formula);
        if(matcher.matches()){
            matches = "T";
        } else {
            matches = "F";
        }
        return matches;
    }

}
