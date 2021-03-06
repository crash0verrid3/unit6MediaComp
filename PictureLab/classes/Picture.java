import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  public Picture(java.net.URL fileUrl) throws java.io.IOException
  {
    // let the parent class handle this fileName
    super(javax.imageio.ImageIO.read(fileUrl));
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  public Picture cropped(int startRow, int startCol, int rows, int cols){
      Picture ret = new Picture(rows, cols);
      Pixel[][] pix = this.getPixels2D();
      Pixel[][] retPix = ret.getPixels2D();
      for(int a=0; a<rows; a++){
          for(int b=0; b<cols; b++){
              retPix[a][b].setColor(pix[a+startRow][b+startCol].getColor());
            }
        }
      return ret;
    }
  public void cropAndCopy(Picture source, int startRow, int startCol, int rows, int cols, int endRow, int endCol){
      this.copy(source.cropped(startRow, startCol, rows, cols), endRow, endCol);
    }
  public Picture scaled(double divideHeight, double divideWidth){
      Pixel[][] pix = this.getPixels2D();
      Picture ret = new Picture((int)(pix.length/divideHeight), (int)(pix[0].length/divideWidth));
      Pixel[][] retPix = ret.getPixels2D();
      for(int a=0; a<retPix.length; a++){
          for(int b=0; b<retPix[0].length; b++){
              retPix[a][b].setColor(pix[(int)(a*divideHeight)][(int)(b*divideWidth)].getColor());
            }
        }
      return ret;
    }
  public Picture scaled(double divideBy){
      return scale(divideBy, divideBy);
    }
  public Picture scaledTo(int width, int height){
      Pixel[][] pix = this.getPixels2D();
      double dH = pix.length / (double)height;
      double dW = pix[0].length / (double)width;
      pix = null;
      return scaled(dH, dW);
    }
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    Picture grayCater = new Picture("caterpillar.jpg");
    grayCater.grayscale();
    this.copy(grayCater, 640, 400);
    Picture robot = new Picture("robot.jpg");
    robot.edgeDetection(1);
    this.copy(robot.scaledTo(50, 50), 50, 50);
    Picture snowmanPoorEdgeDetection = new Picture("snowman.jpg");
    snowmanPoorEdgeDetection = snowmanPoorEdgeDetection.scale(2.5, 2.5);
    snowmanPoorEdgeDetection.edgeDetection(5);
    this.copy(snowmanPoorEdgeDetection, 80, 600);
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue.scale(3, 2.5),300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    //this.mirrorVertical();
    this.write("collage.jpg");
  }
 
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        Color leftColor = leftPixel.getColor();
        if (leftColor.equals(Color.BLACK)){
            boolean[] count_data = new boolean[0];
            try{
                count_data = new boolean[]{pixels[row][col+1].getColor().equals(Color.BLACK), pixels[row][col-1].getColor().equals(Color.BLACK), pixels[row+1][col].getColor().equals(Color.BLACK), pixels[row-1][col].getColor().equals(Color.BLACK)};
            } catch(java.lang.ArrayIndexOutOfBoundsException e){
                // Ignore
            }
                int count = 0;
          for(boolean b : count_data){
              if(b){
                  count ++;
                }
            }
          if(count >= 2){
              pixels[row][col].setColor(Color.BLACK);
            } else{
              pixels[row][col].setColor(Color.WHITE);
            }
        } else
          pixels[row][col].setColor(Color.WHITE);
      }
    }
  }
  public void mirrorVerticalRightToLeft(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=0; row<height; row++){
          for(int col=width-1; col > width / 2; col--){
              pixels[row][width-1-col].setColor(pixels[row][col].getColor());
            }
        }
    }
  public void mirrorVerticalLeftToRight(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=0; row<height; row++){
          for(int col=0; col<width / 2; col++){
              pixels[row][width-1-col].setColor(pixels[row][col].getColor());
            }
        }
    }
  public void grayscale(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=0; row<height; row++){
          for(int col=0; col<width; col++){
              Color color = pixels[row][col].getColor();
              int[] rgb = new int[]{color.getRed(), color.getGreen(), color.getBlue()};
              int average = 0;
              for(int a : rgb){
                  average += a;
                }
              average /= 3;
              pixels[row][col].setColor(new Color(average, average, average, color.getAlpha()));
            }
        }
    }
    
  public void mirrorHorizontalBottomToTop(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=height-1; row>height/2; row--){
          for(int col=0; col < width; col++){
              pixels[height-1-row][col].setColor(pixels[row][col].getColor());
            }
        }
    }
  public void mirrorHorizontalTopToBottom(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=0; row<height/2; row++){
          for(int col=0; col < width; col++){
              pixels[height-1-row][col].setColor(pixels[row][col].getColor());
            }
        }
    }
    
    public void mirrorPartLeftToRight(int x1, int y1, int x2, int y2, int mirrorPoint) // Untested
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        // loop through the rows
        for (int row = y1; row < y2; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = x1; col < mirrorPoint; col++)
            {
                leftPixel = pixels[row][col];
                if(mirrorPoint - col + mirrorPoint <= x2){
                    rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                    rightPixel.setColor(leftPixel.getColor());
                }
            }
        }
    }
    
  public void mirrorDiagonalULtoLRandFlip(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=0; row<height; row++){
          for(int col=0; col < width; col++){
              if(! (row + col >= height/2 + width/2)){
                  pixels[height-1-row][width-1-col].setColor(pixels[row][col].getColor());
                }
            }
        }
    }
  public void mirrorURtoLL(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=0; row<height; row++){
          for(int col=0; col < width; col++){
              if(row < width && col < height){
                  pixels[col][row].setColor(pixels[row][col].getColor());
                }
            }
        }
    }
  public void mirrorTopToBottomAndFlip(){
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
      for(int row=0; row<height/2; row++){
          for(int col=0; col < width; col++){
              pixels[height-1-row][width-1-col].setColor(pixels[row][col].getColor());
            }
        }
    }
  public Color getColorAt(int row, int col){
      Pixel[][] pixels = this.getPixels2D();
      return pixels[row][col].getColor();
    }
  public int rows(){
      return this.getPixels2D().length;
    }
  public int cols(){
      return this.getPixels2D()[0].length;
    }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args)
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
