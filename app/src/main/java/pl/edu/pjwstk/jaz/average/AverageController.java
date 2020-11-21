package pl.edu.pjwstk.jaz.average;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.AverageResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Double.parseDouble;


@RestController
public class AverageController {

    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {

        if (numbers == null || numbers.isEmpty()) {
            return new AverageResult("Please put the parameters.");
        }
        String[] StringArray = numbers.split(",");

        double sum = 0;

        for (String k : StringArray) {
            sum += parseDouble(k);
        }


        BigDecimal average = new BigDecimal(sum / StringArray.length);
        average = average.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
        return new AverageResult("Average equals to: " + average);

    }
}