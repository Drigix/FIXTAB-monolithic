package com.fixtab.app.security;

public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String MANAGER = "ROLE_MANAGER";

    public static final String EMPLOYEE = "ROLE_EMPLOYEE";

    public static final String ADMIN_PREAUTHORIZE = "hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\")";

    public static final String MANAGEMENT_PREAUTHORIZE = "hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")";

    public static final String COMPANY_PREAUTHORIZE = "hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\", \"" + AuthoritiesConstants.EMPLOYEE + "\")";

    private AuthoritiesConstants() {}
}
