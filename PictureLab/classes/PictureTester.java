/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void explore(String url) throws java.io.IOException
  {
    Picture pic = new Picture(new java.net.URL(url));
    pic.explore();
  }
  public static void explore() throws java.io.IOException
  {
    Picture pic = new Picture(FileChooser.pickAFile());
    pic.explore();
  }
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  public static void testMirrorVerticalRightToLeft()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  public static void testScale()
  {
    Picture caterpillar = new Picture(FileChooser.pickAFile());
    caterpillar.explore();
    System.out.println("Size: ");
    java.util.Scanner s = new java.util.Scanner(System.in);
    caterpillar = caterpillar.scaledTo(s.nextInt(), s.nextInt());
    caterpillar.explore();
  }
  /** Method to test mirrorVertical */
  public static void testMirrorVerticalLeftToRight()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalLeftToRight();
    caterpillar.explore();
  }
  public static void testMirrorHorizontalTopToBottom()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalTopToBottom();
    caterpillar.explore();
  }
  public static void testMirrorTopToBottomAndFlip()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorTopToBottomAndFlip();
    caterpillar.explore();
  }
  public static void testMirrorDiagonalULtoLRandFlip()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorDiagonalULtoLRandFlip();
    caterpillar.explore();
  }
  public static void testMirrorURtoLL()
  {
    Picture caterpillar = new Picture("swan.jpg");
    caterpillar.explore();
    caterpillar.mirrorURtoLL();
    caterpillar.explore();
  }
  public static void testMirrorHorizontalBottomToTop()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalBottomToTop();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture(1000, 1000);
    canvas.createCollage();
    canvas.explore();
  }
  public static void testGrayscale()
  {
    Picture canvas = new Picture(FileChooser.pickAFile());
    canvas.grayscale();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  public static void testAnomalyDetection(String anomaly, String url) throws java.io.IOException
  {
    Anomaly a = new Anomaly(anomaly);
    Picture pic = new Picture(new java.net.URL(url));
    a.test(pic);
    System.out.println("Anomaly count: "+a.getAnomalyCount());
    System.out.println("Anomalies found:\n"+a.getAnomalyString());
    pic.explore();
  }
  
  public static void testCropAndCopy()
  {
    Picture canvas = new Picture("640x480.jpg");
    Picture source = new Picture(FileChooser.pickAFile());
    canvas.cropAndCopy(source, 0, 0, 150, 100, 200, 150);
    canvas.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    testGrayscale();
    //testFixUnderwater();
    //testMirrorVerticalLeftToRight();
    testMirrorVerticalRightToLeft();
    testMirrorHorizontalBottomToTop();
    testMirrorHorizontalTopToBottom();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}