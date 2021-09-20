package com.books.management.booksmanagement.entities;

public class AuthorizationResponse {

    private boolean isValidated;
    private String roleType;


    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }
}
