# Worksheet 6: Dependencies

## Quesion 1 - Factories

### (a)

Run the file through various readers (Image reader, PDF reader, Markdown reader, etc.) and see which produces a result without error

### (b)

Have a static counter variable stored in the factory class that increments for every new SpaceObject created. As this number is under a certain threshold, only asteriods are created, as it is under a different greater threshold, asteriods and alien1s are created, and past the last threshold all SpaceObjects are created.

## Question 2 - Dependency Injection - Refactoring

```java
public class SecuritySystem implements SensorObserver
{
    private MotionSensor motionSensor;
    private HeatSensor heatSensor
    private Alarm alarm;
    private boolean armed;
    
    public SecuritySystem(Hardware hw)
    {
        SensorBundle sens = hw.getSensors();
        motionSensor = sens.getMotionSensor();
        heatSensor = sens.getHeatSensor();
        motionSensor.addSensorObserver(this);
        heatSensor.addSensorObserver(this);
        alarm = new Alarm();
        armed = false;
    }

    public void setArmed(boolean newArmed)
    {
        armed = newArmed;
        EmailSystem.sendMessage("Armed: " + newArmed);
    }

    @Override
    public void sensorDetection(Sensor s)
    {
        if(armed)
        {
            alarm.ring();
            EmailSystem.sendMessage("Sensor detection for " +
                s.toString());
        }
    }
}
```