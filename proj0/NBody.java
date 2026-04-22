public class NBody{
	public static double readRadius(String add){
		In read=new In(add);
		int numPlanets = read.readInt();
       
    double radius = read.readDouble();
    
    return radius;
		}
	public static Planet[] readPlanets(String add){
		In read=new In(add);
		int numPlanets= read.readInt();
		double radius = read.readDouble();
		Planet planets[] = new Planet[numPlanets];
		for(int i=0;i<numPlanets;i++){
			double xP = read.readDouble();
			double yP = read.readDouble();
			double xV = read.readDouble();
			double yV = read.readDouble();
			double m = read.readDouble();
			String img = read.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);				
	}
		return planets;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double radius=readRadius(filename);
		Planet[] planets;
		planets=readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering(); 
		int time=0;
		while (time<T ) {
			StdDraw.clear();
			

			double[] xForces=new double[planets.length];
			double[] yForces=new double[planets.length];
			for (int i=0;i<planets.length;i++) {
        xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
    }
		for(int i=0;i<planets.length;i++){
			planets[i].update(dt,xForces[i],yForces[i]);
		}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(Planet p:planets){
				p.draw();
			}

    
			StdDraw.show();
			StdDraw.pause(10);
		 time+=dt;	
}
		StdOut.printf("%d\n", planets.length);
StdOut.printf("%.2e\n", radius);
for (int i = 0; i < planets.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}	

}
}
