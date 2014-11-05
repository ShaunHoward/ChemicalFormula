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
        assertFalse(invoke("      sdfsdfs"));
        assertFalse(invoke("sdfsdfs     "));
        assertFalse(invoke("sdfs dfs"));
        assertFalse(invoke("H "));
        assertFalse(invoke(" H"));
        assertFalse(invoke(" H "));
        assertFalse(invoke("H\nNe"));
    }

    @Test
    public void testSingleElement() {
        assertTrue(invoke("He"));
        assertTrue(invoke("Ja"));
        assertTrue(invoke("H300"));
    }

    @Test
    public void testMultipleElement() {
        assertTrue(invoke("HeNe"));
        assertTrue(invoke("HeN3"));
        assertTrue(invoke("C3O4Az8N3"));
        assertTrue(invoke("HeNeArOXeRn"));
    }

    @Test
    public void testIllegalElement() {
        assertFalse(invoke("jA"));
        assertFalse(invoke("HeNe3rOXeRn"));
        assertFalse(invoke("q"));
        assertFalse(invoke("(N)"));
        assertFalse(invoke("(NE3)"));
    }

    @Test
    public void testMultiplier() {
        assertTrue(invoke("He2Ne"));
        assertTrue(invoke("HeNe2"));
        assertTrue(invoke("He2"));
        assertFalse(invoke("He2e"));
    }

    @Test
    public void testIllegalMultiplier() {
        assertFalse(invoke("He1"));
        assertFalse(invoke("He01"));
        assertFalse(invoke("He0"));
        assertFalse(invoke("He-1"));
    }

    @Test
    public void testNestedParentheses(){
        assertTrue(invoke("(He(Ne)3)3"));
        assertFalse(invoke("(He(Ne)02)3"));
    }

    @Test
    public void testParentheses() {
        assertTrue(invoke("(HeNe)3"));
        assertTrue(invoke("(HNO3)2"));
        assertTrue(invoke("H3(HeH)3Ne4"));
        assertTrue(invoke("H9(NYZ3I2)2"));
        assertTrue(invoke("H9(NYZ3I2)2(NYZ3I2)2"));
        assertTrue(invoke("H9(NYZ3I2)2(NYZ3I2)2Z3"));
        assertTrue(invoke("(NYZ3I2)2(NYZ3I2)2Z3"));
        assertFalse(invoke("(NYZ3I22(NYZ3I2)2Z3"));
        assertFalse(invoke("(NYZ3I2)2NYZ3I2)2Z3"));
    }

    @Test
    public void testIllegalCharacters() {
        assertFalse(invoke(""));
        assertFalse(invoke("!"));
        assertFalse(invoke("H!e"));
        assertFalse(invoke("H!"));
    }

    private boolean invoke(String givenString) {
        if (ChemicalFormula.analyzeFormulaCompleteness(givenString).equals("T")) {
            return true;
        }
        return false;
    }

}
