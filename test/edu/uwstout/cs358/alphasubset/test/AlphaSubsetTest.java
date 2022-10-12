/**
 * 
 */
package edu.uwstout.cs358.alphasubset.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import edu.uwstout.cs358.alphasubset.*;

/**
 * @author jocelyn
 * @author Brittany Hengst
 *
 */
class AlphaSubsetTest {

	@Test
	/**
	 * test a simple case:
	 *   all uppercase letters
	 *   no duplicates
	 *   no special characters
	 *   no numbers
	 *   letters are already in order
	 *   
	 */
	void simpleTest() {
		
		AlphaSubset trivial = new AlphaSubset("ABCD");
		
		//verify that the phrase validated
		assertEquals("ABCD", trivial.getPhrase());
		
		//verify that the correct subset is returned
		String expectedAsString = "ABCD";
		
		//map expected string to an array list of characters
		ArrayList<Character> expected = new ArrayList<Character>();
		for (Character expectedChar : expectedAsString.toCharArray()) {
			expected.add(expectedChar);
		}
	
		try {
			assertArrayEquals(expected.toArray(), trivial.getSubset().toArray());
		} catch (InvalidPhraseException e) {
			fail("Unexpected exception getting the subset");
		}

	}
	
	@Test
	/**
	 * test a simple compare:
	 *   all uppercase letters
	 *   no duplicates
	 *   no special characters
	 *   no numbers
	 *   letters are already in order
	 *   
	 *   Comparing to phrase using a different order
	 */
	void simpleCompare() {
		
		AlphaSubset trivial = new AlphaSubset("ABCD");
		AlphaSubset unsortedTrivial = new AlphaSubset("CDBA");
			
		try {
			assertTrue(trivial.compare(unsortedTrivial));
		} catch (InvalidPhraseException e) {
			fail("Unexpected exception comparing subsets that should be equal");
		}

	}
	
	@Test
	/**
	 * special characters phrase
	 *   
	 *   Expecting an exception for invalid phrase
	 */
	void specialCharStringTest() {

		AlphaSubset specialChars = new AlphaSubset("#$");
		
		//note:  if you want to challenge yourself, find the assert that checks for the exception to be thrown
		//       rather than using a boolean to see if it was triggered.
		boolean expectedExceptionTriggered = false;
		try {
			ArrayList<Character> specialCharsSubset = specialChars.getSubset();
		} catch (InvalidPhraseException e) {
			expectedExceptionTriggered = true;
		}

		assertTrue(expectedExceptionTriggered);
	}
	
	@Test
  /**
   * null phrase
   *   
   *   Expecting an exception for invalid phrase
   */
  void nullStringTest() {

    AlphaSubset nullString = new AlphaSubset(null);
    
    boolean expectedExceptionTriggered = false;
    try {
      ArrayList<Character> nullSubset = nullString.getSubset();
    } catch (InvalidPhraseException e) {
      expectedExceptionTriggered = true;
    }

    assertTrue(expectedExceptionTriggered);
  }

	 @Test
	  /**
	   * empty string phrase
	   *   
	   *   Expecting an exception for invalid phrase
	   */
	  void emptyStringTest() {

	    AlphaSubset emptyString = new AlphaSubset("");
	    
	    boolean expectedExceptionTriggered = false;
	    try {
	      ArrayList<Character> emptySubset = emptyString.getSubset();
	    } catch (InvalidPhraseException e) {
	      expectedExceptionTriggered = true;
	    }

	    assertTrue(expectedExceptionTriggered);
	  }

	 @Test
	 /**
	   * single number phrase
	   * 
	   *   Expecting an exception for invalid phrase
	   */
	  void singleNumberStringTest() {

	    AlphaSubset numChars = new AlphaSubset("1");
	    
	    boolean expectedExceptionTriggered = false;
	    try {
	      ArrayList<Character> numCharsSubset = numChars.getSubset();
	    } catch (InvalidPhraseException e) {
	      expectedExceptionTriggered = true;
	    }

	    assertTrue(expectedExceptionTriggered);
	  }
	 
