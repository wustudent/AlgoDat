class Sphere {
	
	Point center;
	double radius;
	
	Sphere(int x, int y, int z, double r) {
		this.center=new Point(x,y,z);
		this.radius=r;
	}
	
	Sphere(Point c, double r) {
		this.center=new Point(c.getX(),c.getY(),c.getZ());
		this.radius=r;
	}
	
	double getX() {
		return this.center.getX(); 
	}

	double getY() {
		return this.center.getY(); 
	}

	double getZ() {
		return this.center.getZ();
	}


	double getRadius() {
		return this.radius; 
	}

	double calculateDiameter() {
		return this.radius*2; 
	}	
	
	double calculatePerimeter() {
		return 2*Math.PI*this.radius; 
	}
	
	double calculateVolume() {
		return 4/3*Math.PI*Math.pow(this.radius, 3);//4.0/3.0 
	}

}

