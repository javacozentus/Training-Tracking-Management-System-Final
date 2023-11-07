package com.cozentus.TrainingTracking.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cozentus.TrainingTracking.Config.CredentialUserDetails;
import com.cozentus.TrainingTracking.Model.Credential;
import com.cozentus.TrainingTracking.Repository.CredentialRepository;

@Service
public class CredentialUserDetailsService implements UserDetailsService{
       
        @Autowired
        private CredentialRepository credentialRepository;
 
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credential> userInfo = credentialRepository.findByUserId(username);
        return userInfo.map(CredentialUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
    }
}
