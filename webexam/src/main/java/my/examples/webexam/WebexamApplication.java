package my.examples.webexam;

import my.examples.webexam.component.ReportCsvView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.Locale;

@SpringBootApplication
public class WebexamApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebexamApplication.class, args);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.favorParameter(true)
                .favorPathExtension(true).
                ignoreAcceptHeader(false).
                defaultContentType(MediaType.APPLICATION_XML).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON).
                mediaType("csv", new MediaType("text", "csv"));
    }

    @Bean
    public ViewResolver viewResolver() {
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        return beanNameViewResolver;
    }
}


