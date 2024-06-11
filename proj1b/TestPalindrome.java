import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne obo = new OffByOne();
    static OffByN obn = new OffByN(5);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindromeEmpty() {
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testPalindromeOne() {
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testPalindromeTwo1() {
        assertTrue(palindrome.isPalindrome("aa"));
    }

    @Test
    public void testPalindromeTwo2() {
        assertFalse(palindrome.isPalindrome("aA"));
    }

    @Test
    public void testPalindromeThree1() {
        assertTrue(palindrome.isPalindrome("aba"));
    }

    @Test
    public void testPalindromeThree2() {
        assertFalse(palindrome.isPalindrome("abb"));
    }

    @Test
    public void testPalindromeThree3() {
        assertTrue(palindrome.isPalindrome("aaa"));
    }

    @Test
    public void testPalindromeFour1() {
        assertTrue(palindrome.isPalindrome("abba"));
    }

    @Test
    public void testPalindromeFour2() {
        assertTrue(palindrome.isPalindrome("aaaa"));
    }

    @Test
    public void testPalindromeFour3() {
        assertFalse(palindrome.isPalindrome("abab"));
    }

    @Test
    public void testPalindromeFour4() {
        assertFalse(palindrome.isPalindrome("aaab"));
    }

    @Test
    public void testPalindromeMore() {
        assertTrue(palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testPalindromeCCEmpty() {
        assertTrue(palindrome.isPalindrome("", obo));
    }

    @Test
    public void testPalindromeCCOne() {
        assertTrue(palindrome.isPalindrome("a", obo));
    }

    @Test
    public void testPalindromeCCTwo() {
        assertTrue(palindrome.isPalindrome("ab", obo));
        assertTrue(palindrome.isPalindrome("ba", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("aA", obo));
    }

    @Test
    public void testPalindromeCCThree() {
        assertTrue(palindrome.isPalindrome("abb", obo));
        assertTrue(palindrome.isPalindrome("baa", obo));
        assertFalse(palindrome.isPalindrome("aac", obo));
        assertFalse(palindrome.isPalindrome("aAa", obo));
    }

    @Test
    public void testPalindromeCCFour() {
        assertTrue(palindrome.isPalindrome("acbb", obo));
        assertTrue(palindrome.isPalindrome("bcba", obo));
        assertFalse(palindrome.isPalindrome("aacb", obo));
        assertFalse(palindrome.isPalindrome("abaa", obo));
    }

    @Test
    public void testPalindromeCCMore() {
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("af", obn));
    }

}
