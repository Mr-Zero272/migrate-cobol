package com.group_imposter.migrate.util.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * This class is only used for intermediate truncate calculation.<br/>
 * This class provide 4 common operation (add, subtract, multiply, divide) and
 * round operation<br/>
 * After execute intermediate calculation, you need to get result as primitive
 * data type by using functions: intValue, floatValue, bigDecimalValue... <br/>
 * For example: calculate a = b * c + d (using intermediate truncate
 * calculation)<br/>
 * a: type = float, number of decimal part = 4 <br/>
 * b: type = int<br/>
 * c: type = BigDecimal, number of decimal part = 5<br/>
 * d: type = double, number of decimal part = 3<br/>
 * Source code will be:<br/>
 * a = new fDecimal(b,0).multiply(c,5).add(d,3).floatValue();
 * 
 * @author QuyetNV2
 *
 */
public class fDecimal {
    private final static Long LONG_ZERO = Long.valueOf(0L);
    private BigDecimal originObject;
    private int lengthAfterPoint = 0;

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(BigDecimal originObject, int lengthAfterPoint) {
        this.originObject = originObject;
        this.lengthAfterPoint = lengthAfterPoint;
    }

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(BigInteger originObject, int lengthAfterPoint) {
        this(new BigDecimal(originObject), lengthAfterPoint);
    }

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(double originObject, int lengthAfterPoint) {
        this(new BigDecimal(String.valueOf(originObject)), lengthAfterPoint);
    }

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(float originObject, int lengthAfterPoint) {
        this(new BigDecimal(String.valueOf(originObject)), lengthAfterPoint);
    }

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(int originObject, int lengthAfterPoint) {
        this(new BigDecimal(originObject), lengthAfterPoint);
    }

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(long originObject, int lengthAfterPoint) {
        this(new BigDecimal(originObject), lengthAfterPoint);
    }

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(short originObject, int lengthAfterPoint) {
        this(new BigDecimal(originObject), lengthAfterPoint);
    }

    /**
     * Constructor
     * 
     * @param originObject
     *            value of object that need to calculate
     * @param lengthAfterPoint
     *            number of decimals part (number of digits after point)
     */
    public fDecimal(String originObject, int lengthAfterPoint) {
        this(new BigDecimal(originObject), lengthAfterPoint);
    }

    /**
     * Get number of decimals part
     * 
     * @return
     */
    public int getLengthAfterPoint() {
        return lengthAfterPoint;
    }

    /**
     * Set number of decimals part
     * 
     * @param lengthAfterPoint
     */
    public void setLengthAfterPoint(int lengthAfterPoint) {
        this.lengthAfterPoint = lengthAfterPoint;
    }

    /**
     * Round
     * 
     * @param dround
     *            number of decimals part to round
     * @return value after round
     */
    public fDecimal round(int dround) {
        return new fDecimal(originObject.setScale(dround, RoundingMode.HALF_UP), dround);
    }

