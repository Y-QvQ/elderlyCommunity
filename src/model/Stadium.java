package model;

public class Stadium extends Architecture {

	String name = "无";// 场馆名字
	int size = 0;// 场馆容纳人数
	int begin = 0;// 开始时间
	int end = 0;// 结束时间

	public Stadium() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void autoSetState() {
		if (super.getMember().size() == this.size) {// 人满
			super.setState(false);
		} else if (super.getMember().size() > 0 && super.getMember().size() < size) {// 有空余位置
			super.setState(true);
		}
	}

	/**
	 * 设置姓名
	 * @param name
	 * 要设置的姓名
	 * @return
	 */
	public boolean setName(String name) {
		if (name != null) {
			this.name = name;
			return true;
		}
		return false;
	}

	/**
	 * 获得姓名
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置场馆大小
	 * @param size
	 * @return
	 */
	public boolean setSize(Integer size) {
		if (size != null) {
			this.size = size;
			return true;
		}
		return false;
	}

	/**
	 * 获得场馆大小
	 * @return
	 */
	public Integer getSize() {
		return this.size;
	}

	/**
	 * 设置开始时间
	 * @param begin
	 * @return
	 */
	public boolean setBegin(Integer begin) {
		if (begin != null && begin >= 5 && begin <= 23) {// 5-23点间开放
			this.begin = begin;
			return true;
		}
		return false;
	}

	/**
	 * 获得开始时间
	 * @return
	 */
	public Integer getBegin() {
		return this.begin;
	}

	/**
	 * 设置截止时间
	 * @param end
	 * @return
	 */
	public boolean setEnd(Integer end) {
		if (end != null && end >= 5 && end < 23) {
			this.end = end;
			return true;
		}
		return false;
	}

	/**
	 * 获得截止时间
	 * @return
	 */
	public Integer getEnd() {
		return this.end;
	}

	/*
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return super.toString(); }
	 */

}
