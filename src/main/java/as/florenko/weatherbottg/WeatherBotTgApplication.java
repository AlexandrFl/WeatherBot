package as.florenko.weatherbottg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class WeatherBotTgApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherBotTgApplication.class, args);

    }

}
