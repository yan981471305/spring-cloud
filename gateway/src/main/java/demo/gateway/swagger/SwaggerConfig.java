package demo.gateway.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@RefreshScope
@Configuration
public class SwaggerConfig {

    /**
     * 用来创建该Api的基本信息
     * （这些基本信息会展现在文档页面中）
     **/
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("user restful apis")
                .description("测试 Swagger")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * 函数创建Docket的Bean
     * <p>
     * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义，
     * Swagger会扫描该包下所有Controller定义的API，
     * 并产生文档内容（除了被@ApiIgnore指定的请求）
     **/
    @Bean
    public Docket api() {


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // .apis(RequestHandlerSelectors.basePackage("demo.ms"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  //添加ApiOperiation注解的被扫描
                .paths(PathSelectors.any())
                .build();
    }

}
