package web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class MyAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                MyWebConfig.class
        };
    }



    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup( servletContext );
        registerCharacterEncodingFilter(servletContext);
        registerHiddenFieldFilter(servletContext);
    }

    private void registerHiddenFieldFilter(ServletContext sContext) {
        sContext.addFilter( "hiddenHttpMethodFilter", new HiddenHttpMethodFilter() ).addMappingForUrlPatterns( null, true, "/*" );
    }

    private void registerCharacterEncodingFilter(ServletContext sContext) {
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of( DispatcherType.REQUEST, DispatcherType.FORWARD );

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding( "UTF-8" );
        characterEncodingFilter.setForceEncoding( true );

        FilterRegistration.Dynamic characterEncoding = sContext.addFilter( "characterEncoding", characterEncodingFilter );
        characterEncoding.addMappingForUrlPatterns( dispatcherTypes, true, "/*" );
    }
}
