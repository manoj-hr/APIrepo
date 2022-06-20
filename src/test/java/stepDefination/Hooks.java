package stepDefination;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void  beforeDeleteApi() throws IOException {

        if(StepDefination.place_id==null){
            StepDefination sd=new StepDefination();
            sd.add_place_payload("manoj","+(91) 465 765 245","Bengaluru","Kannada");
            sd.user_calls_using_post_http_method("addPlaceApi","post");
            sd.verify_the_place_id_created_for_using("manoj","getPlaceApi");
        }

    }

}
