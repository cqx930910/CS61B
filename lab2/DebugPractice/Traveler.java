public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel= xV;
        yyVel=yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel= p.xxVel;
        yyVel= p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcForceExertedByX(Planet p){
        public double r;
        public double G = 6.67e-11;
        public double ForceX;
        r = Math.sqrt((xxPos -p.xxPos)^2+(yyPos-p.yyPos)^2);
        ForceX = Math.abs(xxPos -p.xxPos)/r *G*p.mass*mass/r^2;
        return ForceX;
    }

    public double calcForceExertedByY(Planet p){
        public double r;
        public double G = 6.67e-11;
        public double ForceX;
        r = Math.sqrt((xxPos -p.xxPos)^2+(yyPos-p.yyPos)^2);
        ForceY = Math.abs(yyPos -p.yyPos)/r *G*p.mass*mass/r^2;
        return ForceY;
    }

    public double calcDistance (planet p){
        return Math.sqrt((xxPos -p.xxPos)^2+(yyPos-p.yyPos)^2);
    }
}
