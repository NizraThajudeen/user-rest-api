package lentra.test.userrestapi.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApi {
    @Bean public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder() .group("public") .pathsToMatch("/**") .build();
    }
}