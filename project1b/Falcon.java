public class Falcon extends Bird {
    public Falcon(){
        System.out.println("I am a Falcon");
    }
    public void gulgate(Falcon bird){
        System.out.println("Falcon Gulgate");
        System.out.println(a);
    }
    public  void test(){
        System.out.println("OH");
    }
    public static void main(String[] args) {
        Bird bird = new Falcon();

        Falcon falcon=(Falcon) bird;
        falcon.gulgate(falcon);
    }
}
