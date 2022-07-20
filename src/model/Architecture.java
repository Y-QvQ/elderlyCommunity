package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

/**
 * 该类具有建筑类对象的基本属性
 * @author YQQ
 *
 */


public abstract class Architecture implements Serializable {

	
	private String id = "无-0-000";// A-1-0666,A区1号楼666号
	private boolean state = true;
	private ArrayList<Member> members = new ArrayList<>(); // 建筑内成员
	private String allName = "";// 获得表中所有名字

	public Architecture() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置id时匹配固定格式X-00-0000
	 * @param id
	 * @return 返回布尔值，确认设置是否成功
	 */
	public boolean setID(String id) {
		String regEx = ".+?-[1-9]{1,2}-[0-9]{3,4}";// 匹配id格式
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
	 * 返回id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * 该抽象方法用于实现自动改变建筑状态，子类中具体实现
	 */
	public abstract void autoSetState();// 子类中判定状态方式不同

	/**
	 * 设置建筑状态
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * 得到建筑状态
	 * @return
	 */
	public boolean getState() {
		autoSetState();
		return this.state;
	}

	/**
	 * 
	 * @param members
	 * 将传入的Member添加到容器中
	 * @return
	 * 返回值确认设置是否成功
	 */
	public boolean setMember(Member members) {
		if (members != null) {
			this.members.add(members);
			return true;
		}
		return false;
	}

	/**
	 * 返回members容器
	 * @return
	 */
	public ArrayList<Member> getMember() {
		return members;
	}

	/**
	 * 移除容器members中的指定对象member
	 * @param member
	 */
	public void remove(Member member) {
		members.remove(member);
	}

	/**
	 * 获得容器members中所有成员的姓名
	 */
	public void setAllName() {
		String str = "";
		for (Person person : members) {
			str += person.getName() + " ";
		}
		this.allName = str;
	}

	/**
	 * 得到所有名称
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
