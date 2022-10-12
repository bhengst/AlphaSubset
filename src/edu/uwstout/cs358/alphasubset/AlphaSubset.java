/**
 * 
 */
package edu.uwstout.cs358.alphasubset;

import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.TreeSet;
import java.lang.Character;

/**
 * 
 * This class takes in a phrase and finds
 * the subset of only alphabetic characters.
 * 
 * @author jocelyn
 * @author Brittany Hengst
 *
 */
public class AlphaSubset {
  // phrase entered
  private String mPhrase;
  // subset of the phrase entered with only alphabetic characters
  private ArrayList<Character> mSubset;

  /**
   * Constructor
   * @param phrase - the phrase to be used
   */
  public AlphaSubset(String phrase) {
    mPhrase = phrase;
    if (!validate()) {
      mPhrase = null;
    }
  }

  /**
   * Checks if the subsets are equivalent
   * @param other subset to compare
   * @return true if matches
   * @throws InvalidPhraseException
   */
  public Boolean compare(AlphaSubset other) throws InvalidPhraseException {
    return this.getSubset().equals(other.getSubset());
  }

  // getter for phrase
  public String getPhrase() {
    return mPhrase;
  }

  /**
   * Get the subset of the given phrase without special characters
   * and numbers.
   * 
   * @return - ArrayList of the subset
   * @throws InvalidPhraseException
   */
  public ArrayList<Character> getSubset() throws InvalidPhraseException {
    if (mPhrase == null) {
      throw new InvalidPhraseException(mPhrase + " is invalid.");
    } // if the subset hasn't been found yet
    if (mSubset == null) {
      mSubset = new ArrayList<Character>();
      TreeSet<Character> uniqueCharsInPhrase = new TreeSet<Character>();
      for (int i = 0; i < mPhrase.length(); i++) {
        uniqueCharsInPhrase.add(mPhrase.charAt(i));
      }
      for (Character phraseChar : uniqueCharsInPhrase) {
        phraseChar = Character.toUpperCase(phraseChar.charValue());
        if (Character.isLetterOrDigit(phraseChar.charValue())) {
          mSubset.add(phraseChar);
        }
      }
    }
    return mSubset;

  }

  /**
   * Validates that the phrase entered is only using
   * characters that are allowed.
   * 
   * @return - true if the phrase is valid
   */
  private boolean validate() {
    return Pattern.matches("[a-zA-Z0-9!.?]+", mPhrase);
  }
}
