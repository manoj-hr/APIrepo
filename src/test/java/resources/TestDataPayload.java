package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;

public class TestDataPayload {

    public AddPlace addPlacePayload(String name,String phone_number,String address,String language){
        AddPlace json=new AddPlace();
        json.setAccuracy(50);
        json.setAddress(address);
        json.setName(name);
        json.setLanguage(language);
        json.setPhone_number(phone_number);
        ArrayList<String> list=new ArrayList<>();
        list.add("short box");
        list.add("notes");
        json.setTypes(list);
        json.setWebsite("https://manoj.com");
        Location loc=new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        json.setLocation(loc);
        return json;
    }
    public String deletePlacePayload(String placeId){
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }
}
