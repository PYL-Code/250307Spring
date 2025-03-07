package chap03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap03.spring.ChangePasswordService;
import chap03.spring.MemberDao;
import chap03.spring.MemberListPrinter;
import chap03.spring.MemberPrinter;
import chap03.spring.MemberRegisterService;

//@~~ import 안되면 pom.xml에서 spring 설정을 넣어야 된다.

@Configuration //컨테이너
public class AppCtx {
	
	@Bean //빈 객체 등록 //주로 쓰는 기능만 빈에 넣는다.
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() { //이 bean이 호출될 때 memberDao 객체가 이미 생성돼 있어야 한다는 명세서
		return new MemberRegisterService(memberDao()); //생성자를 사용하여 의존 주입 방식
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao()); //세터를 사용하여 의존 주입 방식
		return pwdSvc;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
}
