public class Palindrome {
    public static Deque<Character> wordToDeque(String word){
        Deque<Character> deque= new ArrayDequeSolution<>();
        for (char ch : word.toCharArray()) {
            deque.addLast(ch);
        }
        return deque;
    }

    public static boolean isPalindrome(String word){
        return isPalindrome (wordToDeque(word));
        }

    public static boolean isPalindrome(Deque dequeWord){
        if (dequeWord.size()<=1){
            return true;
        }
        if (dequeWord.removeLast()==dequeWord.removeFirst()){
            return isPalindrome(dequeWord);
        }
        else {
            return false;
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc){
        return isPalindrome(wordToDeque(word), cc);
    }

    public static boolean isPalindrome(Deque dequeWord, CharacterComparator cc){
        if (dequeWord.size()<= 1){
            return true;
        }
        if (cc.equalChars((char) dequeWord.removeLast() , (char) dequeWord.removeFirst())){
            return isPalindrome(dequeWord,cc);
        }
        else {
            return false;
        }
    }
    public static void main(String[] args) {
        CharacterComparator OBO = new OffByOne();
        System.out.println(isPalindrome("flake",OBO));
    }
}
