public class NBody {

    public static double readRadius(String path) {
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
    }

    public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[num];
		for (int i = 0; i < num; i++) {
			allPlanets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
										in.readDouble(), in.readDouble(), in.readString());
		}
		return allPlanets;
    }

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dT = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] allPlanets = readPlanets(filename);
		double radius = readRadius(filename);

		String imgBg = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

		/* Stamps three copies of advice.png in a triangular pattern. */
		StdDraw.picture(0, 0, imgBg);

		for (int i = 0; i < allPlanets.length; i++) {
			allPlanets[i].draw();
		}
		StdDraw.show();

		StdDraw.enableDoubleBuffering();

		double [] xForces = new double[allPlanets.length];
		double [] yForces = new double[allPlanets.length];
		double curr_t = 0.;
		while (curr_t < T) {
			for (int i = 0; i < allPlanets.length; i++) {
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}
			StdDraw.picture(0, 0, imgBg);
			for (int i = 0; i < allPlanets.length; i++) {
				allPlanets[i].update(dT, xForces[i], yForces[i]);
				allPlanets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			curr_t += dT;
		}
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
						  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
						  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
		}
	}
}
