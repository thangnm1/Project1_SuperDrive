package com.udacity.thangnm1.superduperdrive.services;


import org.springframework.stereotype.Service;

import com.udacity.thangnm1.superduperdrive.enitites.Credential;
import com.udacity.thangnm1.superduperdrive.mappers.CredentialMapper;
import com.udacity.thangnm1.superduperdrive.mappers.UserMapper;

@Service
public class CredentialService {

	private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public void addCredential(String url, String userName, String credentialUserName, String key, String password) {
        Integer userId = userMapper.getUser(userName).getUserId();
        Credential credential = new Credential(0, url, credentialUserName, key, password, userId);
        credentialMapper.insert(credential);
    }

    public Credential[] getCredentialListings(Integer userId) {
        return credentialMapper.getCredentialListings(userId);
    }

    public Credential getCredential(Integer noteId) {
        return credentialMapper.getCredential(noteId);
    }

    public void deleteCredential(Integer noteId) {
        credentialMapper.deleteCredential(noteId);
    }

    public void updateCredential(Integer credentialId, String newUserName, String url, String key, String password) {
        credentialMapper.updateCredential(credentialId, newUserName, url, key, password);
    }
}
