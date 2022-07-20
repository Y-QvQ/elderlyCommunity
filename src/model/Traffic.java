package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 该类拥有班车路线管理相关属性
 * @author YQQ
 *
 */
public class Traffic {

	private String id = "";
	private String name = "无";
	private String type = "";// 线路方向(上下行)等
	private String begin;
	private String end;
	private String dail;// 运营日期
	private String remark = "";
	private String allName = "";

	public Traffic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获得ID
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置ID，限定格式为X-001
	 * @param id
	 * 要设置的id
	 * @return
	 * 判断是否设置成功
	 */
	public boolean setId(String id) {
		String regEx = ".+?-[0-9]{1,3}";// 匹配id格式
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(id);

		if (matcher.find()) {
			this.id = matcher.group();
			return true;
		}
		return false;
	}

	/**
	 * 获得名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得类型
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置类型
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获得开始时间
	 * @return
	 */
	public String getBegin() {
		return begin;
	}

	/**
	 * 设置开始时间，限定格式X:XX
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
	 * 获得结束时间
	 * @return
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * 设置结束时间
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
	 * 获得运营日期
	 * @return
	 */
	public String getDail() {
		return dail;
	}

	/**
	 * 设置运营日期
	 * @param dail
	 */
	public void setDail(String dail) {
		this.dail = dail;
	}

	/**
	 * 获得备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * 获得所有乘客名称
	 * @return
	 */
	public String getAllName() {
		return allName;
	}

	/**
	 * 设置所有乘客名称
	 * @param allName
	 */
	public void setAllName(String allName) {

		this.allName = allName;
	}

}
