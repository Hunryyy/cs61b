public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	String imgFileName;
	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}	
	public double calcDistance(Planet p){
		double deltx=(p.xxPos-xxPos)*(p.xxPos-xxPos);
		double delty=(p.yyPos-yyPos)*(p.yyPos-yyPos);
		double result=Math.sqrt(deltx+delty);
		return result;
	}
	public double calcForceExertedBy(Planet p){
		double r=this.calcDistance(p);
		double Gnumber=6.67e-11;
		return (mass*p.mass)*Gnumber/(r*r);
	}
	public double calcForceExertedByX(Planet p){
		double deltx=p.xxPos-xxPos;
		double r=this.calcDistance(p);
		double force=this.calcForceExertedBy(p);
		return force*(deltx/r);
	}
	public double calcForceExertedByY(Planet p){
		double delty=p.yyPos-yyPos;
		double r=this.calcDistance(p);
		double force=this.calcForceExertedBy(p);
		return force*(delty/r);
}
	public double calcNetForceExertedByX(Planet[]allPlanets){
		double force=0.0;
		for(int i=0;i<allPlanets.length;i++){
			if(this!=(allPlanets[i]))
				force+=this.calcForceExertedByX(allPlanets[i]);
		}
		return force;
	}	
	public double calcNetForceExertedByY(Planet[]allPlanets){
		double force=0.0;
		for(int i=0;i<allPlanets.length;i++){
			if(!this.equals(allPlanets[i]))
				force+=this.calcForceExertedByY(allPlanets[i]);
		}
		return force;
	}
	public void update(double t, double xforce,double yforce){
		double xa=xforce/mass;
		double ya=yforce/mass;
		xxVel+=(xa*t);
		yyVel+=(ya*t);
		xxPos+=(xxVel*t);
		yyPos+=(yyVel*t);

	}
	public void draw(){

    
    
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	
	}
}
