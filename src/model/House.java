package model;
/**
 * ���ݶ���������
 * @author YQQ
 *
 */
public class House extends Architecture {

	private String remark = "��";

	/**
	 * ���캯��
	 */
	public House() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void autoSetState() {
		if (super.getMember().size() == 0) {
			super.setState(true);
		} else if (super.getMember().size() > 0) {// ��ס��
			super.setState(false);
		}
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

	/*
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return super.toString(); }
	 */
}
