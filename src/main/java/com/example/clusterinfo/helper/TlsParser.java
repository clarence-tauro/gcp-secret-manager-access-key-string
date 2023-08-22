package com.example.clusterinfo.helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.pem.util.PemUtils;

public class TlsParser {

	public SSLFactory parseTlsContext(InputStream certFile, InputStream caFile, InputStream keyFile, String keyPassword) {


			X509ExtendedKeyManager keyManager = PemUtils.loadIdentityMaterial(certFile, keyFile,
					keyPassword == null ? null : keyPassword.toCharArray());
			X509ExtendedTrustManager trustManager = PemUtils.loadTrustMaterial(caFile);

			SSLFactory sslFactory = SSLFactory.builder().withIdentityMaterial(keyManager)
					.withTrustMaterial(trustManager).build();
			return sslFactory;
	}

	public SSLFactory parseStringTlsContext(String certString, String caCertString, String privateKeyString,
			String keyPassword) {


		return parseTlsContext(new ByteArrayInputStream(certString.getBytes()), 
				new ByteArrayInputStream(caCertString.getBytes()), 
				new ByteArrayInputStream(privateKeyString.getBytes()), 
				keyPassword);
	}

}
