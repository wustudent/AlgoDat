
/**
* this class <code> Picture </code> describes a RGB picture with dimensions width x height.
* Each pixels coulour is represented by an RGBColour
* 
* @author AlgoDat
*
*/

import javax.imageio.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Picture {

    /**
    * A 2d array containing the colours of the individual pixels
    */
    private RGBColor imageMatrix[][];

    /**
    * The width of the image in pixels
    */
    private int width;

    /**
    * height of the image in pixels
    */
    private int height;
    
    /**
     * intitialize a picture by creating a white picture
     */
    public Picture(int width, int height){
        this.height = height;
        this.width = width;
        createWhitePicture();
    }
    /**
     * initialize a picture by opening given file
     * @param picUrl path of *.bmp picture
     */
    public Picture(String picUrl){      
        openAndSetPicture(picUrl);      
    }

    /**
    * intialize a picture by giving an image matrix
    * @param imageMatrix two dimansionar RGBColor array
    */
    public Picture(RGBColor imageMatrix[][]){
        this.width = imageMatrix.length;
        this.height = imageMatrix[0].length;
        this.imageMatrix = imageMatrix;
    }

    /**
     * turns this picture 90 degrees to the right
     *
     */
    public void rot90DegRight(){
		RGBColor imageMatrix1[][]= new RGBColor[this.height][this.width];//TODO
		for (int w=0; w< this.width; w++){
            for(int h=0; h< this.height; h++){
                imageMatrix1[this.height-h-1][w] = new RGBColor(imageMatrix[w][h].getRed(),imageMatrix[w][h].getGreen(),imageMatrix[w][h].getBlue());                
            }
        }
        this.imageMatrix=imageMatrix1;
        int tmp=this.height;
        this.height=this.width;
        this.width=tmp;
    }
    
    /**
     * turns this picture 180 degrees
     *
     */
    public void rot180Deg(){
    	RGBColor imageMatrix1[][]= new RGBColor[this.width][this.height];//TODO
		for (int w=0; w< this.width; w++){
            for(int h=0; h< this.height; h++){
                imageMatrix1[this.width-w-1][this.height-h-1] = new RGBColor(imageMatrix[w][h].getRed(),imageMatrix[w][h].getGreen(),imageMatrix[w][h].getBlue());                
            }
        }
        this.imageMatrix=imageMatrix1;
    }
    
    /**
     * finds white pixels and approximates their new color by using the average of neighbour colors
     *
     */
    public void repairPicture(){     
    	for (int w=0; w< this.width; w++){
    		for(int h=0; h< this.height; h++){
    			int r,g,b,cnt;
    			if(this.imageMatrix[w][h].isWhite()){
    				r=0;g=0;b=0;cnt=0;
    				for(int w1=-1; w1<2;w1++){
    					for(int h1=-1; h1<2;h1++){
    						if(w1==0 && h1==0)
    							continue;
    						if(w+w1>this.width-1 || w+w1<0)
    							continue;
    						if(h+h1>this.height-1 || h+h1<0)
    							continue;
    						if(this.imageMatrix[w+w1][h+h1].isWhite())
    							continue;
    						r+=this.imageMatrix[w+w1][h+h1].getRed();
    						g+=this.imageMatrix[w+w1][h+h1].getGreen();
    						b+=this.imageMatrix[w+w1][h+h1].getBlue();
    						cnt++;
    					}
    				}
    				if(cnt!=0){
	    				r/=cnt;g/=cnt;b/=cnt;
	    				this.imageMatrix[w][h].setRGB(r, g, b);	
    				}
    			}
    		}
    	}
    }
        
    /**
     * Creates a completely white picture
     *
     */
    public void createWhitePicture(){
        this.imageMatrix = new RGBColor[this.width][this.height];
        for (int w=0; w< this.width; w++){
            for(int h=0; h< this.height; h++){
                //set this colors in picture
                this.imageMatrix[w][h] = new RGBColor(255, 255, 255);                
            }
        }
    }

    /**
    * DO NOT CHANGE ANYTHING BELOW THIS LINE!
    **/

    // Getters
    
    /**
     * 
     * @return the width of the picture
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * 
     * @return the height of the picture
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * 
     * @return the the picture matrix
     */
    public RGBColor[][] getImageMatrix(){
        return this.imageMatrix;
    }
    
    /**
     * reads an 24-bit(8,8,8) Bitmap and store it into picture-array
     * @param picUrl The url to the pic
     * @return true, if successful else false
     */
    private boolean openAndSetPicture(String picUrl){
         
         BufferedImage pic;
         
         
         try {
             InputStream iS= new FileInputStream(picUrl);
             // get buffer of the picture
             pic = ImageIO.read(iS);    
             
             // get additional picture informations
             this.height = pic.getHeight();
             this.width = pic.getWidth();            
             
             // store rgb colors in picture
             this.imageMatrix = new RGBColor[this.width][this.height];
             ColorModel cm= ColorModel.getRGBdefault();
             for (int w=0; w< this.width; w++){
                 for(int h=0; h< this.height; h++){
                     
                     // read out every RGBcolor
                     int pixel = pic.getRGB(w, h);
                     int rVal= cm.getRed(pixel);
                     int gVal= cm.getGreen(pixel);
                     int bVal= cm.getBlue(pixel);
                     
                     //set this colors in picture
                     this.imageMatrix[w][h] = new RGBColor(rVal, gVal, bVal);                
                 }
             }
             return true;
             
             
         }catch (IOException e) {
             e.printStackTrace();       
         }
         return false;
         
    }
    
    public BufferedImage getImage(){
        BufferedImage buf = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for(int w=0; w<this.width; w++){
            for(int h=0; h<this.height; h++){
                int red= this.imageMatrix[w][h].getRed();
                int blue= this.imageMatrix[w][h].getBlue();
                int green= this.imageMatrix[w][h].getGreen();
                int rgbVal= new Color(red, green, blue).getRGB();
                buf.setRGB(w, h, rgbVal);
            }
    }
    
        return buf;
    }


    
    
}

