package ggc.core;

interface SalePaymentCoeficients {

	private float getNormalCoeficients(int period, int delay){
		if (period == 1)
			return -.1;
		if (period == 2)
			return 0;
		if (period == 3)
			return .05 * delay;
		if (period == 4)
			return .1 * delay;
	}

	private float getSelectionCoeficients(int period, int delay){
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
		if (period == 4)
			return .05 * delay;
	}

	private float getEliteCoeficients(int period, int delay){
		if (period == 1)
			return -.1;
		if (period == 2)
			return -.1;
		if (period == 3)
			return -.05;
		if (period == 4)
			return 0;
	}

	static float getCoeficient(Product product, String status, int delay){

		int tolerance;
		int period;

		if (product instanceof SimpleProduct)
            tolerance = 5;
        else
            tolerance = 3;

		if (delay <= tolerance * -1)
            period = 1;
        else if (tolerance * -1 < delay <= 0)
            period = 2;
        else if (0 < delay <= tolerance)
            period = 3;
        else if (tolerance < delay)
            period = 4;

		if (status.equals("NORMAL"))
			return getNormalCoeficients(period, delay);
		if (status.equals("SELECTION"))
			return getSelectionCoeficients(period, delay);
		if (status.equals("ELITE"))
			return getEliteCoeficients(period, delay);
	}
}