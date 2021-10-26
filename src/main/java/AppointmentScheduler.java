import Model.AppointmentInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AppointmentScheduler {
    public final static String DOMAIN = "http://scheduling-interview-2021-265534043.us-west-2.elb.amazonaws.com/api/Scheduling";
    public final static String START = DOMAIN + "/Start";
    public final static String STOP = DOMAIN + "/Stop";
    public final static String APPOINTMENT_REQUEST = DOMAIN + "/AppointmentRequest";
    public final static String SCHEDULE = DOMAIN + "/Schedule";

    private OkHttpClient client;
    private ObjectMapper mapper;
    private List<AppointmentInfo> schedule;

    public static void main(String[] args) {
        String token = "a0fd7251-5b68-4075-ad47-eacbb60c51d9";

        AppointmentScheduler app = new AppointmentScheduler();
        app.run(token);
    }

    public void run(String token) {
        client = new OkHttpClient();

        Response response;

        // Call Start to reset API
        try {
            response = postStart(token);
        }
        catch (IOException e) {
            // TODO: Log
            System.err.println("Error:\n" + e.getMessage());
            return;
        }

        if (response.code() != 200) {
            // TODO: Log
            System.err.println("Error: response code of " + response.code());
            return;
        }

        System.out.println("Start successful");

        // Call Schedule to get the initial schedule
        try {
            response = getSchedule(token);
        }
        catch (IOException e) {
            // TODO: Log
            System.err.println("Error:\n" + e.getMessage());
            return;
        }

        if (response.code() != 200) {
            // TODO: Log
            System.err.println("Error: response code of " + response.code());
            return;
        }

        String jsonStr;
        try {
            jsonStr = response.body().string();
        } catch (IOException e) {
            // TODO: Log
            System.err.println("Error: Could not retrieve response body.\n" + e.getMessage());
            return;
        }

        mapper = new ObjectMapper();
        try {
            schedule = Arrays.asList(mapper.readValue(jsonStr, AppointmentInfo[].class));
        } catch (JsonProcessingException e) {
            // TODO: Log
            System.err.println("Error: Could not deserialize json.\n" + e.getMessage());
            return;
        }

        // Create patient list and doctor list so the info is easily accessible when scheduling new appointments
        for (AppointmentInfo appointmentInfo : schedule) {
            // Add appointment to patient list
            // Depending on how patient ID's are created/retained, a list where the index is the ID could work well.
            // An alternative might be a map that uses the ID as the key. The values would either be lists of the
            // Appointments associated with that ID or a new model object to store the patient's appointments

            // Add appointment to doctor list
            // Since the numbers of doctors will be much less than the patients, I feel that the list approach would
            // work very well here. Especially since the given number is currently 3 doctors. I would use the same
            // approach that was used to keep track of patient appointments here as well, so they match. That being
            // A doctor's appointments are stored as a list of their appointments or a new model object will store
            // the appointments for that doctor
        }

        // Get first appointment request
        // response = getAppointmentRequest(token);

        // Schedule appointment requests to the schedule until no more requests remain
        while (response.code() == 200) {
            // Check to request against scheduling criteria
            // For each date listed under preferredDays, get the closest legal appointment slot, remember the
            // "distance" from the preferred time. Choose the slot that is most near a preferred time. If a slot
            // is available during a preferred time, there is no need to keep looking.

            // In the edge case that no appointment could be made, the request would be logged for now to indicate
            // it was not scheduled.

            // Add appointment to schedule
            // response = postSchedule(token, appointment);

            // Get next appointment request
            // response = getAppointmentRequest(token);
        }

        // We are done. Call stop I guess.
        // response = postStop(token);
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
        RequestBody body;

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
