package com.hk.ssm4.entity;

public enum AbnormalState {
	/**
	 * 初始化
	 */
	StringI("0","待处理"),
	/**
	 * 已处理
	 */
	DEAL("1","待审核"),
	/**
	 * 驳回
	 */
	DISMISSED("2","驳回"),
	/**
	 * 成功
	 */
	SUCCESS("3","完成");
	
	private String value;
	
	private String desc;
	
	private AbnormalState(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static String getDesc(String value) {
        for (AbnormalState mccEnum : AbnormalState.values()) {
            if (mccEnum.getValue().equals(value)) {
                return mccEnum.getDesc();
            }
        }
        return null;
    }
	
	
}
