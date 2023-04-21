package franklin.paul.sylvester.services;

import franklin.paul.sylvester.dtos.responses.Principal;
import franklin.paul.sylvester.utils.JwtConfig;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TokenServiceTest {
    private TokenService sut;
    private final JwtConfig mockJwtConfig = Mockito.mock(JwtConfig.class);

    @Before
    public void init() {
        sut = new TokenService(mockJwtConfig);
    }

    @Test
    public void test_generateToken_givenSubject() {
        // Arrange
        Principal subject = new Principal(UUID.randomUUID().toString(), "testUsername",
                "testUsername@testUsername.com", new Date(2022,12,13), true, null);

        SignatureAlgorithm stubbedSigAlg = SignatureAlgorithm.HS256;
        Key stubbedSigningKey = new SecretKeySpec(new byte[1], stubbedSigAlg.getJcaName());

        Mockito.when(mockJwtConfig.getSigAlg()).thenReturn(stubbedSigAlg);
        Mockito.when(mockJwtConfig.getSigningKey()).thenReturn(stubbedSigningKey);

        // Act
        String condition = sut.generateToken(subject);


        // Assert
        assertNotNull(condition);
    }

    @Test
    public void test_extractRequesterDetails_givenToken() {
        // Arrange
        String token = "";

        Key stubbedSigningKey = new SecretKeySpec(new byte[1], SignatureAlgorithm.HS256.getJcaName());

        Mockito.when(mockJwtConfig.getSigningKey()).thenReturn(stubbedSigningKey);

        // Act
        Principal condition = sut.extractRequesterDetails(token);

        // Assert
        assertNull(condition);
    }

}