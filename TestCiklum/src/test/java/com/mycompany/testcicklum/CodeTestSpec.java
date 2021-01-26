/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testcicklum;

/**
 *
 * @author Sergio
 */
import org.junit.Test;
import com.mycompany.testciklum.CodeTest;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.Assert;
import static org.junit.Assert.assertArrayEquals;

/*
 *   Please code the tests in the format of reverseArray_returnsExpectedResult. This is an example of how we write our tests at Cardano.
 *
 *   Test 1-4 tests are easy as the function returns a result that can be asserted. Tests 5-7 are more difficult to assert as they are
 *   void, use your knowledge to write a meaningful test.
 *
 *   Feel free to use the internet to help you with you answers but make sure you understand the answer as we will ask you questions.
 */

public class CodeTestSpec {
    @Test
    public void reverseArray_returnsExpectedResult() {
        
        final int[] EXPECTED = {5,4,3,2,1};
        final int[] actual = CodeTest.reverseArray(new int[] {1,2,3,4,5});
        
        final int[] EXPECTED_null = {};
        final int[] actual_null = CodeTest.reverseArray(null);
        assertArrayEquals(EXPECTED_null, actual_null);
        
        final int[] EXPECTED_empty = {};
        final int[] actual_empty = CodeTest.reverseArray(new int[]{});
        assertArrayEquals(EXPECTED_empty, actual_empty);
        
        assertArrayEquals(EXPECTED, actual);
        assertArrayEquals(EXPECTED_null, actual_null);
        assertArrayEquals(EXPECTED_empty, actual_empty);
    }

    @Test
    public void uppercaseArray_returnsExpectedResult() {
        final String[] EXPECTED = {"THIS","IS","THE","TEST"};
        final String[] actual = CodeTest.uppercaseArray(new String[]{"tHis","is","THe","TEST"});
        assertArrayEquals(EXPECTED, actual);
    }

    @Test
    public void findWordCount_returnsExpectedResult() {
        Assert.assertEquals(2,CodeTest.findWordCount("the cat jumped over the mat","the"));
        Assert.assertEquals(1,CodeTest.findWordCount("the cat jumped over the mat","mat"));
        Assert.assertEquals(0,CodeTest.findWordCount("the cat jumped over the mat","doormat"));
    }

    @Test
    public void composeU_returnsExpectedResult() {
        int composed = CodeTest.composeU(x -> x+1, y -> y*3).apply(12);
        Assert.assertEquals(39, composed);
    }

    @Test
    public void writeContentsToConsole_returnsExpectedResult() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        
        // Call to write the content of a file switching the System out to PrintStream
        CodeTest.writeContentsToConsole();
        
        // Set PrintStream buffer with file content
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String fileContent = buffer.toString();
        
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.println("En un lugar de la Mancha,");
        printWriter.println("de cuyo nombre no quiero acordarme,");
        printWriter.println("no ha mucho tiempo que vivia un hidalgo de los de lanza en astillero,");
        printWriter.println("adarga antigua,");
        printWriter.println("rocin flaco y");
        printWriter.println("galgo corredor...");
        printWriter.close();
        
        String expected = expectedStringWriter.toString();
        Assert.assertEquals(expected, fileContent);
        
        // Set System out to the standard output
        buffer.reset();
    }

    @Test
    public void handleInvalidArgument_returnsExpectedResult() {
        CodeTest.numbersIn = new String[]{"1","3","4","5"};
        CodeTest.handleInvalidArgument();
    }

    @Test
    public void puzzle_returnsExpectedResult() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        
        CodeTest ct = new CodeTest();
        CodeTest.numbersIn = new String[]{"1","2","3","6","5","5"};
        CodeTest.puzzle();
        
        // Set PrintStream buffer with input content
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String fileContent = buffer.toString();
        
        Assert.assertEquals("1,2,3,6,5,5,snap!", buffer.toString().strip());
        // Set System out to the standard output
        buffer.reset();
    }
}