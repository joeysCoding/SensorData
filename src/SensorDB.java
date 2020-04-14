import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SensorDB {
    private File dataFile;

    public SensorDB(File dataFile){
        this.dataFile = dataFile;
    }

    public void printAllSensorData() throws IOException {
        InputStream fis = new FileInputStream(this.dataFile);
        DataInputStream dis = new DataInputStream(fis);

        //  System.out.println("Sensor Data");
        // System.out.println("Sensorname    Date    Temperature");

        while (dis.available() > 0){
            SensorDataset currentDS = SensorDataset.getNextDataSet(dis);
            System.out.println(currentDS.toString());
        }
    }

    public List<SensorDataset> getSensorData() throws IOException{
        InputStream fis = new FileInputStream(this.dataFile);
        DataInputStream dis = new DataInputStream(fis);

        List<SensorDataset> sensorDs = new ArrayList<SensorDataset>();
        //  System.out.println("Sensor Data");
        // System.out.println("Sensorname    Date    Temperature");

        while (dis.available() > 0){
            SensorDataset currentDS = SensorDataset.getNextDataSet(dis);
            sensorDs.add(currentDS);
        }

        return sensorDs;
    }

    public void addSensorReading(String sensorName, long date, float temperature) throws IOException {
        SensorDataset sensorDS = new SensorDataset(sensorName, date, temperature);

        OutputStream fos = new FileOutputStream(this.dataFile, true);
        DataOutputStream dos = new DataOutputStream(fos);

        sensorDS.writeDSTo(dos);

        dos.flush();
        dos.close();
        fos.flush();
        fos.close();
    }
}
