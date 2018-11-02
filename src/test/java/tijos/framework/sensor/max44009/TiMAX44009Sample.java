
package tijos.framework.sensor.max44009;

import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;
import tijos.framework.sensor.max44009.TiMAX44009;
import tijos.framework.util.Delay;

/**
 * Sample for MAX44009
 * 
 * @author TiJOS
 *
 */
public class TiMAX44009Sample {

	public static void main(String[] args) {
		try {
			// Open I2C 0
			TiI2CMaster i2c0 = TiI2CMaster.open(0);

			TiMAX44009 max44009 = new TiMAX44009(i2c0);

			while (true) {

				// read light level
				double lux = max44009.readLightLevel();

				System.out.println("LUX:" + lux);

				Delay.msDelay(5000);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
