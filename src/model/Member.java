package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
/**
 * �û���
 * @author YQQ
 *
 */
public class Member extends Person {

	private static final long serialVersionUID = 2;
	private String type = "��";
	private String remark = "��";
	private String password = "c4d038b4bed09fdb1471ef51ec3a32cd";// ����Ĭ������114514��MD5
	private House house = new House();
	private Staff staff = new Staff();
	private ArrayList<Member> kinsfolks = new ArrayList<>();

	/**
	 * �û��๹�캯��
	 * @param name
	 * @param age
	 * @param phone
	 * @param idCard
	 */
	public Member(String name, int age, String phone, String idCard) {
		super(name, age, phone, idCard);
	}

	/**
	 * �û��๹�캯��
	 */
	public Member() {

	}

	/**
	 * �����û�����
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
	 * ����û�����
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * ���ñ�ע
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
	 * ��ñ�ע
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * �����û����룬�����û������MD5ֵ	
	 * @param password
	 * @return
	 */
	public boolean setPasswork(String password) {// �����û����������MD5ֵ��߰�ȫ��
		if (password != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");// ����һ��MD5���ܼ���ժҪ
				md.update(password.getBytes());// ����md5����
				/**
				 * digest()���ȷ������md5 hashֵ������ֵΪ8λ�ַ����� ��Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
				 * BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
				 * һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η���
				 */
				String hashedPwd = new BigInteger(1, md.digest()).toString(16);// 16�Ǳ�ʾת��Ϊ16������
				this.password = hashedPwd;
				return true;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * ����û������MD5ֵ
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * �����û�ס��
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
	 * ����û�ס��
	 * @return
	 */
	public House getHouse() {
		return house;
	}

	/**
	 * �����û��ܼ�
	 * @param staff
	 * Ҫ���õ��û��ܼ�
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
	 * ����û��ܼ�
	 * @return
	 */
	public Staff getStaff() {
		return staff;
	}
	/**
	 * �����������Ӧ��list��
	 * @param kinsfolk
	 * Ҫ��ӵ�����
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
	 * �õ�����������list
	 * @return
	 */
	public ArrayList<Member> getKinsfolks() {
		return kinsfolks;
	}

	/**
	 * ������list�Ƴ�ָ������
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
