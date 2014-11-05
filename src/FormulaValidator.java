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
//        checkEmptyError(formula);
//        checkMultipleLineError(formula);
//        checkSpaceError(formula);
//        return checkPotentialFormula(errors);
        return false;
    }

}
