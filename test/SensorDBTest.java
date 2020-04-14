import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class SensorDBTest {
    private File dataDirectory = new File("dataDir");
    private String dataFilename = "data.txt";
    private File dataFile = new File(dataDirectory.getPath() + File.separator + dataFilename);
    private long globalDate = System.currentTimeMillis();
    @Before
    public void cleanDataFiles(){
        for(File file: dataDirectory.listFiles()){
            file.delete();
        }
    }

    @Test
    public void addSensorReading() {
        SensorDB sensorDB = new SensorDB(dataFile);
        SensorDataset ds1 = new SensorDataset("Sensor1", globalDate, 23f);
        SensorDataset ds2 = new SensorDataset("Sensor1", globalDate, 24f);
        SensorDataset ds3 = new SensorDataset("Sensor1", globalDate, 25f);

        List<SensorDataset> expecteds = new ArrayList<SensorDataset>();
        expecteds.add(ds1);
        expecteds.add(ds2);
        expecteds.add(ds3);


        try {
            sensorDB.addSensorReading("Sensor1", globalDate, 23f);
            sensorDB.addSensorReading("Sensor1", globalDate, 24f);
            sensorDB.addSensorReading("Sensor1", globalDate, 25f);
            List<SensorDataset> actuals = sensorDB.getSensorData();
            assertArrayEquals(expecteds.toArray(), actuals.toArray());
            sensorDB.printAllSensorData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}