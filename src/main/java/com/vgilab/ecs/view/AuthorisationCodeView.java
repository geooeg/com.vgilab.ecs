package com.vgilab.ecs.view;

import com.vgilab.ecs.persistence.entity.AuthorizationCodeEntity;
import com.vgilab.ecs.persistence.repositories.AuthorizationCodeRepository;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author smuellner
 */
@Component
@ManagedBean(name = "authorisationCodeView")
@SessionScoped
public class AuthorisationCodeView implements Serializable {

    @Autowired
    private AuthorizationCodeRepository authorizationCodeRepository;
    
    private AuthorizationCodeEntity authorizationCodeEntity;

    /**
     * @return the authorizationCode
     */
    public String getAuthorizationCode() {
        if(null == authorizationCodeEntity || Calendar.getInstance().before(authorizationCodeEntity.getValidUntilTime())) {
            authorizationCodeEntity = authorizationCodeRepository.save(new AuthorizationCodeEntity());
        }
        return authorizationCodeEntity.getId();
    }
}
