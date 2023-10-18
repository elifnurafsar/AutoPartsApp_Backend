package com.itg.AutomobilePartApp.Entities.AutoParts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = FakeFactory.class)
public class Config {

    @Bean
    public ExhaustSystemPart exhaustSystemPart() {
        return new ExhaustSystemPart();
    }

    @Bean
    public FuelInjectionSystem fuelInjectionSystem() {
        return new FuelInjectionSystem();
    }

    @Bean
    public BrakeSystemPart brakeSystemPart() {
        return new BrakeSystemPart();
    }

    @Bean
    public Other other() {
        return new Other();
    }
}
