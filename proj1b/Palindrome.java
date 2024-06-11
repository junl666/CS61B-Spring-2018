public class Palindrome {

    /**
     * Given a String, wordToDeque should return a Deque
     * where the characters appear in the same order as in the String.
     * For example, if the word is “persiflage”, then the returned Deque
     * should have ‘p’ at the front, followed by ‘e’, and so forth.
     * */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> rtDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            rtDeque.addLast(word.charAt(i));
        }
        return rtDeque;
    }

    /**
     * The isPalindrome method should return true if the given word
     * is a palindrome, and false otherwise. Any word of length 1 or 0
     * is a palindrome. The function below will be implemented using recursion.
     * */
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    /**
     * The isPalindrome method should return true if the given word
     * is a palindrome, and false otherwise, but the equal assert will be judged
     * by the given comparator. Any word of length 1 or 0 is a palindrome.
     * The function below will be implemented using recursion.
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);
    }

    /**
     * Helper function for isPalindrome.
     */
    public boolean isPalindromeHelper(Deque<Character> d) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        } else if (d.removeFirst() == d.removeLast()) {
            return isPalindromeHelper(d);
        } else {
            return false;
        }
    }

    /**
     * Helper function for isPalindrome with the given comparator.
     */
    public boolean isPalindromeHelper(Deque<Character> d, CharacterComparator cc) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        } else if (cc.equalChars(d.removeFirst(), d.removeLast())) {
            return isPalindromeHelper(d, cc);
        } else {
            return false;
        }
    }
}
