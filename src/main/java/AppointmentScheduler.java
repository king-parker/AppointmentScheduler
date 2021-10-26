import Model.AppointmentInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppointmentScheduler {
    public final static String DOMAIN = "http://scheduling-interview-2021-265534043.us-west-2.elb.amazonaws.com/api/Scheduling";
    public final static String START = DOMAIN + "/Start";
    public final static String STOP = DOMAIN + "/Stop";
    public final static String APPOINTMENT_REQUEST = DOMAIN + "/AppointmentRequest";
    public final static String SCHEDULE = DOMAIN + "/Schedule";

    private OkHttpClient client;

    public static void main(String[] args) {
        String token = "a0fd7251-5b68-4075-ad47-eacbb60c51d9";

        AppointmentScheduler app = new AppointmentScheduler();
        app.run(token);
    }

    public void run(String token) {
        client = new OkHttpClient();

        Response response = null;

        // Call Start to reset API
        try {
            response = postStart(token);
        }
        catch (IOException e) {
            // TODO: Log
            return;
        }

        if (response.code() != 200) {
            // TODO: Log
            return;
        }

        System.out.println("Start successful");

        // Call Schedule to get the initial schedule
        try {
            response = getSchedule(token);
        }
        catch (IOException e) {
            // TODO: Log
            System.err.println("Error: " + e.getMessage());
            return;
        }

        if (response.code() != 200) {
            // TODO: Log
            System.err.println("Error: response code of " + response.code());
            return;
        }

        String jsonStr = null;
        try {
            jsonStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<AppointmentInfo> schedule = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            schedule = Arrays.asList(mapper.readValue(jsonStr, AppointmentInfo[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for (AppointmentInfo appointmentInfo : schedule) {
            System.out.println(appointmentInfo);
        }
    }

    private Response postStart(String token) throws IOException {
        String url = buildUrl(START, "token", token);

        // TODO: Log
        System.out.println("URL for START post request: " + url);

        return postRequest(url, "");
    }

    private Response getSchedule(String token) throws IOException {
        String url = buildUrl(SCHEDULE, "token", token);

        // TODO: Log
        System.out.println("URL for SCHEDULE get request: " + url);

        return getRequest(url);
    }

    private String buildUrl(String baseUrl, String paramKey, String paramVal) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter(paramKey, paramVal);

        return urlBuilder.build().toString();
    }

    private Response getRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        return client.newCall(request).execute();
    }

    private Response postRequest(String url, String bodyContent) throws IOException {
        RequestBody body = null;

        if (bodyContent.isEmpty()) {
            body = RequestBody.create(new byte[0]);
        }
        else {
            body = RequestBody.create(bodyContent, MediaType.parse("application/json"));
        }
        Request request = new Request.Builder().url(url).post(body).build();

        return client.newCall(request).execute();
    }
}
