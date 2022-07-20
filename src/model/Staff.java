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
	 * ����Ա������
	 * @param type
	 * Ҫ���õ�Ա������
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
	 * ���Ҫ���õ�����
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * ����û�������members��
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
	 * �������
	 * @return
	 */
	public ArrayList<Member> getMember() {
		return members;
	}

	/**
	 * ���������Ƴ�ָ���û�
	 * @param member
	 * Ҫ�Ƴ����û�
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
