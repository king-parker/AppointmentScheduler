import okhttp3.*;

import java.io.IOException;

public class AppointmentScheduler {
    public final static String DOMAIN = "http://scheduling-interview-2021-265534043.us-west-2.elb.amazonaws.com/api/Scheduling";
    public final static String START = DOMAIN + "/Start";
    public final static String STOP = DOMAIN + "/Stop";
    public final static String APPOINTMENT_REQUEST = "/AppointmentRequest";
    public final static String SCHEDULE = "/Schedule";

    private OkHttpClient client;

    public static void main(String[] args) {
        String token = "a0fd7251-5b68-4075-ad47-eacbb60c51d9";

        AppointmentScheduler app = new AppointmentScheduler();
        app.run(token);
    }

    public void run(String token) {
        client = new OkHttpClient();

        Response response = null;
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
    }

    private Response postStart(String token) throws IOException {
        String url = buildUrl(START, "token", token);

        // TODO: Log
        System.out.println("URL for request: " + url);

        RequestBody body = RequestBody.create(new byte[0]);
        Request request = new Request.Builder().url(url).post(body).build();

        return postRequest(url, "");
    }

    private String buildUrl(String baseUrl, String paramKey, String paramVal) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(START).newBuilder();
        urlBuilder.addQueryParameter(paramKey, paramVal);
        String url = urlBuilder.build().toString();

        return url;
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
