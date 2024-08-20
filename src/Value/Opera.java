package Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Opera {
    public static double add(double a, double b) {
        BigDecimal a1a= new BigDecimal(a);
        BigDecimal b1b= new BigDecimal(b);
        return a1a.add(b1b).doubleValue();
    }
    public static double sub(double a, double b) {
        BigDecimal a1a= new BigDecimal(a);
        BigDecimal b1b= new BigDecimal(b);
        return a1a.subtract(b1b).doubleValue();
    }
    public static double mul(double a, double b) {
        BigDecimal a1a= new BigDecimal(a);
        BigDecimal b1b= new BigDecimal(b);
        return a1a.multiply(b1b).doubleValue();
    }
    public static double div(double a, double b) {
        BigDecimal a1a= new BigDecimal(a);
        BigDecimal b1b= new BigDecimal(b);
        return a1a.divide(b1b,2, RoundingMode.HALF_UP).doubleValue();
    }

}
