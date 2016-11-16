import org.junit.* ;
import static org.junit.Assert.* ;


public class PictureUnitTest {
    @SuppressWarnings("unused")
	private double delta = 1e-5;
    public final static int WW=2;
    public final static int HH=4;
    public final static int XW=0;
    public final static int XH=1;
    
    @Test(timeout=1000)
    public void testRepairPicture(){
        RGBColor[][] imageMatrix = new RGBColor[WW][HH];
        for (int w=0; w<WW; w++){
            for (int h=0; h<HH; h++){
                if ((w==XW) & (h==XH)){
                    imageMatrix[w][h] = new RGBColor(255,255,255);
                } else {
                    imageMatrix[w][h] = new RGBColor(0,0,0);
                }
            }
        }
        Picture p = new Picture(imageMatrix);
        p.repairPicture();
        assertTrue("The white pixel was not repaired", !(p.getImageMatrix()[XW][XH].isWhite()));
    }

    @Test(timeout=1000)
    public void testRot90DegRight(){
        RGBColor[][] imageMatrix = new RGBColor[WW][HH];
        for (int w=0; w<WW; w++){
            for (int h=0; h<HH; h++){
                if ((w==XW) & (h==XH)){
                    imageMatrix[w][h] = new RGBColor(255,255,255);
                } else {
                    imageMatrix[w][h] = new RGBColor(0,0,0);
                }
            }
        }
        Picture p = new Picture(imageMatrix);
        System.out.println("XW:"+XW);
        System.out.println("XH:"+XH);
        System.out.println("Before:");
        System.out.println("w:"+p.getWidth());
        System.out.println("h:"+p.getHeight());
        p.rot90DegRight();
        System.out.println("XW:"+(HH-XH-1));
        System.out.println("XH:"+XW);
 
        System.out.println("After:");
        System.out.println("w:"+p.getWidth());
        System.out.println("h:"+p.getHeight());
        assertTrue("The white pixel was not rotated", !(p.getImageMatrix()[XW][XH].isWhite()));
        assertTrue("The white pixel was not rotated", (p.getImageMatrix()[HH-XH-1][XW].isWhite()));

    }


    @Test(timeout=1000)
    public void testRot180DegRight(){
        RGBColor[][] imageMatrix = new RGBColor[WW][HH];
        for (int w=0; w<WW; w++){
            for (int h=0; h<HH; h++){
                if ((w==XW) & (h==XH)){
                    imageMatrix[w][h] = new RGBColor(255,255,255);
                } else {
                    imageMatrix[w][h] = new RGBColor(0,0,0);
                }
            }
        }
        Picture p = new Picture(imageMatrix);
        p.rot180Deg();
        assertTrue("The white pixel was not rotated", !(p.getImageMatrix()[XW][XH].isWhite()));
        assertTrue("The white pixel was not rotated", (p.getImageMatrix()[WW-XW-1][HH-XH-1].isWhite()));

    }

}

