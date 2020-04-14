import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class SensorDataset {
    private String sensorName;
    private long date;
    private float temperature;

    public SensorDataset(String sensorName, long date, float temperature) {
        this.sensorName = sensorName;
        this.date = date;
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDataset that = (SensorDataset) o;
        return date == that.date &&
                Float.compare(that.temperature, temperature) == 0 &&
                Objects.equals(sensorName, that.sensorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorName, date, temperature);
    }

    @Override
    public String toString() {
        return "SensorDataset{" +
                "sensorName='" + sensorName + '\'' +
                ", date=" + date +
                ", temperature=" + temperature +
                '}';
    }

    public void writeDSTo(DataOutputStream dos) throws IOException {
        //OutputStream fos = new FileOutputStream(file);
        //DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF(this.sensorName);
        dos.writeLong(this.date);
        dos.writeFloat(this.temperature);

        //dos.flush();
        //dos.close();
        //fos.flush();
        //fos.close();
    }

    public static SensorDataset getNextDataSet(DataInputStream dis) throws IOException {
        String currentSensorName = dis.readUTF();
        long currentDate = dis.readLong();
        float currentTemperature = dis.readFloat();

        return new SensorDataset(currentSensorName, currentDate, currentTemperature);
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
