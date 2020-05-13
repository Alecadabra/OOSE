# Worksheet 6: Dependencies

## Quesion 1 - Factories

### (a)

Run the file through various readers (Image reader, PDF reader, Markdown reader, etc.) and see which produces a result without error

### (b)

Have a static counter variable stored in the factory class that increments for every new SpaceObject created. As this number is under a certain threshold, only asteriods are created, as it is under a different greater threshold, asteriods and alien1s are created, and past the last threshold all SpaceObjects are created.

## Question 2 - Dependency Injection - Refactoring

### (a)

Before Dependency Injection Pattern:

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

After refactoring:

```java
public class SecuritySystem implements SensorObserver
{
    private SensorBundle sens;
    private MotionSensor motionSensor;
    private HeatSensor heatSensor;
    private Alarm alarm;
    private boolean armed;
    EmailSystem email;
    
    public SecuritySystem(SensorBundle sens, MotionSensor motionSensor,
        HeatSensor heatSensor, Alarm alarm, EmailSystem email)
    {
        this.sens = sens;
        this.motionSensor = motionSensor;
        this.heatSensor = heatSensor;
        this.alarm = alarm;
        this.armed = false;
        this.email = email;

        motionSensor.addSensorObserver(this);
        heatSensor.addSensorObserver(this);
    }

    public void setArmed(boolean newArmed)
    {
        this.armed = newArmed;
        this.email.sendMessage("Armed: " + newArmed);
    }

    @Override
    public void sensorDetection(Sensor s)
    {
        if(armed)
        {
            this.alarm.ring();
            this.email.sendMessage("Sensor detection for " +
                s.toString());
        }
    }
}
```

Other classes to refactor:

EmailSystem use non-static methods.

### (b)

Example of injector code:

```java
SecuritySystem sec;
Hardware hw = new Hardware(...);
SensorBundle sens = hw.getSensors();
MotionSensor motionSensor = sens.getMotionSensor();
HeatSensor heatSensor = sens.getHeatSensor();
Alarm alarm = new Alarm;
EmailSystem email = new EmailSystem(...);

sec = new SecuritySystem(sens, motionSensor, heatSensor, alarm, email);
```