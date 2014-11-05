package formula;

import java.io.*;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
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

    static Pattern NUMBER = Pattern.compile("[2-9]");

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
            System.err.println("I/O Exception occurred while reading std in.");
        } catch (IllegalArgumentException iae) {
            System.out.println("F");
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
    private static String analyzeFormulaCompleteness(String formula) throws IllegalArgumentException {
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
    private static String analyzeFormulaSoundness(String formula) throws IllegalArgumentException {
        String matches = "";
        Matcher matcher = FORMULA_PATTERN.matcher(formula);
        if(matcher.matches()){
            matches = "T";
        } else {
            matches = "F";
        }
        return matches;
    }

    private static List stripParentheses(String formula) throws IllegalArgumentException {
        List components = new ArrayList();
        String subFormula = "";
        int endParenIndex = 0;

        if (formula.contains("(")){
            while(formula.contains("(")){
                endParenIndex = formula.indexOf(")");
                subFormula = formula.substring(formula.lastIndexOf("("), endParenIndex);
                checkParenthesesMultiplier(formula, endParenIndex);
                components.add(subFormula);
                formula.replace(subFormula, "");
            }
        }
    }

    /**
     * Checks the multiplier that follows the parentheses of a chemical formula.
     * Throws an exception if the multiplier is not between 2 and 9.
     *
     * @param formula - the formula to check the multiplier in
     * @param endParenIndex - where to begin checking for the multiplier at
     * @throws IllegalFormatException - thrown when the multiplier is not between 2 and 9
     */
    private static void checkParenthesesMultiplier(String formula, int endParenIndex) throws IllegalFormatException {
        StringBuilder formulaBuilder = new StringBuilder(formula);
        int currIndex = endParenIndex + 1;
        StringBuilder numberBuilder = new StringBuilder();
        while(Character.isDigit(formulaBuilder.charAt(currIndex))){
            numberBuilder.append(formulaBuilder.charAt(currIndex));
            formulaBuilder.deleteCharAt(currIndex);
            currIndex++;
        }
        Matcher matcher = NUMBER.matcher(numberBuilder.toString());
        if (!matcher.matches()){
            throw new IllegalArgumentException("Incorrect multipliers following parentheses.");
        }
        formula = formulaBuilder.toString();
    }

}
