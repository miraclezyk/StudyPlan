package gefp.util;

import gefp.model.User;
import gefp.security.SecurityUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class DefaultUrls {

    public String userHomeUrl( HttpServletRequest request )
    {
        User user = SecurityUtils.getUser();

        String homeUrl;
        if( user.isAdmin() )
            homeUrl = "/firstpage";
        else if( user.isAdvisor() )
            homeUrl = "/advisorPage";
        else
            homeUrl = "/studentPage";

        return homeUrl;
    }

}
