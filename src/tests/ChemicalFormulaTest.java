package tests;

import formula.ChemicalFormula;
import formula.FormulaValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the chemical formula class of project 10 in EECS 293
 *
 * @author Shaun Xoward
 */
public class ChemicalFormulaTest {

    @Test
    public void testJunkInput(){
        assertFalse(isValidFormula("      sdfsdfs"));
        assertFalse(isValidFormula("sdfsdfs     "));
        assertFalse(isValidFormula("sdfs dfs"));
        assertFalse(isValidFormula("X "));
        assertFalse(isValidFormula(" X"));
        assertFalse(isValidFormula(" X "));
        assertFalse(isValidFormula("X\nNe"));
    }

    @Test
    public void testSingleElement() {
        assertTrue(isValidFormula("Xe"));
        assertTrue(isValidFormula("Ja"));
        assertTrue(isValidFormula("X300"));
        assertTrue(isValidFormula("Xee"));
    }

    @Test
    public void testMultipleElement() {
        assertTrue(isValidFormula("XeNe"));
        assertTrue(isValidFormula("XeN3"));
        assertTrue(isValidFormula("C3O4Az8N3"));
        assertTrue(isValidFormula("XeNeArOXeRn"));
    }

    @Test
    public void testIllegalElement() {
        assertFalse(isValidFormula("jA"));
        assertFalse(isValidFormula("XeNe3rOXeRn"));
        assertFalse(isValidFormula("q"));
        assertFalse(isValidFormula("(N)"));
        assertFalse(isValidFormula("(NE3)"));
    }

    @Test
    public void testMultiplier() {
        assertTrue(isValidFormula("Xe2Ne"));
        assertTrue(isValidFormula("XeNe2"));
        assertTrue(isValidFormula("Xe2"));
        assertFalse(isValidFormula("Xe2e"));
    }

    @Test
    public void testIllegalMultiplier() {
        assertFalse(isValidFormula("Xe1"));
        assertFalse(isValidFormula("Xe01"));
        assertFalse(isValidFormula("Xe0"));
        assertFalse(isValidFormula("Xe-1"));
    }

    @Test
    public void testNestedParentheses(){
        assertTrue(isValidFormula("(Xe(Ne)3)3"));
        assertFalse(isValidFormula("(Xe(Ne)02)3"));
    }

    @Test
    public void testParentheses() {
        assertTrue(isValidFormula("(XeNe)3"));
        assertTrue(isValidFormula("(XNO3)2"));
        assertTrue(isValidFormula("X3(XeX)3Ne4"));
        assertTrue(isValidFormula("X9(NYZ3I2)2"));
        assertTrue(isValidFormula("X9(NYZ3I2)2(NYZ3I2)2"));
        assertTrue(isValidFormula("X9(NYZ3I2)2(NYZ3I2)2Z3"));
        assertTrue(isValidFormula("(NYZ3I2)2(NYZ3I2)2Z3"));
        assertFalse(isValidFormula("(NYZ3I22(NYZ3I2)2Z3"));
        assertFalse(isValidFormula("(NYZ3I2)2NYZ3I2)2Z3"));
    }

    @Test
    public void testIllegalCharacters() {
        assertFalse(isValidFormula(""));
        assertFalse(isValidFormula("!"));
        assertFalse(isValidFormula("X!e"));
        assertFalse(isValidFormula("X!"));
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
