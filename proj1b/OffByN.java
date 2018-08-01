public class OffByN implements CharacterComparator {
    public int N;
    public OffByN(int x){
        N= x;
    }
    public boolean equalChars(char x, char y){
        return equalChars( x,  y, N);
    }
    public boolean equalChars(char x, char y, int N){
        return Math.abs(x-y)==N;
    }
    public static void main(String[] args) {
        OffByN offby5 = new OffByN(5);
        System.out.println( offby5.equalChars('a', 'f'));  // true
        System.out.println(offby5.equalChars('f', 'h'));  // false
    }
}
