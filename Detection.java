import java.awt.*;  
import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;  
import java.io.IOException;  

import javax.imageio.ImageIO;  
import javax.swing.*;  

import org.opencv.core.Core;  
import org.opencv.core.Mat;  
import org.opencv.core.MatOfByte;  
import org.opencv.core.MatOfRect;  
import org.opencv.core.Point;  
import org.opencv.core.Rect;  
import org.opencv.core.Scalar;  
import org.opencv.core.Size;  
import org.opencv.highgui.Highgui;  
import org.opencv.highgui.VideoCapture;  
import org.opencv.imgproc.Imgproc;  
import org.opencv.objdetect.CascadeClassifier;  

class FacePanel extends JPanel{  
     private static final long serialVersionUID = 1L;  
     private BufferedImage image;  
     // Create a constructor method  
     public FacePanel(){  
          super();   
     }  
     /*  
      * Converts/writes a Mat into a BufferedImage.  
      *   
      * @param matrix Mat of type CV_8UC3 or CV_8UC1  
      * @return BufferedImage of type TYPE_3BYTE_BGR or TYPE_BYTE_GRAY  
      */       
     public boolean matToBufferedImage(Mat matrix) {  
          MatOfByte mb=new MatOfByte();  
          Highgui.imencode(".jpg", matrix, mb);  
          try {  
               this.image = ImageIO.read(new ByteArrayInputStream(mb.toArray()));  
          } catch (IOException e) {  
               e.printStackTrace();  
               return false; // Error  
          }  
       return true; // Successful  
     }  
     public void paintComponent(Graphics g){  
          super.paintComponent(g);   
          if (this.image==null) return;         
           g.drawImage(this.image,10,10,this.image.getWidth(),this.image.getHeight(), null);
     }
        
}  
class FaceDetector {  
     private CascadeClassifier face_cascade; 
     CascadeClassifier mouthCascade;
     CascadeClassifier eyesCascade;
     // Create a constructor method  
     public FaceDetector(){  
         // face_cascade=new CascadeClassifier("./cascades/lbpcascade_frontalface_alt.xml");  
         //..didn't have not much luck with the lbp
         
        face_cascade=new CascadeClassifier("C://Users//Huniya//Downloads//opencv//sources//samples//winrt//FaceDetection//FaceDetection//Assets//haarcascade_frontalface_alt.xml"); 
        eyesCascade = new CascadeClassifier("C://Users//Huniya//Downloads//opencv//sources//data//haarcascades_cuda//haarcascade_eye_tree_eyeglasses.xml");
        mouthCascade= new CascadeClassifier("C://Users//Huniya//Downloads//opencv//sources//data//haarcascades//Mouth.xml");

        if (face_cascade.empty()) {
            System.out.println("Face cascade failed to load.");
            return;
        } else {
            System.out.println("Face cascade loaded successfully.");
        }

        if (eyesCascade.empty()) {
            System.out.println("Eyes cascade failed to load.");
        } else {
            System.out.println("Eyes cascade loaded successfully.");
        }

        if (mouthCascade.empty()) {
            System.out.println("Mouth cascade failed to load.");
        } else {
            System.out.println("Mouth cascade loaded successfully.");
        }
  
     }  
     public Mat detect(Mat inputframe){  
          Mat mRgba=new Mat();  
          Mat mGrey=new Mat();  
         
          
          MatOfRect faces = new MatOfRect();  
          inputframe.copyTo(mRgba);  
          inputframe.copyTo(mGrey);  
          Imgproc.cvtColor( mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);  
          Imgproc.equalizeHist( mGrey, mGrey );  
          face_cascade.detectMultiScale(mGrey, faces);  
          System.out.println(String.format("Detected %s faces", faces.toArray().length));  
          int i=0;
          for(Rect rect:faces.toArray())  
          { 
        	  Core.rectangle(mRgba,  //where to draw the box
        			    new Point(rect.x, rect.y),   //bottom left
        			    new Point(rect.x + rect.width, rect.y + rect.height), //top right 
        			    new Scalar(255, 0, 0));
        	  
        	  Mat faceROI = mRgba.submat(rect);
              MatOfRect eyes = new MatOfRect();

              //-- In each face, detect eyes
            //  eyes_cascade.detectMultiScale( faceROI, eyes, 1.1, 2, 0 |CASCADE_SCALE_IMAGE, Size(30, 30) );
              eyesCascade.detectMultiScale(faceROI, eyes,1.1,2,0,new Size(30,30) ,new Size());            
             // Rect[] facesArray = faces.toArray();
             // Rect[] eyesArray = eyes.toArray();
              System.out.println(String.format("Detected %s eyes", eyes.toArray().length));
              for (Rect rect1:eyes.toArray())
              {
            	 
                 Point center1 = new Point(rect.x + rect1.x + rect1.width * 0.5, rect.y + rect1.y + rect1.height * 0.5);
                 int radius = (int) Math.round((rect1.width + rect1.height) * 0.25);
                 Core.circle(mRgba, center1, radius, new Scalar(255, 0, 0), 4, 8, 0);
              }
               /*Point center= new Point(rect.x + rect.width*0.5, rect.y + rect.height*0.5 );  
               //draw a blue eclipse around face
               Size s = new Size( rect.width*0.5 , rect.height*0.5);
                Core.ellipse( mRgba, center,s , 4, 8, 0, new Scalar( 0, 0, 255 ) ); 
                 
          */	i++;
          
          
          MatOfRect mouth = new MatOfRect();
          
          Mat mRgba1=new Mat();  
          Mat mGrey1=new Mat(); 
        
          inputframe.copyTo(mRgba1);  
          inputframe.copyTo(mGrey1);  
          Imgproc.cvtColor( mRgba1, mGrey1, Imgproc.COLOR_BGR2GRAY);  
          Imgproc.equalizeHist( mGrey1, mGrey1 );  
            
          
          rect.height = (int) Math.round(rect.height * 0.5);
          rect.y = rect.y + rect.height;
          mouthCascade.detectMultiScale(faceROI, mouth, 1.1, 2, 0, new Size(30, 30), new Size());
       //   mouthCascade.detectMultiScale(faceROI, mouth, 1.1, 2, 0, new Size(30, 30), new Size());
          System.out.println(String.format("Detected %s mouth", mouth.toArray().length));
          Rect[] mouthArray = mouth.toArray();
         
     
          for (Rect mouth1:mouth.toArray()) {
        	 
        		  Point centre3 = new Point(rect.x + mouth1.x + mouth1.width ,
        		                    rect.y + mouth1.y + mouth1.height);
        		          Core.ellipse(mRgba, centre3, new Size(mouth1.width * 0.5,mouth1.height * 0.5), 0, 0, 360,new Scalar(255, 112,43), 4, 8, 0);
				
				
          }
          
              }  
          
          
          return mRgba;  
     }  
}  
public class Main {  
    
