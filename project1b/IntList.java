public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }
    public void skippify() {
        IntList p = this;
        int n = 1;
        while (p != null) {
            IntList next = p.rest;
            for (int i = 0; i < n; i += 1) {
                if (next == null) {
                    return; // NOTE: This should be a break to make more sense.
                }
                next = next.rest;
            }
            p.rest = next;
            p = p.rest;
            n++;
        }
    }

    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(1, L);
        L = new IntList(2, L);
        L = new IntList(3, L);
        L = new IntList(4, L);
        L = new IntList(5, L);
        L = new IntList(6, L);
        L = new IntList(7, L);
        L = new IntList(8, L);
        L = new IntList(9, L);
        L = new IntList(10, L);
        L = new IntList(11, L);
        L.skippify();
        L.get(0);
    }
}