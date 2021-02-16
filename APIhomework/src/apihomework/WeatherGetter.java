package apihomework;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;



public class WeatherGetter {

    public static void getWeather()
    {
        // Create HTTP Connection
        String baseUrl = "https://api.openweathermap.org";
        String callAction = "/data/2.5/weather?";
        String city = "q=Greensboro";
        String apiKey = "fb0439fe4558d91a8b2e35b6b1e76e56";
        String urlString = baseUrl + callAction + city + "&appid=" + apiKey;
        URL url;

        try {
            //Make the connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");


            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Could not load weather update: " + status);
            } else {
                // Parsing input stream into a text string.
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the connections.
                in.close();
                con.disconnect();

                // Print out our complete JSON string.
                System.out.println("\nOutput: " + content.toString());

                // Parse that object into a usable Java JSON object.
                JSONObject obj = new JSONObject(content.toString());

                // Print out the weather update.
                LocalDate myDate = LocalDate.now(); // Create a date object
                LocalTime myTime = LocalTime.now(); // Create a time object


                String location = obj.getString("name");
                String temp = obj.getJSONObject("main").getString("temp");
                System.out.println("\nDate: " + myDate + "\nTime: " + myTime + "\nCity: " + location + "\nTemperature: " + temp + " K");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }
    }

}