	 @Test
   /**
     * single allowed special character phrase
     * 
     *   Expecting an exception for invalid phrase
     */
    void singleAllowedSpecialTest() {

      AlphaSubset numChars = new AlphaSubset("?");
      
      boolean expectedExceptionTriggered = false;
      try {
        ArrayList<Character> numCharsSubset = numChars.getSubset();
      } catch (InvalidPhraseException e) {
        expectedExceptionTriggered = true;
      }

      assertTrue(expectedExceptionTriggered);
    }
	 
	 @Test
	  /**
	   * test a simple case:
	   *   all lowercase letters
	   *   no duplicates
	   *   no special characters
	   *   no numbers
	   *   letters are already in order
	   *   
	   */
	  void lowerCaseOrderTest() {
	    
	    AlphaSubset lowerChars = new AlphaSubset("abcd");
	    
	    //verify that the phrase validated
	    assertEquals("abcd", lowerChars.getPhrase());
	    
	    //verify that the correct subset is returned
	    String expectedAsString = "ABCD";
	    
	    //map expected string to an array list of characters
	    ArrayList<Character> expected = new ArrayList<Character>();
	    for (Character expectedChar : expectedAsString.toCharArray()) {
	      expected.add(expectedChar);
	    }
	  
	    try {
	      assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
	    } catch (InvalidPhraseException e) {
	      fail("Unexpected exception getting the subset");
	    }

	  }
	 
	 @Test
   /**
    * test a simple case:
    *   all lowercase letters
    *   no duplicates
    *   no special characters
    *   no numbers
    *   letters are not in order
    *   
    */
   void lowerCaseTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("dcba");
     
     //verify that the phrase validated
     assertEquals("dcba", lowerChars.getPhrase());
     
     //verify that the correct subset is returned
     String expectedAsString = "ABCD";
     
     //map expected string to an array list of characters
     ArrayList<Character> expected = new ArrayList<Character>();
     for (Character expectedChar : expectedAsString.toCharArray()) {
       expected.add(expectedChar);
     }
   
