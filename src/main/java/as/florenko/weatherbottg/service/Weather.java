package as.florenko.weatherbottg.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private String description;
    private long code;
    private String icon;
}
