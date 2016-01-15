import java.util.ArrayList;
/**
 * Write a description of class Anomaly here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Anomaly
{
    private String type;
    private String[] options; // I'm planning to use this
    private double sensitivity;
    private boolean updated = false;
    private int count = 0;
    private ArrayList<String> anomalies = new ArrayList<String>();
    private static double DEFAULT_SENSITIVITY = 1;

    public Anomaly(String type, double sensitivity, String[] options)
    {
        this.type = type;
        this.options = options;
        this.sensitivity = sensitivity;
    }
    public Anomaly(String type, double sensitivity)
    {
        this.type = type;
        this.options = new String[0];
        this.sensitivity = sensitivity;
    }
    public Anomaly(String type)
    {
        this.type = type;
        this.options = new String[0];
        this.sensitivity = this.DEFAULT_SENSITIVITY;
    }
    public String toString(){
        String ret = "Type: "+this.type+"\nOptions:\n";
        for(String opt : this.options){
            ret += opt+"\n";
        }
        ret += "Sensitivity: "+this.sensitivity;
        return ret;
    }
    public void test(Picture pic){
        
    }
    public int getAnomalyCount(){
        return this.count;
    }
    public String getAnomalyString(){
        String ret = "";
        for(String s : this.anomalies){
            ret += s + "\n";
        }
        int length = ret.length();
        if(length == 0) return "";
        return ret.substring(0, length-1);
    }
}
