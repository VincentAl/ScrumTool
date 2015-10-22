package hei.gl.scrumtool.web;

import hei.gl.scrumtool.core.config.AppConfig;
import hei.gl.scrumtool.core.config.DBConfig;
import hei.gl.scrumtool.web.config.WebConfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		Class<?>[] rootConf = {AppConfig.class, DBConfig.class};
		return rootConf;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class<?>[] servConf ={WebConfig.class};
		return servConf;
	}

	@Override
	protected String[] getServletMappings() {
		String[] servMapping ={"/"};
		return servMapping;
	}

}
