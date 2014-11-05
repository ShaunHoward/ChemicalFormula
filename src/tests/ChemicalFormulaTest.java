package tests;

import formula.ChemicalFormula;
import formula.FormulaValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the chemical formula class of project 10 in EECS 293
 *
 * @author Shaun Howard
 */
public class ChemicalFormulaTest {

    @Test
    public void testJunkInput(){
        assertFalse(isValidFormula("      sdfsdfs"));
        assertFalse(isValidFormula("sdfsdfs     "));
        assertFalse(isValidFormula("sdfs dfs"));
        assertFalse(isValidFormula("H "));
        assertFalse(isValidFormula(" H"));
        assertFalse(isValidFormula(" H "));
        assertFalse(isValidFormula("H\nNe"));
    }

    @Test
    public void testSingleElement() {
        assertTrue(isValidFormula("He"));
        assertTrue(isValidFormula("Ja"));
        assertTrue(isValidFormula("H300"));
    }

    @Test
    public void testMultipleElement() {
        assertTrue(isValidFormula("HeNe"));
        assertTrue(isValidFormula("HeN3"));
        assertTrue(isValidFormula("C3O4Az8N3"));
        assertTrue(isValidFormula("HeNeArOXeRn"));
    }

    @Test
    public void testIllegalElement() {
        assertFalse(isValidFormula("jA"));
        assertFalse(isValidFormula("HeNe3rOXeRn"));
        assertFalse(isValidFormula("q"));
        assertFalse(isValidFormula("(N)"));
        assertFalse(isValidFormula("(NE3)"));
    }

    @Test
    public void testMultiplier() {
        assertTrue(isValidFormula("He2Ne"));
        assertTrue(isValidFormula("HeNe2"));
        assertTrue(isValidFormula("He2"));
        assertFalse(isValidFormula("He2e"));
    }

    @Test
    public void testIllegalMultiplier() {
        assertFalse(isValidFormula("He1"));
        assertFalse(isValidFormula("He01"));
        assertFalse(isValidFormula("He0"));
        assertFalse(isValidFormula("He-1"));
    }

    @Test
    public void testNestedParentheses(){
        assertTrue(isValidFormula("(He(Ne)3)3"));
        assertFalse(isValidFormula("(He(Ne)02)3"));
    }

    @Test
    public void testParentheses() {
        assertTrue(isValidFormula("(HeNe)3"));
        assertTrue(isValidFormula("(HNO3)2"));
        assertTrue(isValidFormula("H3(HeH)3Ne4"));
        assertTrue(isValidFormula("H9(NYZ3I2)2"));
        assertTrue(isValidFormula("H9(NYZ3I2)2(NYZ3I2)2"));
        assertTrue(isValidFormula("H9(NYZ3I2)2(NYZ3I2)2Z3"));
        assertTrue(isValidFormula("(NYZ3I2)2(NYZ3I2)2Z3"));
        assertFalse(isValidFormula("(NYZ3I22(NYZ3I2)2Z3"));
        assertFalse(isValidFormula("(NYZ3I2)2NYZ3I2)2Z3"));
    }

    @Test
    public void testIllegalCharacters() {
        assertFalse(isValidFormula(""));
        assertFalse(isValidFormula("!"));
        assertFalse(isValidFormula("H!e"));
        assertFalse(isValidFormula("H!"));
    }

    /**
     * Checks if the formula is valid by calling the analyzeFormulaCompleteness() method.
     *
     * @param givenString - the string to check as a valid chemical formula
     * @return whether the chemical formula is valid or not
     */
    private boolean isValidFormula(String givenString) {
        if (ChemicalFormula.analyzeFormulaCompleteness(givenString).equals("T")) {
            return true;
        }
        return false;
    }

}
