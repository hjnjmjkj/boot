package com.hk.ssm4.entity;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.hibernate.validator.constraints.ScriptAssert;

@ScriptAssert(script = "_this.password==_this.confirmation", lang = "javascript", alias = "_this", message = "账号密码不相同") 
public class PayRequestDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 支付完成时间
     **/
    @NotEmpty(message="支付完成时间不能空",groups={Default.class,First.class})
    @Size(max=14,message="支付完成时间长度不能超过{max}位")
    private String payTime;
     
    /**
     * 状态
     **/
    @NotEmpty(message="状态不能空",groups={Default.class,Second.class})
    @Pattern(regexp = "0[0123]", message = "状态只能为00或01或02或03")
    private String status;
    
    private String password;
    private String confirmation;
    /*
    @ParameterScriptAssert(lang = "javascript", script = "password==_this.confirmation")
    public void changePassword(String password, String confirmation) {  
  
    }*/
    @AssertTrue(message="两次密码要一致")
    public boolean getTest(){
    	return StringUtils.equals(password, confirmation);
    }
 
    public String getPayTime() {
        return payTime;
    }
 
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	
    
}
