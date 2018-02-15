package com.hkschool.rest.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class BaseController {

	public String getToken(String email, Long id){
		String token = Jwts.builder().setSubject(email).setId(id+"").signWith(SignatureAlgorithm.HS512, "DAAN.COM").compact();
		return token;
	};
	
	public String getIdFromToken(String token){
		String userId = null;
		if(token != null) {
			Jws<Claims> jws = Jwts.parser().setSigningKey("DAAN.COM").parseClaimsJws(token);
			userId = jws.getBody().getId();
		}
		return userId;
	}
}