    /**
     * Addition operation (Add BigDecimal object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(BigDecimal object, int lengthAfterPoint) {
        if (object == null) {
            return new fDecimal(LONG_ZERO, 0);
        }
        this.originObject = BigDecimalUtil.truncate(this.originObject, this.lengthAfterPoint);
        object = BigDecimalUtil.truncate(object, lengthAfterPoint);
        int size = Math.max(this.lengthAfterPoint, lengthAfterPoint);
        BigDecimal tmp = BigDecimalUtil.truncate(this.originObject.add(object), size);
        fDecimal result = new fDecimal(tmp, size);
        return result;
    }

    /**
     * Addition operation (Add BigInteger object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(BigInteger object, int lengthAfterPoint) {
        return add(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Addition operation (Add long object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(long object, int lengthAfterPoint) {
        return add(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Addition operation (Add int object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(int object, int lengthAfterPoint) {
        return add(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Addition operation (Add short object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(short object, int lengthAfterPoint) {
        return add(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Addition operation (Add double object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(double object, int lengthAfterPoint) {
        return add(new BigDecimal(String.valueOf(object)), lengthAfterPoint);
    }

    /**
     * Addition operation (Add float object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(float object, int lengthAfterPoint) {
        return add(new BigDecimal(String.valueOf(object)), lengthAfterPoint);
    }

    /**
     * Addition operation (Add fDecimal object to current fDecimal object)
     * 
     * @param object
     *            addend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal add(fDecimal object, int lengthAfterPoint) {
        return add(object.originObject, lengthAfterPoint);
    }

    /**
     * Subtraction operation (Subtract BigDecimal object to current fDecimal
     * object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(BigDecimal object, int lengthAfterPoint) {
        if (object == null) {
            return new fDecimal(LONG_ZERO, 0);
        }
        this.originObject = BigDecimalUtil.truncate(this.originObject, this.lengthAfterPoint);
        object = BigDecimalUtil.truncate(object, lengthAfterPoint);
        int size = Math.max(this.lengthAfterPoint, lengthAfterPoint);
        BigDecimal tmp = BigDecimalUtil.truncate(this.originObject.subtract(object), size);
        fDecimal result = new fDecimal(tmp, size);
        return result;
    }

    /**
     * Subtraction operation (Subtract BigInteger object to current fDecimal
     * object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(BigInteger object, int lengthAfterPoint) {
        return subtract(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Subtraction operation (Subtract long object to current fDecimal object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(long object, int lengthAfterPoint) {
        return subtract(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Subtraction operation (Subtract int object to current fDecimal object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(int object, int lengthAfterPoint) {
        return subtract(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Subtraction operation (Subtract short object to current fDecimal object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(short object, int lengthAfterPoint) {
        return subtract(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Subtraction operation (Subtract double object to current fDecimal object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(double object, int lengthAfterPoint) {
        return subtract(new BigDecimal(String.valueOf(object)), lengthAfterPoint);
    }

    /**
     * Subtraction operation (Subtract float object to current fDecimal object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(float object, int lengthAfterPoint) {
        return subtract(new BigDecimal(String.valueOf(object)), lengthAfterPoint);
    }

    /**
     * Subtraction operation (Subtract fDecimal object to current fDecimal
     * object)
     * 
     * @param object
     *            subtrahend
     * @param lengthAfterPoint
     *            number of decimal part of object that used to add
     * @return
     */
    public fDecimal subtract(fDecimal object, int lengthAfterPoint) {
        return subtract(object.originObject, lengthAfterPoint);
    }

    /**
     * Multiplication operation (Multiply BigDecimal object to current fDecimal
     * object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(BigDecimal object, int lengthAfterPoint) {
        if (object == null) {
            return new fDecimal(LONG_ZERO, 0);
        }
        this.originObject = BigDecimalUtil.truncate(this.originObject, this.lengthAfterPoint);
        object = BigDecimalUtil.truncate(object, lengthAfterPoint);
        int size = this.lengthAfterPoint + lengthAfterPoint;
        BigDecimal tmp = BigDecimalUtil.truncate(this.originObject.multiply(object), size);
        fDecimal result = new fDecimal(tmp, size);
        return result;
    }

    /**
     * Multiplication operation (Multiply BigInteger object to current fDecimal
     * object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(BigInteger object, int lengthAfterPoint) {
        return multiply(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Multiplication operation (Multiply long object to current fDecimal
     * object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(long object, int lengthAfterPoint) {
        return multiply(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Multiplication operation (Multiply int object to current fDecimal object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(int object, int lengthAfterPoint) {
        return multiply(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Multiplication operation (Multiply short object to current fDecimal
     * object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(short object, int lengthAfterPoint) {
        return multiply(new BigDecimal(object), lengthAfterPoint);
    }

    /**
     * Multiplication operation (Multiply double object to current fDecimal
     * object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(double object, int lengthAfterPoint) {
        return multiply(new BigDecimal(String.valueOf(object)), lengthAfterPoint);
    }

    /**
     * Multiplication operation (Multiply float object to current fDecimal
     * object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(float object, int lengthAfterPoint) {
        return multiply(new BigDecimal(String.valueOf(object)), lengthAfterPoint);
    }

    /**
     * Multiplication operation (Multiply fDecimal object to current fDecimal
     * object)
     * 
     * @param object
     *            multiplier
     * @param lengthAfterPoint
     *            number of decimal part of multiplier
     * @return
     */
    public fDecimal multiply(fDecimal object, int lengthAfterPoint) {
        return multiply(object.originObject, lengthAfterPoint);
    }

