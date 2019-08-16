package response;

import com.google.gson.JsonElement;

public class StandarResponse {
    private StatusResponse status;
    private int statusResponse;
    private String message;
    private JsonElement data;

    private StandarResponse(){

    }

    public StandarResponse(StatusResponse status) {
        this.status = status;
    }

    public StandarResponse(int status) {
        this.statusResponse = status;
    }

    public StandarResponse(StatusResponse status, String message) {
        this.status = status;
        this.message = message;
    }

    public StandarResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.data = data;
    }

    public StandarResponse(int statusResponse, JsonElement data) {
        this.statusResponse = statusResponse;
        this.data = data;
    }

    public int getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(int statusResponse) {
        this.statusResponse = statusResponse;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
