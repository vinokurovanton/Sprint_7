package json;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier getRandomCourier(){
        return new Courier(RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(7));
    }
}
