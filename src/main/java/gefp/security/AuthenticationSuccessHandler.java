package gefp.security;

import gefp.util.DefaultUrls;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends
    SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    DefaultUrls defaultUrls;

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request,
        HttpServletResponse response, Authentication authentication )
        throws ServletException, IOException
    {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest( request, response );
        if( savedRequest != null )
        {
            super.onAuthenticationSuccess( request, response, authentication );
            return;
        }

        getRedirectStrategy().sendRedirect( request, response,
            defaultUrls.userHomeUrl( request ) );
    }

}
