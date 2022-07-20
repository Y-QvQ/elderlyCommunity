package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * 建筑的容器类
 * @author YQQ
 *
 */
public class ArchitectureList implements XList<Architecture> {

	private ArrayList<Architecture> list = new ArrayList<>();

	/**
	 * 构造函数
	 */
	public ArchitectureList() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加建筑到list中
	 */
	@Override
	public boolean addList(Architecture e) {
		return list.add(e);
	}

	/**
	 * 搜索对象并返回索引值，找不到返回空
	 */
	@Override
	public Integer search(String input, String pattern) {
		// TODO Auto-generated method stub
		if (pattern.equals("ID")) {
			for (Architecture architecture : list) {
				if (input.equals(architecture.getId())) {
					return list.indexOf(architecture);
				}
			}
		}

		return null;
	}

	/**
	 * 根据索引获得对象
	 */
	public Architecture get(Integer index) {
		return list.get(index);
	}

	/**
	 * @return
	 * 返回list
	 */
	public ArrayList<Architecture> getList() {
		return this.list;
	}

	/**
	 * @return
	 * 返回list中对象数量
	 */
	public Integer getSize() {
		return list.size();
	}


	@Override
	public boolean delete(Integer e) {
		// TODO Auto-generated method stub
		boolean remove = list.remove(e);
		return remove;
	}


	@Override
	public Architecture amend(Integer e) {
		// TODO Auto-generated method stub
		if (e >= 0 && e < list.size()) {
			return list.get(e);
		}
		return null;
	}


	@Override
	public void writeList(String dir) {// 序列化
		// TODO Auto-generated method stub
		ObjectOutputStream objout = null;
		try {
			File file = new File(dir);
			if (file.exists()) {
				objout = new ObjectOutputStream(new FileOutputStream(dir));

				try {
					objout.writeObject(list);
					objout.flush();// 刷新
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					if (objout != null) {
						objout.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void readList(String dir) {
		// TODO Auto-generated method stub
		ObjectInputStream objin = null;
		try {
			File file = new File(dir);
			if (file.exists()) {
				objin = new ObjectInputStream(new FileInputStream(dir));

				Object o = null;
				try {
					o = objin.readObject();
					ArrayList<Architecture> list = (ArrayList<Architecture>) o;
					this.list = list;

				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					if (objin != null) {
						try {
							objin.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
