package org.kaushal.school.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.kaushal.school.auditservice.AuditService;
import org.kaushal.school.dto.AuditDTO;
import org.kaushal.school.entity.EntitySuper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

	@Autowired
	private AuditService auditService;

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	@Pointcut("execution(* org.kaushal.school.service.*.*(..))")
	private void pointCutRef() {

	}

	// || @annotation(com.java4u.spring.arithmetic.Loggable))
	@Before("pointCutRef() ")
	public void beforeTask() {

	}

	@After("pointCutRef()")
	public void afterTask(JoinPoint point) {

	}

	@AfterReturning(pointcut = "pointCutRef()", returning = "retVal")
	public void doAfterReturnTask(JoinPoint point, Object retVal) {
		String entityName = point.getTarget().getClass().getName();
		// System.out.println("entity name: " + entityName);
		// System.out.println("entity name: " + point.getSignature().getName());
		AuditDTO auditDTO = new AuditDTO();
		auditDTO.setEntity(entityName);
		auditDTO.setOperation(point.getSignature().getName());
		auditDTO.setOpTime(new Date());
		auditDTO.setStatus(true);
		if (retVal instanceof EntitySuper) {
			System.out.println("Audit Refrence");
			auditDTO.setPrimaryKeyEntity(((EntitySuper) retVal).getPrimaryKey());
		}
		auditService.createAudit(auditDTO);

	}

	@AfterThrowing(pointcut = "pointCutRef()", throwing = "ex")
	public void doAfterThrowingTask(Exception ex) {
		System.out.println("Excepton Throwing " + ex);
	}
}
