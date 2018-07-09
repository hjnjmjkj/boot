package com.hk.ssm4.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.hibernate.validator.HibernateValidator;

import com.hk.ssm4.entity.First;
import com.hk.ssm4.entity.PayRequestDto;
import com.hk.ssm4.entity.Second;

public class ValidationUtils {
	/**
     * 使用hibernate的注解来进行验证
     * 
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, Default.class);
        // 抛出检验异常
        if (!constraintViolations.isEmpty()) {
            StringBuffer sb=new StringBuffer();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage());
            }
            //throw new RuntimeException(sb.toString());
            //throw new ConstraintViolationException(constraintViolations);
        }

    }
    public static void main(String[] args) {
    	PayRequestDto dto = new PayRequestDto();
    	dto.setPassword("1");
    	dto.setConfirmation("1");
    	ValidationUtils.validate(dto);
	}
}
