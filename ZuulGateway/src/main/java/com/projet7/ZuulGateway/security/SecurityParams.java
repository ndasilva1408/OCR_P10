package com.projet7.ZuulGateway.security;



public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="projet7@ocr";
    public static final long EXPIRATION=4*3600*1000;//4h
    public static final String HEADER_PREFIX="Bearer ";
}
