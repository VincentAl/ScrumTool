package hei.gl.scrumtool.core.config;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "hei.scrumtool.core.service" })
public class AppConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);


    @Bean
    public Properties dbProperties() {
        Properties props = new Properties();
        try {
            props.load(AppConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            LOGGER.error("Impossible de charger db.properties");
        }
        return props;
    }
}