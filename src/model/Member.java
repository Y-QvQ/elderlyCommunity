package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
/**
 * 用户类
 * @author YQQ
 *
 */
public class Member extends Person {

	private static final long serialVersionUID = 2;
	private String type = "无";
	private String remark = "无";
	private String password = "c4d038b4bed09fdb1471ef51ec3a32cd";// 设置默认密码114514的MD5
	private House house = new House();
	private Staff staff = new Staff();
	private ArrayList<Member> kinsfolks = new ArrayList<>();

	/**
	 * 用户类构造函数
	 * @param name
	 * @param age
	 * @param phone
	 * @param idCard
	 */
	public Member(String name, int age, String phone, String idCard) {
		super(name, age, phone, idCard);
	}

	/**
	 * 用户类构造函数
	 */
	public Member() {

	}

	/**
	 * 设置用户类型
	 * @param type
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
	 * 获得用户类型
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置备注
	 * @param remark
	 * @return
	 */
	public boolean setRemark(String remark) {
		if (remark != null) {
			this.remark = remark;
			return true;
		}
		return false;
	}

	/**
	 * 获得备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置用户密码，保存用户密码的MD5值	
	 * @param password
	 * @return
	 */
	public boolean setPasswork(String password) {// 保存用户输入密码的MD5值提高安全性
		if (password != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");// 生成一个MD5加密计算摘要
				md.update(password.getBytes());// 计算md5函数
				/**
				 * digest()最后确定返回md5 hash值，返回值为8位字符串。 因为md5 hash值是16位的hex值，实际上就是8位的字符
				 * BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
				 * 一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
				 */
				String hashedPwd = new BigInteger(1, md.digest()).toString(16);// 16是表示转换为16进制数
				this.password = hashedPwd;
				return true;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * 获得用户密码的MD5值
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置用户住房
	 * @param house
	 * @return
	 */
	public boolean setHouse(House house) {
		if (house != null) {
			this.house = house;
			return true;
		}
		return false;
	}

	/**
	 * 获得用户住房
	 * @return
	 */
	public House getHouse() {
		return house;
	}

	/**
	 * 设置用户管家
	 * @param staff
	 * 要设置的用户管家
	 * @return
	 */
	public boolean setStaff(Staff staff) {
		if (staff != null) {
			this.staff = staff;
			return true;
		}
		return false;
	}

	/**
	 * 获得用户管家
	 * @return
	 */
	public Staff getStaff() {
		return staff;
	}
	/**
	 * 添加亲属到对应的list中
	 * @param kinsfolk
	 * 要添加的亲属
	 * @return
	 */
	public boolean addKinsfolks(Member kinsfolk) {
		if (kinsfolk != null) {
			this.kinsfolks.add(kinsfolk);
			return true;
		}
		return false;
	}
	/**
	 * 得到保存亲属的list
	 * @return
	 */
	public ArrayList<Member> getKinsfolks() {
		return kinsfolks;
	}

	/**
	 * 从亲属list移除指定对象
	 * @param member
	 */
	public void removeKinsfolks(Member member) {
		kinsfolks.remove(member);
	}

	/*
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return super.toString(); }
	 */
	@Override
	public void setAllName(String allName) {
		String str = "";
		for (Person person : kinsfolks) {
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
