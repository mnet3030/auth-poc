package com.example.idaaspoc.auth;

import com.example.idaaspoc.entity.User;
import com.example.idaaspoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;

public class IdaasUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) throws UsernameNotFoundException {
        String technicalId = preAuthenticatedAuthenticationToken.getPrincipal().toString();
        String credential = preAuthenticatedAuthenticationToken.getCredentials().toString();
        User user = userRepository.getUserByTechnicalId(technicalId);
        if (user == null) {
            throw new UsernameNotFoundException("User with technical id'" + technicalId + "' not found.");
        }
        return new org.springframework.security.core.userdetails.User(technicalId, credential, new ArrayList<>());
    }
}
