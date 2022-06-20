package resources;

public enum EndPoints {
    addPlaceApi("/maps/api/place/add/json"),
    getPlaceApi("/maps/api/place/get/json"),
    deletePlaceAPi("/maps/api/place/delete/json");
    private String resources;

    EndPoints(String resources) {
        this.resources=resources;
    }
    public String getEndPoint(){
        return resources;
    }
}
