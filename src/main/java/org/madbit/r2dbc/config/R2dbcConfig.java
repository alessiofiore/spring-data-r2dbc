package org.madbit.r2dbc.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories(basePackages = "org.madbit.r2dbc.reactive.repository")
public class R2dbcConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "oracle")
                        .option(HOST, "vsppdbarp-scan.sisal.it")
                        .option(PORT, 1521)
                        .option(DATABASE, "spsar.mp.it")
                        .option(USER, "wager_gateway")
                        .option(PASSWORD, "wager_gateway")
//                        .option(MAX_SIZE, 40)
                        .build());
    }
}
