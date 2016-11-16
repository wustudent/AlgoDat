/**
 * Represents a Cuboid
 * http://en.wikipedia.org/wiki/Cuboid
 * 
 * @author AlgoDat
 *
 */
public class Cuboid extends Body{

	double height, length, width;
	
	/*
	 * Constructor for a Cuboid object
	 */
	public Cuboid() {
		this.height=0;
		this.length=0;
		this.width=0;
	}
	/*
	 * Constructor for a Cuboid object
	 * 
	 * @param h height
	 * @param l length
	 * @param w width
	 */
	public Cuboid(double h, double l, double w) {
		this.height=h;
		this.length=l;
		this.width=w;
	}
	/*
	 * Constructor for a Cuboid object
	 * 
	 * @param src Cuboid object
	 */
	public Cuboid(Cuboid src) {
		this.height=src.getHeight();
		this.length=src.getLength();
		this.width=src.getWidth();
	}
	public double getHeight(){
		return this.height;
	}
	public double getLength(){
		return this.length;
	}
	public double getWidth(){
		return this.width;
	}
	//TODO: ggf. weitere Methoden und member implementieren
	@Override
	double calculateVolume() {
		return this.height*this.length*this.width;
	}
	@Override
	double calculateSurface() {
		return 2*(this.height*this.length+this.length*this.width+this.height*this.width);
	}


}

