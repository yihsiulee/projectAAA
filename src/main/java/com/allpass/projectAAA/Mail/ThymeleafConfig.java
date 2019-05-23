package com.allpass.projectAAA.Mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

//import org.thymeleaf.templatemode.TemplateModeHandlers;

@Configuration
public class ThymeleafConfig {



//    @Bean
//    public SpringTemplateEngine springTemplateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.addTemplateResolver(htmlTemplateResolver());
//        return templateEngine;
//    }
@Bean
public TemplateEngine emailTemplateEngine() {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    // Resolver for TEXT emails
//    templateEngine.addTemplateResolver(textTemplateResolver());
    // Resolver for HTML emails (except the editable one)
    templateEngine.addTemplateResolver(htmlTemplateResolver());
    // Resolver for HTML editable emails (which will be treated as a String)
//    templateEngine.addTemplateResolver(stringTemplateResolver());
    // Message source, internationalization specific to emails
//    templateEngine.setTemplateEngineMessageSource(emailMessageSource());
    return templateEngine;
}

    @Bean
    public ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        //templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("templates/formal/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
//        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
