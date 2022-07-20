package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * ����ӵ�а೵·�߹����������
 * @author YQQ
 *
 */
public class Traffic {

	private String id = "";
	private String name = "��";
	private String type = "";// ��·����(������)��
	private String begin;
	private String end;
	private String dail;// ��Ӫ����
	private String remark = "";
	private String allName = "";

	public Traffic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���ID
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * ����ID���޶���ʽΪX-001
	 * @param id
	 * Ҫ���õ�id
	 * @return
	 * �ж��Ƿ����óɹ�
	 */
	public boolean setId(String id) {
		String regEx = ".+?-[0-9]{1,3}";// ƥ��id��ʽ
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(id);

		if (matcher.find()) {
			this.id = matcher.group();
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
	 * ��������
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �������
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * ��������
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ��ÿ�ʼʱ��
	 * @return
	 */
	public String getBegin() {
		return begin;
	}

	/**
	 * ���ÿ�ʼʱ�䣬�޶���ʽX:XX
	 * @param begin
	 * @return
	 */
	public boolean setBegin(String begin) {
		String regEx = "[0-9]{1,2}:[0-9]{1,2}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(begin);

		if (matcher.find()) {
			this.begin = matcher.group();
			return true;
		}
		return false;

	}

	/**
	 * ��ý���ʱ��
	 * @return
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * ���ý���ʱ��
	 * @param end
	 * @return
	 */
	public boolean setEnd(String end) {
		String regEx = "[0-9]{1,2}:[0-9]{1,2}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(end);

		if (matcher.find()) {
			this.end = matcher.group();
			return true;
		}
		return false;
	}

	/**
	 * �����Ӫ����
	 * @return
	 */
	public String getDail() {
		return dail;
	}

	/**
	 * ������Ӫ����
	 * @param dail
	 */
	public void setDail(String dail) {
		this.dail = dail;
	}

	/**
	 * ��ñ�ע
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * ���ñ�ע
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * ������г˿�����
	 * @return
	 */
	public String getAllName() {
		return allName;
	}

	/**
	 * �������г˿�����
	 * @param allName
	 */
	public void setAllName(String allName) {

		this.allName = allName;
	}

}
