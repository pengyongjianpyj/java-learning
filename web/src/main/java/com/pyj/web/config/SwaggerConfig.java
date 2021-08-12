package com.pyj.web.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@SuppressWarnings("checkstyle:MissingJavadocMethod")
public class SwaggerConfig {

  @Value("${swagger.basePath:}")
  private String swaggerPath;

  @Bean
  public Docket createControllerDocket() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("controller").apiInfo(controllerInfo())
        .host(swaggerPath)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.pyj.web"))
        .paths(PathSelectors.any()).build().globalOperationParameters(buildCommonParameters());
  }

  private ApiInfo controllerInfo() {
    return new ApiInfoBuilder()
        .title("内部controller")
        .description("swagger-api")
        .version("1.0")
        .build();
  }

  private List<Parameter> buildCommonParameters() {
    ParameterBuilder parameterBuilder = new ParameterBuilder();
    parameterBuilder
        .parameterType("header") //参数类型支持header, cookie, body, query etc
        .name("session") //参数名
        .description("session")
        .modelRef(new ModelRef("string")) //指定参数值的类型
        .required(false).build(); //非必需，这里是全局配置
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(parameterBuilder.build());
    return parameters;
  }
}
