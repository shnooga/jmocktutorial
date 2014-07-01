package com.oogie;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.hamcrest.CoreMatchers.containsString;
import org.hamcrest.Matcher;
import org.hamcrest.core.StringContains;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        String s = "yes we have no bananas today";
        /* Long winded version
         Matcher<String>containsBananas = new StringContains("bananas");
         Matcher<String>containsMangoes = new StringContains("mangoes"); 
         assertTrue(containsBananas.matches(s));
         assertFalse(containsMangoes.matches(s));       
     
         assertTrue(containsString("bananas").matches(s));
         assertFalse(containsString("mangoes").matches(s));     
         */

        // Prefered way.
        assertThat(s, containsString("bananas"));
        assertThat(s, containsString("mangoes"));
    }
}
