package com.itg.AutomobilePartApp.Entities;

import com.itg.AutomobilePartApp.Entities.AutoParts.BrakeSystemPart;
import com.itg.AutomobilePartApp.Entities.AutoParts.ExhaustSystemPart;
import com.itg.AutomobilePartApp.Entities.AutoParts.FuelInjectionSystem;
import com.itg.AutomobilePartApp.Entities.AutoParts.Other;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "ExhaustSystemPart")
    public ExhaustSystemPart ExhaustSystemPart() {
        return new ExhaustSystemPart();
    }

    @Bean(name = "FuelInjectionSystem")
    public FuelInjectionSystem fuelInjectionSystem() {
        return new FuelInjectionSystem();
    }

    @Bean(name = "BrakeSystemPart")
    public BrakeSystemPart brakeSystemPart() {
        return new BrakeSystemPart();
    }

    @Bean(name = "Other")
    public Other other() {
        return new Other();
    }
}
