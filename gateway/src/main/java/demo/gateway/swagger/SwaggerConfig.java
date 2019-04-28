package demo.gateway.swagger;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.util.Arrays;
import java.util.List;

@RefreshScope
@Configuration
public class SwaggerConfig {

    @Value("${swagger.oauth.base-url}")
    private String oAuthBaseUri;

    @Value("${swagger.oauth.client-id}")
    private String clientId;

    @Value("${swagger.oauth.client-secret}")
    private String clientSecret;

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                                .select()
                                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
                                .paths(PathSelectors.any())
                                .build()
                                .apiInfo(apiInfo())
                                .securitySchemes(Arrays.asList(oAuth2(),apiKey()))
                                .securityContexts(Arrays.asList(securityContext()))
                                .useDefaultResponseMessages(false);
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                       .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                       .deepLinking(true)
                       .displayOperationId(false)
                       .defaultModelsExpandDepth(1)
                       .defaultModelExpandDepth(1)
                       .defaultModelRendering(ModelRendering.EXAMPLE)
                       .displayRequestDuration(false)
                       .docExpansion(DocExpansion.NONE)
                       .filter(false)
                       .maxDisplayedTags(null)
                       .operationsSorter(OperationsSorter.ALPHA)
                       .showExtensions(false)
                       .tagsSorter(TagsSorter.ALPHA)
                       .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                       .validatorUrl(null)
                       .build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                       .clientId(clientId)
                       .clientSecret(clientSecret)
                       .scopeSeparator(" ")
                       .useBasicAuthenticationWithAccessCodeGrant(false)
                       .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                       .securityReferences(Lists.newArrayList(oauth2Ref(),apiKeyRef()))
                       .forPaths(PathSelectors.any())
                       .build();
    }

    private SecurityReference oauth2Ref() {
        AuthorizationScope defaultScope = new AuthorizationScope("openid", "全部");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = defaultScope;
        return new SecurityReference("oauth2", authorizationScopes);
    }

    private SecurityReference apiKeyRef() {
        AuthorizationScope defaultScope = new AuthorizationScope("global", "全部");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = defaultScope;
        return new SecurityReference("Authorization", authorizationScopes);
    }

    private List<AuthorizationScope> scopes(){
        return Arrays.asList(
                new AuthorizationScope("openid","全部"));
    }

    private SecurityScheme apiKey(){
        return new ApiKey("Authorization","Authorization","header");
    }

    private SecurityScheme oAuth2(){

        String TOKEN_REQUEST_URL = oAuthBaseUri+"/oauth/authorize";
        String TOKEN_URL = oAuthBaseUri+"/oauth/token";

        GrantType grantType = new AuthorizationCodeGrantBuilder()
                                      .tokenEndpoint(new TokenEndpoint(TOKEN_URL, "access_token"))
                                      .tokenRequestEndpoint(new TokenRequestEndpoint(TOKEN_REQUEST_URL, clientId, clientSecret ))
                                      .build();

        SecurityScheme oauth = new OAuthBuilder().name("oauth2")
                                       .grantTypes(Arrays.asList(grantType))
                                       .scopes(scopes())
                                       .build();

        return oauth;
    }
}
