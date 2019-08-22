package hw1;

/**
 * 
 * @author Abdullah Stimulates the activity of a driver for a shared- ride
 *         service similar to Uber or Lyft.The UberDriver is configured with a
 *         per-mile rate and a per-minute rate. When driving with one or more
 *         passengers, the driver is credited according to the number of miles
 *         driven and also the number of minutes spent driving, multiplied by
 *         the number of passengers currently in the vehicle. When there are
 *         zero passengers in the vehicle, the driver earns nothing. The money
 *         earned is accumulated in the total credits
 */
public class UberDriver {

	/**
	 * Number of Money earned for the Uber Driver
	 */
	private double credits;
	/**
	 * Number of miles Driven
	 */
	private int milesDriven;
	/**
	 * Number of minutes driven.
	 */
	private int minutesDriven;
	/**
	 * Rate of minutes driven
	 */
	private double minutesRate;
	/**
	 * Rate of miles driven
	 */
	private double milesRate;
	/**
	 * Number of Passengers
	 */
	private int numPassengers;
	/**
	 * Maximum number of passengers allowed in the vehicle at one time.
	 */
	public static final int MAX_PASSENGERS = 4;

	/**
	 * Cost to operate the vehicle per mile.
	 */
	public static final double OPERATING_COST = 0.5;

	/**
	 * Constructs an UberDriver with the given per-mile rate and per-minute rate.
	 * 
	 * @param givenPerMileRate
	 * @param givenPerMinuteRate
	 */

	public UberDriver(double givenPerMileRate, double givenPerMinuteRate) {
		milesRate = givenPerMileRate;
		minutesRate = givenPerMinuteRate;
		credits = 0;
		milesDriven = 0;
		minutesDriven = 0;
		numPassengers = 0;
	}

	/**
	 * 
	 * @return Returns the total miles driven since this UberDriver was constructed
	 */
	public int getTotalMiles() {
		return milesDriven;

	}

	/**
	 * 
	 * @return Returns the total minutes driven since this UberDriver was
	 *         constructed
	 */
	public int getTotalMinutes() {
		return minutesDriven;

	}

	/**
	 * Drives the vehicle for the given number of miles over the given number of
	 * minutes
	 * 
	 * @param miles
	 * @param minutes
	 */
	public void drive(int miles, int minutes) {

		milesDriven = (milesDriven + miles);
		minutesDriven = (minutesDriven + minutes);

		credits = credits + (miles * milesRate) * numPassengers;

		credits = credits + (minutes * minutesRate) * numPassengers;

	}

	/**
	 * Simulates sitting in the vehicle without moving for the given number of
	 * minutes Equivalent to drive(0,minutes).
	 * 
	 * @param minutes
	 */
	public void waitAround(int minutes) {
		minutesDriven = minutesDriven + minutes;
		credits = credits + (minutesDriven * minutesRate) * numPassengers;

	}

	/**
	 * Drives the vehicle for the given number of miles at the given speed.
	 * Equivalent to drive(miles,m), where m is the actual number of minutes
	 * required,rounded to the nearest integer.
	 * 
	 * @param miles
	 * @param averageSpeed
	 */
	public void driveAtSpeed(int miles, double averageSpeed) {
		int temp;
		milesDriven = milesDriven + miles;
		minutesDriven = minutesDriven + ((int) Math.round((miles / averageSpeed) * 60));
		temp = ((int) Math.round((miles / averageSpeed) * 60));

		credits = credits + (miles * milesRate) * numPassengers;

		credits = credits + ((temp * minutesRate) * numPassengers);

	}

	/**
	 * Returns the number of passengers currently in the vehicle
	 * 
	 * @return
	 */
	public int getPassengerCount() {
		return numPassengers;

	}

	/**
	 * Increases the passenger count by 1, not exceeding MAX_PASSENGERS.
	 */
	public void pickUp() {

		numPassengers = numPassengers + 1;
		numPassengers = Math.min(numPassengers, MAX_PASSENGERS);

	}

	/**
	 * Decreases the passenger count by 1, not going below zero.
	 */
	public void dropOff() {
		numPassengers = numPassengers - 1;
		numPassengers = Math.max(numPassengers, 0);
	}

	/**
	 * Returns this UberDriver's total credits(money earned before deducting
	 * operating costs).
	 * 
	 * @return
	 */
	public double getTotalCredits() {

		return credits;

	}

	/**
	 * Returns this UberDriver's profit(total credits, less operating costs).
	 * 
	 * @return
	 */
	public double getProfit() {
		return credits - (OPERATING_COST * milesDriven);

	}

	/**
	 * Returns this UberDriver's average profit per hour worked.
	 * 
	 * @return
	 */
	public double getAverageProfitPerHour() {
		return 60 * (credits - (OPERATING_COST * milesDriven)) / minutesDriven;

	}
}
