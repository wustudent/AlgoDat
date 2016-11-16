/**
 * Represents a Cube
 * @author AlgoDat
 */
public class Cube extends Cuboid{

	/*
	 * Constructor without parameter
	 */
	public Cube() {
		super.height=0;
		super.length=0;
		super.width=0;
	}
	/*
	 * Constructor with one parameter
	 * 
	 * @param length the side length of the cube
	 */
	public Cube(double length) {
		super.height=length;
		super.length=length;
		super.width=length;
	}

	public double getLength(){
		return this.length;
	}
	//TODO: ggf. weitere Methoden und member implementieren
	@Override
	double calculateVolume() {
		return super.calculateVolume();//return this.height*this.length*this.width;
	}
	@Override
	double calculateSurface() {
		return super.calculateSurface();//return 2*(this.height*this.length+this.length*this.width+this.height*this.width);
	}
}

