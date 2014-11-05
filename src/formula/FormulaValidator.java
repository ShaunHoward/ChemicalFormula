package formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Validates the syntactic context of a chemical formula.
 *
 * This class provides a barricade for the ChemicalFormula
 * class, so invalid input is caught early.
 *
 * If the string being checked can be a syntactically valid
 * chemical formula, true will be returned.
 *
 * Otherwise, false will be returned.
 *
 * @author Shaun Howard
 */
public class FormulaValidator {

    // Stores the errors found in the formula.
    private List<String> errors;

    public FormulaValidator() {
        errors = new ArrayList<>();
    }

    /**
     * Validate that the given formula can be a syntactically
     * valid chemical formula.
     * Returns true if it can be valid, false otherwise.
     *
     * @param formula - the formula to validate
     * @return whether the formula can be a syntactically valid
     * chemical formula
     */
    public boolean validateFormula(String formula){
        checkEmptyError(formula);
        checkMultipleLineError(formula);
        checkSpaceError(formula);
        checkMatchedParenthesis(formula);
        return checkPotentialFormula(errors);
    }

    /**
     * Checks if the input formula only contains whitespace.
     * Adds an error to error list accordingly.
     *
     * @param formula - the formula to check for only whitespace
     */
    private void checkEmptyError(String formula) {
        assert formula != null : "Formula is null during empty check.";
        String trimmedString = formula.trim();
        if (trimmedString.isEmpty()){
            errors.add("Input formula contained only whitespace.");
        }
    }

    /**
     * Checks if the input formula contains multiple lines.
     * Adds an error to error list accordingly.
     *
     * @param formula - the formula to check for multiple lines
     */
    private void checkMultipleLineError(String formula) {
        assert formula != null : "Formula is null during multiple line check.";
        if (formula.contains("\n")){
            errors.add("Input formula contained multiple lines.");
        }
    }

    /**
     * Checks if the formula contains a space anywhere.
     * Adds an error to error list accordingly.
     *
     * @param formula - the formula to check for a space
     */
    private void checkSpaceError(String formula) {
        assert formula != null : "Formula is null during space check.";
        if(formula.contains(" ")){
            errors.add("Input formula contained at least one space.");
        }
    }


    /**
     * Checks if the formula has matching parenthesis, if
     * there are any.
     * Adds an error to error list accordingly.
     *
     * @param formula - the formula to check for matching parentheses
     */
    private void checkMatchedParenthesis(String formula) {
        assert formula != null : "Formula is null during matched parentheses check.";
        StringBuilder formulaBuilder = new StringBuilder(formula);
        Stack parentheses = new Stack();
        int beginParenIndex = 0;
        int endParenIndex = 0;
        // Check for any beginning parentheses.
        while((beginParenIndex = formulaBuilder.indexOf("(")) > -1){
            formulaBuilder.deleteCharAt(beginParenIndex);
            parentheses.push("(");
            // Check for appropriate ending parentheses.
            if((endParenIndex = formulaBuilder.indexOf(")")) > -1 && endParenIndex > beginParenIndex){
                formulaBuilder.deleteCharAt(endParenIndex);
                // Try to match parentheses.
                if (!parentheses.isEmpty()) {
                    parentheses.pop();
                } else {
                    errors.add("Input formula contained too many ending parentheses.");
                }
            }
        }
        assert parentheses != null :"Stack is null after checking matched parentheses.";
        assert parentheses != null :"Formula builder is null after checking matched parentheses.";
        // Check if there are any parentheses left unmatched.
        checkRemainingParentheses(parentheses, formulaBuilder);
    }

    /**
     * Checks if the parentheses stack is not empty or if the input string
     * builder still contains ending parentheses.
     * When either are true, an error about mismatched parentheses is added
     * to the error list.
     *
     * @param stack - the stack to check if empty from parentheses matching
     * @param formula - the string builder to check for ending parentheses
     */
    private void checkRemainingParentheses(Stack stack, StringBuilder formula){
        if (!stack.isEmpty()){
            errors.add("Input formula contained too many starting parentheses.");
        } else if (formula.indexOf(")") > -1){
            errors.add("Input formula contained too many ending parentheses.");
        }
    }

    /**
     * Checks if the formula is a potential chemical formula
     * according to the amount of errors. If there are zero
     * errors, true is returned. Otherwise, false is
     * returned.
     *
     * @param errors - the errors found in the formula
     * @return whether the formula is potentially a chemical formula
     */
    private boolean checkPotentialFormula(List<String> errors){
        return errors.isEmpty();
    }
}
