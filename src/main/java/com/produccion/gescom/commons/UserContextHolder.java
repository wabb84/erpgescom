package com.produccion.gescom.commons;

public class UserContextHolder {
	private static final ThreadLocal<String> userName = new ThreadLocal<>();
	private static final ThreadLocal<Long> userId = new ThreadLocal<>();
	private static final ThreadLocal<Long> sociedadId = new ThreadLocal<>();

    public static void setUserName(String name) {
        userName.set(name);
    }
    public static String getUserName() {
        return userName.get();
    }
    
    public static void setUserId(Long iduser) {
        userId.set(iduser);
    }

    public static Long getUserId() {
        return userId.get();
    }
    
    public static void setSociedadId(Long idsociedad) {
    	sociedadId.set(idsociedad);
    }

    public static Long getSociedadId() {
        return sociedadId.get();
    }

    public static void clear() {
        userName.remove();
        userId.remove();
        sociedadId.remove();
    }
}