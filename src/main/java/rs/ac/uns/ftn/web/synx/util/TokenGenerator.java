package rs.ac.uns.ftn.web.synx.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.web.synx.model.User;

public class TokenGenerator {

	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SEPARATOR = ".";
	private static final String SEPARATOR_SPLITTER = "\\.";

	private final Mac hmac;

	public TokenGenerator() {
		
		byte[] secretKey = DatatypeConverter.parseBase64Binary(
				"9SyECk96oDsTmXfogIieDI0cD/8FpnojlYSUJT5U9I/FGVmBz5oskmjOR8cbXTvoPjX+Pq/T/b1PqpHX0lYm0oCBjXWICA==");

		try {
			hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
		}
	}

	public String issueToken(User user) {
		
		byte[] userBytes = toJSON(user);
		byte[] hash = createHmac(userBytes);

		final StringBuilder builder = new StringBuilder(170);

		builder.append(toBase64(userBytes));
		builder.append(SEPARATOR);
		builder.append(toBase64(hash));

		return builder.toString();
	}
	
	public User parseUserFromToken(String token){
		if(token == null) return null;
		
		final String[] parts = token.split(SEPARATOR_SPLITTER);
		
		if(parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0){
			
			final byte[] userBytes = fromBase64(parts[0]);
			final byte[] hash = fromBase64(parts[1]);
			
			boolean validHash = Arrays.equals(createHmac(userBytes), hash);
            if (validHash) {
                final User user = fromJSON(userBytes);
                return user;
            }
            
            
		}
		
		return null;
	}
	
	private User fromJSON(final byte[] userBytes){
		
		try {
			
			return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), User.class);
			
		} 
		catch (JsonParseException e) {
			e.printStackTrace();
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private byte[] toJSON(User u){
		
		try {
			
			return new ObjectMapper().writeValueAsBytes(u);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
    private String toBase64(byte[] content) {
        return DatatypeConverter.printBase64Binary(content);
    }

    private byte[] fromBase64(String content) {
        return DatatypeConverter.parseBase64Binary(content);
    }

    private synchronized byte[] createHmac(byte[] content) {
        return hmac.doFinal(content);
    }
}
