package ru.homework.loanmaster.service;

import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.homework.loanmaster.util.Countries;
import ru.homework.loanmaster.validation.ErrorCode;
import ru.homework.loanmaster.validation.ValidationException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountryService {
    //TODO: property
    private final static String COUNTRY_METRIC_RESOURCE = "countries.txt";

    public final static String DEFAULT_COUNTRY_CODE = "LV";
    //TODO: property
    public final static int COUNTRY_PER_MINUTE = 100;

    private HashMap<String, AtomicInteger> countryMetricMap = new HashMap<>();

    @Resource
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void initCountryMetrics() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceLoader.getResource("classpath:" + COUNTRY_METRIC_RESOURCE).getInputStream()));

        String countryCode;
        while ((countryCode = bufferedReader.readLine()) != null) {
            countryMetricMap.put(countryCode.trim(), new AtomicInteger());
        }
    }

    //TODO: time property
    @Scheduled(initialDelay = 60 * 1000, fixedDelay = 60 * 1000)
    public void saveMinuteMetric() {
        countryMetricMap.values().forEach(i -> i.set(0));
    }

    public String getCountryCodeByIp(String ip) {
        String countryCode;
        try {
            countryCode = Countries.getCountryCodeByIp(ip);

            if (Countries.UNKNOWN_VALUE.equals(countryCode)) {
                countryCode = DEFAULT_COUNTRY_CODE;
            }
        } catch (Exception e) {
            countryCode = DEFAULT_COUNTRY_CODE;
        }

        if (countryMetricMap.get(countryCode).addAndGet(1) > COUNTRY_PER_MINUTE) {
            throw new ValidationException(ErrorCode.BLOCKED_BY_COUNTRY_METRIC);
        }

        return countryCode;

    }

}
