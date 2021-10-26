import okhttp3.*;

import java.net.URL;

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

        start(token);
    }

    private void start(String token) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(START).newBuilder();
        urlBuilder.addQueryParameter("token", token);
        String url = urlBuilder.build().toString();
        System.out.println("URL for request: " + url);
        RequestBody body = RequestBody.create(new byte[0]);
        Request request = new Request.Builder().url(url).post(body).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        }
        catch (Exception e) {
            return;
        }

        System.out.println(response.code());
    }
}
