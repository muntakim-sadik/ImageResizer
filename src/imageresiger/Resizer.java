/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageresiger;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author paagol
 */
public class Resizer implements Runnable {
    ImageResigerView parent;
    String path;
    Resizer(ImageResigerView x,String inpath,String outpath){
        parent=x;
        path=inpath;
          ImageResizer.outputPath  = outpath;
    }
    public  void run() 
     {
          //String path = "C:\\Users\\Naimul Arif\\Desktop\\input";  // Directory path here
          
          String files;
          File folder = new File(path);
          File[] listOfFiles = folder.listFiles();
int cnt=0;
parent.setTotal(listOfFiles.length);
          for (int i = 0; i < listOfFiles.length; i++)
          {
              String imageToResize=path+"\\";

               if (listOfFiles[i].isFile())
               {
                   files = listOfFiles[i].getName();
                   imageToResize=imageToResize+files; //path+imagename is found

                   Thread t=new Thread(new ImageResizer(imageToResize,files,parent  ));
                   
                   t.start();
                   
                   cnt++;
                   if(cnt==5){
                    try {
                        t.join();
                        cnt=0;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(Resizer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
               }
          }
          parent.showMsg();
    }
}
