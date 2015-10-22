package hei.gl.scrumtool.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages={"hei.gl.scrumtool.web.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}
	
	@Bean
	public VelocityConfigurer getVelocityConfigurer(){
		VelocityConfigurer vc = new VelocityConfigurer();
		vc.setResourceLoaderPath("WEB-INF/velocity");
		return vc;
	}
	
	@Bean
	public VelocityViewResolver getVelocityViewResolver(){
		VelocityViewResolver vvr = new VelocityViewResolver();
		vvr.setSuffix(".vm");
		return vvr;
	}
	

}


