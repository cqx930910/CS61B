public class IntList5000 {
    private IntList5000 rest;
    private int primitive;
    private Integer reference;
    public static String slogan;
    public IntList5000(IntList5000 r, int p, Integer l, String s) {
        rest = r; primitive = p; reference = l; slogan = s;
    }
    public static void disenturbulate(IntList5000 p) {
        p.primitive = 5000;
        p.reference = new Integer(1000);
        p = new IntList5000(null, 5, 10, "relax");
    }
    public void indoctrinate(int b) {
        b = 0;
        this.rest.primitive = b;
    }
    public void integrate(Integer v) {
        this.reference = v;
        v = new Integer(10);
    }
    public static void main(String[] args) {
        IntList5000 x = new IntList5000(null, 100, new Integer(7), "live");
        IntList5000 y = new IntList5000(x, 9, x.reference, "eat");
        System.out.println(y.primitive);
        System.out.println(y.reference);
        IntList5000.disenturbulate(y);
        System.out.println(y.primitive);
        System.out.println(y.reference);
        y.indoctrinate(100);
        System.out.println(x.primitive);
        x.integrate(new Integer(200));
        System.out.println(x.reference);
        System.out.println(IntList5000.slogan);
    }
}