package as.florenko.weatherbottg.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {
    String decFormat = "#0.00";
    private int count;
    private Data[] data;


    @Override
    public String toString() {
        return data[0].getCity_name() + "\nТемпература " + data[0].getTemp() + "\n" + data[0].getWeather().getDescription() +
                "\nНаправление ветра " + data[0].getWind_cdir_full() + "\nСкорость ветра " + new DecimalFormat(decFormat).format(data[0].getWind_spd());
    }
}

