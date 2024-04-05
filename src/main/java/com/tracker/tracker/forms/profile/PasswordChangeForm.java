package com.tracker.tracker.forms.profile;

public class PasswordChangeForm {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public PasswordChangeForm setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public PasswordChangeForm setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public PasswordChangeForm setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
