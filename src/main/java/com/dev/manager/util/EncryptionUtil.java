package com.dev.manager.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dev.encryptor.AES;

@Component
public class EncryptionUtil {

	@Value("${key}")
	private String key;

	@Value("${customKey}")
	private String customKey;

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

	public String encryptCustom(String pin, String plainText) {
		return aes.encrypt(customKey + pin, plainText);
	}

	public String decryptCustom(String pin, String cipherText) {
		return aes.decrypt(customKey + pin, cipherText);
	}

}
