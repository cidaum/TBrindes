/**
 * Created by felipe on 01/02/14.
 */

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LiberaBrinde {

    private static LiberaBrinde instance;
    private final GpioController gpio = GpioFactory.getInstance();

    // provision gpio pin #01 as an output pin and turn on
    private final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);

    private LiberaBrinde() {

    }

    public static synchronized LiberaBrinde getInstance() {
        if (instance == null) {
            instance = new LiberaBrinde();
        }
        return instance;
    }

    public void liberar() {
        pin.pulse(1,true);
        System.out.println("Brinde liberado");
    }
}
