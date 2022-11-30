package as.florenko.weatherbottg;

import as.florenko.weatherbottg.service.WeatherInfo;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Request {
    public final double samLat = 53.2453;
    public final double samLon = 50.2243;

    public final double nbLat = 53.680765;
    public final double nbLon = 50.057051;

    public WeatherInfo getWeather(double lat, double lon) {
        final RestTemplate restTemplate = new RestTemplate();
        String units = "units=metric";
        String lang = "lang=ru";

        String samLat = "lat=" + lat;
        String samLon = "lon=" + lon;

        String url = "https://weatherbit-v1-mashape.p.rapidapi.com/current/?" + samLat + "&" + samLon + "&" + units + "&" + lang;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "bc59e36621msh7784a041578be6ep173668jsn71da5c0bc917");
        headers.set("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class);

        String responseBody = response.getBody();
        Gson gson = new Gson();
        WeatherInfo weatherInfo = gson.fromJson(responseBody, WeatherInfo.class);
        System.out.println(weatherInfo);
        return weatherInfo;
    }
}
