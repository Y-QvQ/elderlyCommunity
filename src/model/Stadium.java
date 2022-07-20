package model;

public class Stadium extends Architecture {

	String name = "��";// ��������
	int size = 0;// ������������
	int begin = 0;// ��ʼʱ��
	int end = 0;// ����ʱ��

	public Stadium() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void autoSetState() {
		if (super.getMember().size() == this.size) {// ����
			super.setState(false);
		} else if (super.getMember().size() > 0 && super.getMember().size() < size) {// �п���λ��
			super.setState(true);
		}
	}

	/**
	 * ��������
	 * @param name
	 * Ҫ���õ�����
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
	 * �������
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ���ó��ݴ�С
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
	 * ��ó��ݴ�С
	 * @return
	 */
	public Integer getSize() {
		return this.size;
	}

	/**
	 * ���ÿ�ʼʱ��
	 * @param begin
	 * @return
	 */
	public boolean setBegin(Integer begin) {
		if (begin != null && begin >= 5 && begin <= 23) {// 5-23��俪��
			this.begin = begin;
			return true;
		}
		return false;
	}

	/**
	 * ��ÿ�ʼʱ��
	 * @return
	 */
	public Integer getBegin() {
		return this.begin;
	}

	/**
	 * ���ý�ֹʱ��
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
	 * ��ý�ֹʱ��
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
