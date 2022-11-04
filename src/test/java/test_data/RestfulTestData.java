package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String, String> bookingDatesMethod(String checkin, String checkout) {

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkin);
        bookingDatesMap.put("checkout", checkout);

        return bookingDatesMap;
    }

    public Map<String, Object> exDataMethod(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates) {

        Map<String, Object> outerMap = new HashMap<>();

        outerMap.put("firstname", firstname);
        outerMap.put("lastname", lastname);
        outerMap.put("totalprice", totalprice);
        outerMap.put("depositpaid", depositpaid);
        outerMap.put("bookingdates", bookingdates);

        return outerMap;

    }

}