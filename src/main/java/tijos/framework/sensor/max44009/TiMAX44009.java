package tijos.framework.sensor.max44009;

import java.io.IOException;

import tijos.framework.devicecenter.TiI2CMaster;

/**
 * MAX44009 ambient light sensor features driver for TiJOS  
 * @author TiJOS
 *
 */
public class TiMAX44009 {
	private TiI2CMaster i2cMaster;
	/**
	 * REG Address
	 */
	public final static int LUX_HIGH_REG = 0x03;
	public final static int LUX_LOW_REG = 0x04;
	/**
	 * Default Address
	 */
	private int i2cAddress = 0x4A;

	/**
	 * Initialize with i2c master and default address 0x4a
	 * 
	 * @param i2c
	 */
	public TiMAX44009(TiI2CMaster i2c) {
		this(i2c, 0x4A);
	}

	/**
	 * initialize with i2c master and address
	 * 
	 * @param i2c
	 * @param addr
	 */
	public TiMAX44009(TiI2CMaster i2c, int addr) {
		this.i2cMaster = i2c;
		this.i2cAddress = addr;
	}

	/**
	 * 
	 * Read light level from sensor
	 * 
	 * @return Light level in lux (0 ~ 187,269)
	 * @throws IOException
	 */
	public double readLightLevel() throws IOException {

		byte[] date = new byte[2];
		int Mantissa, Exponent;
		double lux = 0;

		// read high register
		this.i2cMaster.read(this.i2cAddress, LUX_HIGH_REG, date, 0, 1);

		Exponent = (date[0] & 0xf0) >> 4;

		// read low register
		this.i2cMaster.read(this.i2cAddress, LUX_LOW_REG, date, 1, 1);

		// tail value
		Mantissa = (date[0] & 0xf0) << 4 | (date[1] & 0x0f);

		// lux = 2 (exponent) x tail x 0.045
		lux = Math.pow(2, Exponent) * Mantissa * 0.045;

		return lux;

	}

}
