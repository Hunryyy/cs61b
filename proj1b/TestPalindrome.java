import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testPalindrome(){
        assertTrue(palindrome.isPalindrome("bb"));
        assertFalse(palindrome.isPalindrome("ab"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));

        assertFalse(palindrome.isPalindrome("aA"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("aaaaab"));
    }

    @Test
    public void testPalindrome_OffByOne() {

        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("abb",cc));
        assertTrue(palindrome.isPalindrome("a",cc));
        assertTrue(palindrome.isPalindrome("flake",cc));

        assertFalse(palindrome.isPalindrome("noon",cc));
        assertFalse(palindrome.isPalindrome("aAab",cc));
    }

    @Test
    public void testIsPalindromeOffByN() {
        int N = 5;
        CharacterComparator offBy5 = new OffByN(N);

        // 'a' (97) and 'f' (102) differ by 5
        assertTrue(palindrome.isPalindrome("af", offBy5));

        // 'a' and 'f' differ by 5, 'b' and 'g' differ by 5
        assertTrue(palindrome.isPalindrome("abgf", offBy5));

        // 'a' and 'b' only differ by 1, so they fail for OffBy5
        assertFalse(palindrome.isPalindrome("ab", offBy5));
    }
}
