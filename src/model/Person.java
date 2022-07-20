package model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
/**
 * �������еĻ�������
 * @author YQQ
 *
 */
public class Person implements Serializable {

	
	private static final long serialVersionUID = 1;
	private String name;
	private int age;
	private String phone;// �绰�ţ�ͷ������
	private String idCard;// 18λ���֤
	private String gender;// �����֤�ж�,��/Ů
	private String birthdy;// �����֤��ȡ,yyyy��MM��dd��
	private String allName = "";

	/**
	 * ���캯��
	 * @param name
	 * @param age
	 * @param phone
	 * @param idCard
	 */
	public Person(String name, int age, String phone, String idCard) {
		setName(name);
		setAge(age);
		setPhone(phone);
		setIDCard(idCard);
	}

	/**
	 * ���캯��
	 */
	public Person() {

	}

	/**
	 * ��������������2-4������
	 * @param name
	 * Ҫ���õ�����
	 * @return
	 */
	public boolean setName(String name) {
		String regEx = "[\u4E00-\u9FA5]{2,5}(?:��[\u4E00-\u9FA5]{2,5})*";// ƥ��������
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(name);

		if (matcher.find()) {
			this.name = matcher.group();
			return true;
		}
		return false;
	}

	/**
	 * �������
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * �������䣬����������������Χ��
	 * @param age
	 * @return
	 */
	public boolean setAge(int age) {
		if (age <= 200 && age >= 0) {
			this.age = age;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getAge() {
		return this.age;
	}

	/**
	 * ���ú��룬����11λ���룬���ÿ�ͷ
	 * @param phone
	 * Ҫ���õĵ绰��
	 * @return
	 */
	public boolean setPhone(String phone) {
		String regEx = "^1[3-9]\\d{9}$";// ����ƥ��绰�Ÿ�ʽ
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(phone);

		if (matcher.find()) {
			this.phone = matcher.group();
			return true;
		}
		return false;
	}

	/**
	 * ��õ绰��
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * �������֤���������֤�����Ա������
	 * @param idCard
	 * Ҫ���õ����֤
	 * @return
	 */
	public boolean setIDCard(String idCard) {
		if (IDCardUtil.isValid(idCard)) {// ������֤��ʽ
			this.idCard = idCard;
			this.setGender();// �Զ������Ա�
			this.setBirthday();// �Զ���������
			return true;
		}
		return false;
	}

	/**
	 * ������֤
	 * @return
	 */
	public String getIDCard() {
		return idCard;
	}

	/**
	 * �������֤�����Ա�
	 */
	public void setGender() {
		this.gender = IDCardUtil.getSex(idCard);
	}

	/**
	 * ����Ա�
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * �������֤��������
	 */
	public void setBirthday() {
		this.birthdy = IDCardUtil.getBirthday(idCard);
	}

	/**
	 * �������
	 * @return
	 */
	public String getBirthday() {
		return birthdy;
	}

	/*
	 * public String toString() {
	 * 
	 * Gson gson=new Gson(); String str=gson.toJson(this); return str; }
	 */

	/**
	 * ���list����������
	 * @return
	 */
	public String getAllName() {
		return allName;
	}

	/**
	 * ����list��������
	 * @param allName
	 */
	public void setAllName(String allName) {
		this.allName = allName;
	}

	/**
	 * ��дequals�����֤��ͬʱ�ж�Ϊͬһ���û�
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			return getIDCard().equals(((Person) obj).getIDCard());
		}
		return false;
	}

}
