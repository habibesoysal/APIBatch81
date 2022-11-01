package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

    public Map<String, String> dataKeyMapMethod(String name, String email, String gender, String status) {

        Map<String, String> dataKeyMap = new HashMap<>();
        dataKeyMap.put("name", name);
        dataKeyMap.put("email", email);
        dataKeyMap.put("gender", gender);
        dataKeyMap.put("status", status);

        return dataKeyMap;
    }

    public Map<String, Object> exDataMethod(Object meta, Map<String, String> data) {

        Map<String, Object> olusturulanExDataMap = new HashMap<>();
        olusturulanExDataMap.put("meta", meta);
        olusturulanExDataMap.put("data", data);

        return olusturulanExDataMap;

    }
}
/*

{
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}




 */