package chap03.spring;

public class ChangePasswordService {

	private MemberDao memberDao; //의존 생성 안 함 (new로 객체 생성 안 함)

	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);

		memberDao.update(member);
	}

	public void setMemberDao(MemberDao memberDao) { //setter 사용해서 의존 주입
		this.memberDao = memberDao;
	}

}
