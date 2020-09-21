package kr.co.goodee.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect //Aop를 사용하기 위한 어노테이션
public class LogAspect {

	/*kr.co.goodee.service.AopService.before() 를 실행 하기 전에 실행할 내용*/
	@Before("execution(* kr.co.goodee.service.AopService.before(..))") //point cut 표현식(메서드 지정)
	public void logBefore(JoinPoint jp) {//JoinPoint 는 실행되는 지점에 대한 정보를 담고 있다.
		System.out.println("**************");
		System.out.println("logBefore 메서드 실행");
		System.out.println("기준 메서드 : " + jp.getSignature().getName());
		System.out.println("**************");
	}
	
	@After("execution(* kr.co.goodee.service.AopService.after(..))")
	public void logAfter(JoinPoint jp) {
		System.out.println("**************");
		System.out.println("logAfter 메서드 실행");
		System.out.println("기준 메서드 : " + jp.getSignature().getName());
		System.out.println("**************");
	}
	
	//service에서 실행되고 반환되는 값을 result라는 변수명으로 먼저 받아 보겠다.
	//인자값이 2개인 경우 각 항목에 대한 이름이 있어야 한다.
	@AfterReturning(pointcut ="execution(* kr.co.goodee.service.AopService.afterreturning(..))", returning="result")
	public void logAfterReturning(JoinPoint jp, Object result) {
		System.out.println("**************");
		System.out.println("logAfterReturning 메서드 실행");
		System.out.println("기준 메서드 : " + jp.getSignature().getName());
		System.out.println("가로채서 받은 반환값 : " + result);
		System.out.println("**************");
	}
	
	@AfterThrowing(pointcut ="execution(* kr.co.goodee.service.AopService.afterthrowing(..))", throwing="e")
	public void logAfterThrowing(JoinPoint jp, Throwable e) {
		System.out.println("**************");
		System.out.println("logAfterThrowing 메서드 실행");
		System.out.println("기준 메서드 : " + jp.getSignature().getName());
		System.out.println("가로채서 받은 예외 값 : " + e.toString());
		System.out.println("**************");
	}
	
	@Around("execution(* kr.co.goodee.service.AopService.around(..))")
	public void logAround(ProceedingJoinPoint proc) throws Throwable {
		System.out.println("**************");
		System.out.println("logAround 메서드 실행");
		System.out.println("기준 메서드 이름 : " + proc.getSignature().getName());
		System.out.println("기준 메서드 인자 값 : " + Arrays.deepToString(proc.getArgs()));
		
		System.out.println("around() 실행 전 처리");
		proc.proceed();//해당 메서드 실행
		System.out.println("around() 실행 후 처리");
		
		System.out.println("**************");
	}
}
