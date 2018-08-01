public class Monster {
    protected String noise = "blargh";
    public static int spookFactor = 5;

    public Monster() {
        System.out.println("Muhahaha !!!");
    }

    public void spook() {
        System.out.println("I go" + noise);
        System.out.println("I am" + spookFactor + "spooky.");
    }
    public static void mash(Monster g) {
        System.out.println("boogity boo :");
        g.spook();
        //	spook	()	;
    }

}