package com.dev.manager.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dev.encryptor.AES;

@Component
public class EncryptionUtil {

	@Value("${key}")
	private String key;

	AES aes = null;

	public EncryptionUtil() {
		aes = new AES();
	}

	public String encrypt(String plainText) {
		return aes.encrypt(key, plainText);
	}

	public String decrypt(String cipherText) {
		return aes.decrypt(key, cipherText);
	}
}
