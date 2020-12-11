package pl.edu.pjwstk.jaz;

import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;

public class AverageResult {
    public final String result;

    public AverageResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
