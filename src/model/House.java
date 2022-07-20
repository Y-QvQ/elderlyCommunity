package model;
/**
 * 房屋对象所属类
 * @author YQQ
 *
 */
public class House extends Architecture {

	private String remark = "无";

	/**
	 * 构造函数
	 */
	public House() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void autoSetState() {
		if (super.getMember().size() == 0) {
			super.setState(true);
		} else if (super.getMember().size() > 0) {// 有住户
			super.setState(false);
		}
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

	/*
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return super.toString(); }
	 */
}
