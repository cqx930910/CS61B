class Ghoul extends Monster {
    public Ghoul() {
        System.out.println("I am a ghoul.");
    }
    @Override
    public void spook() {
        System.out.println("I ’m so ghoul :" + noise);
        System.out.println("I am" + spookFactor + "spooky.");
    }

    public static void mash(Monster g) {
        System.out.println("boogity boo :");
        g.spook();
        //	spook	()	;
    }

    public void haunt() {
        System.out.println("ERRERERRRRERRR");
        mash(this);
    }

    public void bark(){
        System.out.println("Lucas");
    }
    public static void main(String[] args) {
        //	part	a
        System.out.println(" Part a : ");
        Monster m = new Ghoul();
        m.mash(m);

        System.out.println("Part b :");
        Ghoul g = new Ghoul();
        g.spook();
        g.mash(g);

        System.out.println("Part c :");

        g.spookFactor = 10;
//        m.mash(m);
        System.out.println(" Part d : ");
        Ghoul ghastly = new Ghoul();
        m = ghastly;
        ghastly = (Ghoul) m;
        ghastly.haunt();
        m.mash(ghastly);
    }
}