package sejong.capstone.team13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sejong.capstone.team13.model.ForecastData;
import sejong.capstone.team13.repository.ForecastRepository;

@Controller
public class ForecastController {

    ForecastRepository forecastRepository;

    @Autowired
    public ForecastController(ForecastRepository forecastRepository){
        this.forecastRepository = forecastRepository;
    }

    @GetMapping("/forecast")
    @ResponseBody
    public ForecastData getForecastData(){
        return forecastRepository.getPredictedData().get(0);
    }


}
