package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

/**
 * ������н��������Ļ�������
 * @author YQQ
 *
 */


public abstract class Architecture implements Serializable {

	
	private String id = "��-0-000";// A-1-0666,A��1��¥666��
	private boolean state = true;
	private ArrayList<Member> members = new ArrayList<>(); // �����ڳ�Ա
	private String allName = "";// ��ñ�����������

	public Architecture() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����idʱƥ��̶���ʽX-00-0000
	 * @param id
	 * @return ���ز���ֵ��ȷ�������Ƿ�ɹ�
	 */
	public boolean setID(String id) {
		String regEx = ".+?-[1-9]{1,2}-[0-9]{3,4}";// ƥ��id��ʽ
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(id);

		if (matcher.find()) {
			this.id = matcher.group();
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return
	 * ����id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * �ó��󷽷�����ʵ���Զ��ı佨��״̬�������о���ʵ��
	 */
	public abstract void autoSetState();// �������ж�״̬��ʽ��ͬ

	/**
	 * ���ý���״̬
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * �õ�����״̬
	 * @return
	 */
	public boolean getState() {
		autoSetState();
		return this.state;
	}

	/**
	 * 
	 * @param members
	 * �������Member��ӵ�������
	 * @return
	 * ����ֵȷ�������Ƿ�ɹ�
	 */
	public boolean setMember(Member members) {
		if (members != null) {
			this.members.add(members);
			return true;
		}
		return false;
	}

	/**
	 * ����members����
	 * @return
	 */
	public ArrayList<Member> getMember() {
		return members;
	}

	/**
	 * �Ƴ�����members�е�ָ������member
	 * @param member
	 */
	public void remove(Member member) {
		members.remove(member);
	}

	/**
	 * �������members�����г�Ա������
	 */
	public void setAllName() {
		String str = "";
		for (Person person : members) {
			str += person.getName() + " ";
		}
		this.allName = str;
	}

	/**
	 * �õ���������
	 * @return
	 */
	public String getAllName() {
		setAllName();
		return allName;
	}

	/*
	 * public String toString() {
	 * 
	 * Gson gson=new Gson(); String str=gson.toJson(this); return str; }
	 */
}