    /**
     * Division operation (Divide current fDecimal object by BigDecimal object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(BigDecimal object, int lengthAfterPoint, int dmax) {
        if (object == null) {
            return new fDecimal(LONG_ZERO, 0);
        }

        this.originObject = BigDecimalUtil.truncate(this.originObject, this.lengthAfterPoint);
        object = BigDecimalUtil.truncate(object, lengthAfterPoint);
        int size = Math.max(this.lengthAfterPoint - lengthAfterPoint, dmax);
        BigDecimal tmp = null;
        try {
            tmp = BigDecimalUtil.truncate(this.originObject.divide(object), size);
        } catch (ArithmeticException e) {
            tmp = BigDecimalUtil.truncate(
                    new BigDecimal(this.originObject.doubleValue() / object.doubleValue()), size);
        }

        fDecimal result = new fDecimal(tmp, size);
        return result;
    }

    /**
     * Division operation (Divide current fDecimal object by BigInteger object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(BigInteger object, int lengthAfterPoint, int dmax) {
        return divide(new BigDecimal(object), lengthAfterPoint, dmax);
    }

    /**
     * Division operation (Divide current fDecimal object by long object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(long object, int lengthAfterPoint, int dmax) {
        return divide(new BigDecimal(object), lengthAfterPoint, dmax);
    }

    /**
     * Division operation (Divide current fDecimal object by int object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(int object, int lengthAfterPoint, int dmax) {
        return divide(new BigDecimal(object), lengthAfterPoint, dmax);
    }

    /**
     * Division operation (Divide current fDecimal object by short object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(short object, int lengthAfterPoint, int dmax) {
        return divide(new BigDecimal(object), lengthAfterPoint, dmax);
    }

    /**
     * Division operation (Divide current fDecimal object by double object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(double object, int lengthAfterPoint, int dmax) {
        return divide(new BigDecimal(String.valueOf(object)), lengthAfterPoint, dmax);
    }

    /**
     * Division operation (Divide current fDecimal object by float object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(float object, int lengthAfterPoint, int dmax) {
        return divide(new BigDecimal(String.valueOf(object)), lengthAfterPoint, dmax);
    }

    /**
     * Division operation (Divide current fDecimal object by fDecimal object)
     * 
     * @param object
     *            divisor
     * @param lengthAfterPoint
     *            number of decimal part of divisor
     * @return
     */
    public fDecimal divide(fDecimal object, int lengthAfterPoint, int dmax) {
        return divide(object.originObject, lengthAfterPoint, dmax);
    }

    /**
     * Get short value
     * 
     * @return
     */
    public short shortValue() {
        return originObject.shortValue();
    }

    /**
     * Get int value
     * 
     * @return
     */
    public int intValue() {
        return originObject.intValue();
    }

    /**
     * Get long value
     * 
     * @return
     */
    public long longValue() {
        return originObject.longValue();
    }

    /**
     * Get BigInteger value
     * 
     * @return
     */
    public BigInteger bigIntegerValue() {
        return originObject.toBigInteger();
    }

    /**
     * Get float value
     * 
     * @return
     */
    public float floatValue() {
        return originObject.floatValue();
    }

    /**
     * Get double value
     * 
     * @return
     */
    public double doubleValue() {
        return originObject.doubleValue();
    }

    /**
     * Get BigDecimal value
     * 
     * @return
     */
    public BigDecimal bigDecimalValue() {
        return originObject;
    }

    /**
     * Get String value
     * 
     * @return
     */
    public String stringValue() {
        return originObject.toString();
    }

    /**
     * Compare to another object
     * 
     * @param object
     * @return -1: less than 0: equal 1: greater
     */
    public int compareTo(fDecimal object) {
        return originObject.compareTo(object.originObject);
    }
}
