package imageresiger;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageResizer implements Runnable{

        int customHeight,customWidth,originalHeight,originalWidth;
        static int mxHeight,mxWeidth;
        static String outputPath;
        String outputPath1;
        String file, fileName;
        ImageResigerView parent;
        
        
        ImageResizer(String f, String ff,ImageResigerView p){
            file =f;
            fileName=ff;
            parent = p;
        }
	public void run()
        {
            outputPath1 =outputPath+"\\"+fileName;
//System.out.println(outputPath1);

            try
            {

                    BufferedImage originalImage = ImageIO.read(new File(file));

                    originalHeight=originalImage.getHeight();
                    originalWidth=originalImage.getWidth();
                    set(originalWidth,originalHeight);
                  //System.out.println(customHeight+ "  "+customWidth);

                    int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                    BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                    ImageIO.write(resizeImageJpg, "jpg", new File(outputPath1));
            }
            catch(IOException e)
            {
                   e.printStackTrace();
            }
parent.increaseLabel();
        }
               
               void set(int a,int b){
                   
                     //  System.out.println("hic>>"+a+" "+b);
                   if(b>mxHeight){
                       int t=b;
                       b=mxHeight;
                       a = (a*mxHeight)/t;
                       set(a,b);
                   }else if(a>mxWeidth){
                       int t=a;
                       a=mxWeidth;
                       b=(b*mxWeidth)/t;
                       set(a,b);
                   }else{
                       customHeight = b;
                       customWidth=a;
                   }
                   
               }

    public BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(customWidth, customHeight, type);//image is created
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, customWidth, customHeight, null); //original image is drawn the imagefile
	g.dispose();

	return resizedImage;
    }

    
}
