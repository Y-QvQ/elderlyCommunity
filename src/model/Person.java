package model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
/**
 * 人所具有的基础属性
 * @author YQQ
 *
 */
public class Person implements Serializable {

	
	private static final long serialVersionUID = 1;
	private String name;
	private int age;
	private String phone;// 电话号，头像名称
	private String idCard;// 18位身份证
	private String gender;// 由身份证判断,男/女
	private String birthdy;// 由身份证获取,yyyy年MM月dd日
	private String allName = "";

	/**
	 * 构造函数
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
	 * 构造函数
	 */
	public Person() {

	}

	/**
	 * 设置姓名，限制2-4个中文
	 * @param name
	 * 要设置的姓名
	 * @return
	 */
	public boolean setName(String name) {
		String regEx = "[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*";// 匹配中文名
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(name);

		if (matcher.find()) {
			this.name = matcher.group();
			return true;
		}
		return false;
	}

	/**
	 * 获得姓名
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置年龄，限制在人类正常范围内
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
	 * 设置号码，限制11位号码，常用开头
	 * @param phone
	 * 要设置的电话号
	 * @return
	 */
	public boolean setPhone(String phone) {
		String regEx = "^1[3-9]\\d{9}$";// 正则匹配电话号格式
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(phone);

		if (matcher.find()) {
			this.phone = matcher.group();
			return true;
		}
		return false;
	}

	/**
	 * 获得电话号
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置身份证并根据身份证设置性别和生日
	 * @param idCard
	 * 要设置的身份证
	 * @return
	 */
	public boolean setIDCard(String idCard) {
		if (IDCardUtil.isValid(idCard)) {// 检查身份证格式
			this.idCard = idCard;
			this.setGender();// 自动设置性别
			this.setBirthday();// 自动设置生日
			return true;
		}
		return false;
	}

	/**
	 * 获得身份证
	 * @return
	 */
	public String getIDCard() {
		return idCard;
	}

	/**
	 * 根据身份证设置性别
	 */
	public void setGender() {
		this.gender = IDCardUtil.getSex(idCard);
	}

	/**
	 * 获得性别
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * 根据身份证设置生日
	 */
	public void setBirthday() {
		this.birthdy = IDCardUtil.getBirthday(idCard);
	}

	/**
	 * 获得生日
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
	 * 获得list中所有姓名
	 * @return
	 */
	public String getAllName() {
		return allName;
	}

	/**
	 * 设置list所有姓名
	 * @param allName
	 */
	public void setAllName(String allName) {
		this.allName = allName;
	}

	/**
	 * 重写equals当身份证相同时判断为同一个用户
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			return getIDCard().equals(((Person) obj).getIDCard());
		}
		return false;
	}

}
