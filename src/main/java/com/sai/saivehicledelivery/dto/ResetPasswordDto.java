/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.dto;

/**
 *
 * @author Harsh Gawde
 */
public class ResetPasswordDto {

    private Integer loginId;
    private String loginName;
    private String password;
    private String nPassword;
    
   public ResetPasswordDto(){
       
   }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getnPassword() {
        return nPassword;
    }

    public void setnPassword(String nPassword) {
        this.nPassword = nPassword;
    }
   
   
   
}
