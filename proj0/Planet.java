public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) *  (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return G * this.mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return G * this.mass * p.mass * dx/ (r * r * r);
    }

    public double calcForceExertedByY(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return G * this.mass * p.mass * dy/ (r * r * r);
    }

    public double calcNetForceExertedByX (Planet[] allPlanets) {
        double net_force = 0.;
        for (int i = 0; i < allPlanets.length; i++) {
            if (allPlanets[i] != this) {
                net_force += calcForceExertedByX(allPlanets[i]);
            }
        }
        return net_force;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets) {
        double net_force = 0.;
        for (int i = 0; i < allPlanets.length; i++) {
            if (allPlanets[i] != this) {
                net_force += calcForceExertedByY(allPlanets[i]);
            }
        }
        return net_force;
    }

	public void update(double dt,double fX,double fY){
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}
