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
            Pattern.compile("([A-Z][a-z]?([2-9][0-9]*)?)+");

    // Formula to match in assertions.
    static String FORMULA = "([A-Z][a-z]?([2-9][0-9]*)?)+";

    // Pattern for identify the correct multiplier in a formula.
    static Pattern MULTIPLIER = Pattern.compile("[2-9]");

    /**
     * Takes a string from the standard in and determines if it is
     * a syntactically valid chemical formula.
     * Prints "T" to standard out if it is valid, prints "F" otherwise.
     *
     * @param args - the input arguments
     */
    public static void main(String[] args) {
        String formula = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String appendage;

            // Read the input from std in
            while ((appendage = br.readLine()) != null) {
                formula = formula + "\n" + appendage;
            }

            assert formula != null : "Formula input string is null.";

            //Prints whether the chemical formula is syntactically valid.
            System.out.println(analyzeFormulaCompleteness(formula));
        } catch (IOException ioe) {
            System.err.println("I/O Exception occurred while reading std in.");
            System.exit(1);
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
    public static String analyzeFormulaCompleteness(String formula){
        FormulaValidator validator = new FormulaValidator();
        //Validate the context of the formula.
        boolean potentialFormula = validator.validateFormula(formula);
        String potential = "";

        if (potentialFormula){
            // Analyze the syntactical soundness of the formula.
            potential = analyzeFormulaSoundness(formula);
        } else {
            potential = "F";
        }

        assert potential != null : "Potential formula string is null.";
        assert potential.equals("T") || potential.equals("F"): "Potential" +
                "is not in correct form from formula analysis.";

        return potential;
    }

    /**
     * Analyzes the syntax soundness of the input formula string
     * with parentheses group checking and a formula regular expression.
     * Returns a string "T" if the input formula is
     * syntactically valid, otherwise returns a string "F".
     *
     * @param formula - the formula to check for syntactical soundness
     */
    private static String analyzeFormulaSoundness(String formula){
        String matches = "T";
        assert formula != null : "Formula is null in soundness check.";
        assert FORMULA_PATTERN.toString().equals(FORMULA) : "Formula pattern is not correct pattern.";

        if (!(formula = stripParentheses(formula)).isEmpty()){
            Matcher matcher = FORMULA_PATTERN.matcher(formula);
            if (!matcher.matches()) {
                matches = "F";
            }
        } else {
            matches = "F";
        }

        return matches;
    }

    /**
     * Removes the parentheses grouping in the given formula as long
     * as it has the correct type of grouping with valid multipliers.
     * This is an effort to reduce complexity in checking the formula with
     * the formula regular expression.
     *
     * @param formula - the formula to check the parentheses grouping of
     * @return a parentheses-stripped string of the formula or an empty string
     * if the parentheses grouping was incorrect
     */
    private static String stripParentheses(String formula){
        assert formula != null : "Formula is null during stripping of parentheses.";
        StringBuilder formulaBuilder = new StringBuilder(formula);
        StringBuilder emptyBuilder = new StringBuilder("");

        // Strips all front parentheses.
        stripFrontParentheses(formulaBuilder);
        int endParenIndex = 0;
        Matcher multMatcher;

        // Check the grouping multipliers and strip ending parentheses if correct grouping.
        while ((endParenIndex = formulaBuilder.indexOf(")")) > -1){
            formulaBuilder.deleteCharAt(endParenIndex);

            // Try to check for multiplier of group.
            if (endParenIndex < formulaBuilder.length()) {
                char multiplier = formulaBuilder.charAt(endParenIndex);

                //Check multiplier digit, if there is one.
                if (Character.isDigit(multiplier)) {
                    multMatcher = MULTIPLIER.matcher(Character.toString(multiplier));
                    if (!multMatcher.matches()){
                        formulaBuilder = emptyBuilder;
                    }
                } else {
                    formulaBuilder = emptyBuilder;
                }
            }
            else {
                formulaBuilder = emptyBuilder;
            }
        }
        return formulaBuilder.toString();
    }

    /**
     * Strips the front parentheses from the given string builder.
     *
     * @param formulaBuilder - the builder for the formula to remove
     *                       front parentheses from
     */
    private static void stripFrontParentheses(StringBuilder formulaBuilder){
        int beginParenIndex = 0;
        while((beginParenIndex = formulaBuilder.indexOf("(")) > -1){
            formulaBuilder.deleteCharAt(beginParenIndex);
        }
    }
}
