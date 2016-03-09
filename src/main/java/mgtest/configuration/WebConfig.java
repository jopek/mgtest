package mgtest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{
//  @Value("${frontend.dir}")
//  public String frontendDir;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

//    System.err.println("frontend dir: " + frontendDir);
//
//    if (frontendDir != null && !frontendDir.isEmpty()) {
//      registry.addResourceHandler("/**").addResourceLocations(frontendDir);
//    } else {
//      registry.addResourceHandler("/**").addResourceLocations("file:../frontend/app/");
//    }

    registry
        .addResourceHandler("/resources/public/**")
        .addResourceLocations("/");
  }
}
