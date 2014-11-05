import java.util.ArrayList;
import java.util.List;

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
        return checkPotentialFormula(errors);
    }

    /**
     * Checks if the input formula only contains whitespace.
     *
     * @param formula - the formula to check for only whitespace
     */
    private void checkEmptyError(String formula) {
        String trimmedString = formula.trim();
        if (trimmedString.isEmpty()){
            errors.add("Input formula contained only whitespace.");
        }
    }

    /**
     * Checks if the input formula contains multiple lines.
     *
     * @param formula - the formula to check for multiple lines
     */
    private void checkMultipleLineError(String formula) {
        if (formula.contains("\n")){
            errors.add("Input formula contained multiple lines.");
        }
    }

    /**
     * Checks if the formula contains a space anywhere.
     *
     * @param formula - the formula to check for a space
     */
    private void checkSpaceError(String formula) {
        if(formula.contains(" ")){
            errors.add("Input formula contained at least one space.");
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
