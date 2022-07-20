package model;

import java.util.ArrayList;

public class Staff extends Person {

	private static final long serialVersionUID = 3;
	private String type;
	private ArrayList<Member> members = new ArrayList<>();

	public Staff(String name, int age, String phone, String idCard) {
		super(name, age, phone, idCard);
	}

	public Staff() {

	}

	/**
	 * 设置员工类型
	 * @param type
	 * 要设置的员工类型
	 * @return
	 */
	public boolean setType(String type) {
		if (type != null) {
			this.type = type;
			return true;
		}
		return false;
	}

	/**
	 * 获得要设置的类型
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 添加用户到容器members中
	 * @param members
	 * @return
	 */
	public boolean setMember(Member members) {
		if (members != null) {
			this.members.add(members);
			return true;
		}
		return false;
	}

	/**
	 * 获得容器
	 * @return
	 */
	public ArrayList<Member> getMember() {
		return members;
	}

	/**
	 * 从容器中移除指定用户
	 * @param member
	 * 要移除的用户
	 */
	public void removeMember(Member member) {
		members.remove(member);
	}

	/*
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return super.toString(); }
	 */

	@Override
	public void setAllName(String allName) {
		String str = "";
		for (Person person : members) {
			str += person.getName() + " ";
		}
		super.setAllName(str);
	}

	@Override
	public String getAllName() {
		setAllName(null);
		return super.getAllName();
	}

}