	public static void main(String arg[]) throws InterruptedException{  
      // Load the native library.  
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
      //or ...     System.loadLibrary("opencv_java244");       

      //make the JFrame
      JFrame frame = new JFrame("WebCam Capture - Face detection");  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
     
      FaceDetector faceDetector=new FaceDetector();  
      FacePanel facePanel = new FacePanel();  
      frame.setSize(400,400); //give the frame some arbitrary size 
      frame.setBackground(Color.BLUE);
      frame.add(facePanel,BorderLayout.CENTER);       
      frame.setVisible(true);       
      
      //Open and Read from the video stream  
       Mat webcam_image=new Mat();  
       VideoCapture webCam =new VideoCapture(0);   
   
        if( webCam.isOpened())  
          {  
           Thread.sleep(500); /// This one-time delay allows the Webcam to initialize itself  
           while( true )  
           {  
        	 webCam.read(webcam_image);  
             if( !webcam_image.empty() )  
              {   
            	  Thread.sleep(200); /// This delay eases the computational load .. with little performance leakage
                   frame.setSize(webcam_image.width()+40,webcam_image.height()+60);  
                   //Apply the classifier to the captured image  
                   webcam_image=faceDetector.detect(webcam_image);  
                  //Display the image  
                   facePanel.matToBufferedImage(webcam_image);  
                   facePanel.repaint();   
              }  
              else  
              {   
                   System.out.println(" --(!) No captured frame from webcam !");   
                   break;   
              }  
             }  
            }
           webCam.release(); //release the webcam
 
      } //end main 
	
}