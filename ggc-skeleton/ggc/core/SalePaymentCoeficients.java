package ggc.core;

interface SalePaymentCoeficients {

	private static double getNormalCoeficients(int period, int delay){
		if (period == 1)
			return -.1;
		if (period == 2)
			return 0;
		if (period == 3)
			return .05 * delay;
		else // period == 4
			return .1 * delay;
	}

	private static double getSelectionCoeficients(int period, int delay){
		if (period == 1)
			return -.1;
		if (period == 2 && delay <= -2)
			return -.05;
		if (period == 2)
			return 0;
		if (period == 3 && delay <= 1)
			return 0;
		if (period == 3)
			return .02 * delay;
		else // period == 4
			return .05 * delay;
	}

	private static double getEliteCoeficients(int period, int delay){
		if (period == 1)
			return -.1;
		if (period == 2)
			return -.1;
		if (period == 3)
			return -.05;
		else // period == 4
			return 0;
	}

	static double getCoeficient(Product product, Status status, int delay){

		int tolerance;
		int period;

		if (product instanceof SimpleProduct)
            tolerance = 5;
        else
            tolerance = 3;

		if (delay <= tolerance * -1)
            period = 1;
        else if (tolerance * -1 < delay && delay < 0)
            period = 2;
        else if (0 < delay && delay <= tolerance)
            period = 3;
        else // tolerance < delay
            period = 4;

		if (status == Status.NORMAL)
			return getNormalCoeficients(period, delay);
		if (status == Status.SELECTION)
			return getSelectionCoeficients(period, delay);
		else // status.equals("ELITE")
			return getEliteCoeficients(period, delay);
	}
}