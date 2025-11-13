package com.storix.spring_vote_22nd.global.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

    private final UserDetailsService userDetailsService;
    @Value("${JWT_SECRET_KEY}") private String secret;
    @Value("${JWT_ACCESS_TOKEN_VALIDITY_MS}") private long accessValidityMs;
    @Value("${JWT_REFRESH_TOKEN_VALIDITY_MS}") private long refreshValidityMs;


    private Key key;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes); // HMAC-SHA
    }

    /** 요청 헤더에서 Bearer 토큰만 추출 */
    public String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    /** 액세스 토큰 생성: subject = userId, roles = 권한 CSV */
    public String createAccessToken(Long id, Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessValidityMs);

        return Jwts.builder()
                .setSubject(String.valueOf(id))      // user 식별자
                .claim("roles", authorities)          // 권한
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** 리프레시 토큰 생성 */
    public String createRefreshToken(Long id) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshValidityMs);

        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** 토큰에서 사용자 ID(subject) 추출 */
    public String getTokenUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /** 토큰을 기반으로 Authentication 생성 (SecurityContext에 넣을 용도) */
    public Authentication getAuthentication(String token) {
        String userId = getTokenUserId(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(
                userDetails, token, userDetails.getAuthorities());
    }

    /** 유효성 검증 (서명/만료/포맷) */
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false; // 만료
        } catch (JwtException | IllegalArgumentException e) {
            return false; // 서명 불일치/변조/형식 오류
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /** 리프레시 토큰 만료 시간을 Date로 얻고 싶은 경우 */
    public Date getRefreshTokenExpiry(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getExpiration();
    }
}

