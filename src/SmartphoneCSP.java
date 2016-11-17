import model.NormalizedValue;
import model.OS;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;

public class SmartphoneCSP {

	public static List<Smartphone> createSomeSmartphoneObjects() {
		List<Smartphone> smartphones = new ArrayList<>();

		int[] pricePercentile = {399, 599, 799, 899, 1099};
		double[] displaySizePercentile = {4.0, 4.5, 5.0, 5.5, 6.0};
		int[] displayResolutionPercentile = {250, 300, 350, 400, 450};
		double[] ramPercentile = {1.0, 2.0, 3.0, 4.0, 5.0};
		int[] storagePercentile = {16, 32, 64, 128, 256};
		int[] batterySizePercentile = {1500, 2250, 3000, 3750, 4500};
		int[] batteryEndurancePercentile = {55, 60, 65, 70, 75};
		int[] backCameraSensorPercentile = {5, 8, 10, 12, 16};
		double[] frontCameraSensorPercentile = {1.3, 2.1, 5.0, 8.0, 8.7};

		Smartphone iPhone4 = new Smartphone()
				.withName("iPhone 4")
				.withOperatingSystem(OS.APPLE)
				.withPrice(convertFromWithInt(40, pricePercentile))
				.withDisplaySize(convertFromWithDouble(3.5, displaySizePercentile))
				.withDisplayResolution(convertFromWithInt(163, displayResolutionPercentile))
				.withRam(convertFromWithDouble(0.256, ramPercentile))
				.withStorage(convertFromWithInt(16, storagePercentile))
				.withBatterySize(convertFromWithInt(1420, batterySizePercentile))
				.withBackCameraSensor(convertFromWithInt(5, backCameraSensorPercentile))
				.withFrontCameraSensor(convertFromWithDouble(1.3, frontCameraSensorPercentile));

		smartphones.add(iPhone4);

		return smartphones;
	}

	public static NormalizedValue convertFromWithInt(int value, int[] valuePercentile) {
		if(value <= valuePercentile[0]) {
			return NormalizedValue.ONE;
		} else if (value <= valuePercentile[1]) {
			return NormalizedValue.TWO;

		} else if (value <= valuePercentile[2]) {
			return NormalizedValue.THREE;

		} else if (value <= valuePercentile[3]) {
			return NormalizedValue.FOUR;
		} else {
			return NormalizedValue.FIVE;
		}
	}

	public static NormalizedValue convertFromWithDouble(double value, double[] valuePercentile) {
		if(value <= valuePercentile[0]) {
			return NormalizedValue.ONE;
		} else if (value <= valuePercentile[1]) {
			return NormalizedValue.TWO;

		} else if (value <= valuePercentile[2]) {
			return NormalizedValue.THREE;

		} else if (value <= valuePercentile[3]) {
			return NormalizedValue.FOUR;
		} else {
			return NormalizedValue.FIVE;
		}
	}

	public static void main(String[] args) {

		List<Smartphone> smartphones = createSomeSmartphoneObjects();

		//TODO: work on the list of smartphones
	}
}