     try {
       assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception getting the subset");
     }

   }
	 
	 @Test
   /**
    * test a simple case:
    *   some lowercase letters
    *   no duplicates
    *   no special characters
    *   number in first place
    *   letters are already in order
    *   
    */
   void lowerCaseNumberTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("1efgh");
     
     //verify that the phrase validated
     assertEquals("1efgh", lowerChars.getPhrase());
     
     //verify that the correct subset is returned
     String expectedAsString = "EFGH";
     
     //map expected string to an array list of characters
     ArrayList<Character> expected = new ArrayList<Character>();
     for (Character expectedChar : expectedAsString.toCharArray()) {
       expected.add(expectedChar);
     }
     
     try {
       assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception getting the subset");
     }

   }
	 
	 @Test
   /**
    * test a simple case:
    *   some lowercase letters
    *   some uppercase letters
    *   no duplicates
    *   no special characters
    *   no numbers
    *   letters are already in order
    *   
    */
   void lowerAndUpperCaseOrderTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("AbCd");
     
     //verify that the phrase validated
     assertEquals("AbCd", lowerChars.getPhrase());
     
     //verify that the correct subset is returned
     String expectedAsString = "ABCD";
     
     //map expected string to an array list of characters
     ArrayList<Character> expected = new ArrayList<Character>();
     for (Character expectedChar : expectedAsString.toCharArray()) {
       expected.add(expectedChar);
     }
   
     try {
       assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception getting the subset");
     }

   }
	 
	 @Test
   /**
    * test a simple case:
    *   some lowercase letters
    *   some uppercase letters
    *   no duplicates
    *   no special characters
    *   no numbers
    *   letters are not in order
    *   
    */
   void lowerAndUpperCaseTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("DcbA");
     
     //verify that the phrase validated
     assertEquals("DcbA", lowerChars.getPhrase());
     
     //verify that the correct subset is returned
     String expectedAsString = "ABCD";
     
     //map expected string to an array list of characters
     ArrayList<Character> expected = new ArrayList<Character>();
     for (Character expectedChar : expectedAsString.toCharArray()) {
       expected.add(expectedChar);
     }
   
     try {
       assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception getting the subset");
     }

   }
	 
	 @Test
   /**
    * test a simple case:
    *   some lowercase letters
    *   duplicates
    *   no special characters
    *   no numbers
    *   letters are already in order
    *   
    */
   void duplicatesOrderedCaseTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("aaabc");
     
     //verify that the phrase validated
     assertEquals("aaabc", lowerChars.getPhrase());
     
     //verify that the correct subset is returned
     String expectedAsString = "ABC";
     
     //map expected string to an array list of characters
     ArrayList<Character> expected = new ArrayList<Character>();
     for (Character expectedChar : expectedAsString.toCharArray()) {
       expected.add(expectedChar);
     }
   
     try {
       assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception getting the subset");
     }

   }
	 
	 @Test
   /**
    * test a simple case:
    *   some lowercase letters
    *   duplicates
    *   no special characters
    *   no numbers
    *   letters are not in order
    *   
    */
   void duplicatesCaseTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("cccddbb");
     
     //verify that the phrase validated
     assertEquals("cccddbb", lowerChars.getPhrase());
     
     //verify that the correct subset is returned
     String expectedAsString = "BCD";
     
     //map expected string to an array list of characters
     ArrayList<Character> expected = new ArrayList<Character>();
     for (Character expectedChar : expectedAsString.toCharArray()) {
       expected.add(expectedChar);
     }
   
     try {
       assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception getting the subset");
     }

   }
	 
	 @Test
   /**
    * test a simple case:
    *   all uppercase letters
    *   no duplicates
    *   allowed special character
    *   no numbers
    *   letters are already in order
    *   
    */
   void allowedSpecialCaseTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("AB?");
     
     //verify that the phrase validated
     assertEquals("AB?", lowerChars.getPhrase());
     
     //verify that the correct subset is returned
     String expectedAsString = "AB";
     
     //map expected string to an array list of characters
     ArrayList<Character> expected = new ArrayList<Character>();
     for (Character expectedChar : expectedAsString.toCharArray()) {
       expected.add(expectedChar);
     }
   
     try {
       assertArrayEquals(expected.toArray(), lowerChars.getSubset().toArray());
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception getting the subset");
     }

   }
	 
	 @Test
   /**
    * test a simple case:
    *   some lowercase letters
    *   no duplicates
    *   a special characters
    *   no numbers
    *   letters are already in order
    *   
    */
   void charsAndSpecialCaseTest() {
     
     AlphaSubset lowerChars = new AlphaSubset("abcd$");
     
     boolean expectedExceptionTriggered = false;
     try {
       ArrayList<Character> specialCharsSubset = lowerChars.getSubset();
     } catch (InvalidPhraseException e) {
       expectedExceptionTriggered = true;
     }

     assertTrue(expectedExceptionTriggered);

   }
   
   @Test
    /**
     * test a simple compare:
     *   all lowercase letters
     *   no duplicates
     *   no special characters
     *   no numbers
     *   letters are already in order
     *   
     *   Comparing to phrase using a different order
     */
    void lowerSimpleCompare() {
      
      AlphaSubset trivial = new AlphaSubset("abcd");
      AlphaSubset unsortedTrivial = new AlphaSubset("cdba");
        
      try {
        assertTrue(trivial.compare(unsortedTrivial));
      } catch (InvalidPhraseException e) {
        fail("Unexpected exception comparing subsets that should be equal");
      }

    }
   
   @Test
   /**
    * test a simple compare:
    *   all lowercase letters
    *   no duplicates
    *   no special characters
    *   no numbers
    *   letters are already in order
    *   
    *   Comparing to phrase that is not equal
    */
   void notEqualCompare() {
     
     AlphaSubset trivial = new AlphaSubset("abcd");
     AlphaSubset unsortedTrivial = new AlphaSubset("hgfi");
       
     try {
       assertFalse(trivial.compare(unsortedTrivial));
     } catch (InvalidPhraseException e) {
       fail("Unexpected exception comparing subsets that should be equal");
     }

   }

}
