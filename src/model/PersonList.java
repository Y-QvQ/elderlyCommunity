package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersonList implements XList<Person> {

	private ArrayList<Person> list = new ArrayList<>();

	public PersonList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addList(Person e) {
		return list.add(e);
	}

	@Override
	public Integer search(String input, String pattern) {
		// TODO Auto-generated method stub

		if (pattern.equals("name")) {
			for (Person person : list) {
				if (input.equals(person.getName())) {
					return list.indexOf(person);
				}
			}
		} else if (pattern.equals("phone")) {
			for (Person person : list) {
				if (input.equals(person.getPhone())) {
					return list.indexOf(person);
				}
			}
		} else if (pattern.equals("idCard")) {
			for (Person person : list) {
				if (input.equals(person.getIDCard())) {
					return list.indexOf(person);
				}
			}
		}
		return -5;
	}


	/**
	 * 根据索引获得对象
	 */
	public Person get(Integer index) {
		return list.get(index);
	}

	/**
	 * 获得list中对象数目
	 * @return
	 */
	public Integer getSize() {
		return list.size();
	}

	/**
	 * 获得list
	 * @return
	 */
	public ArrayList<Person> getPersonList() {
		return list;
	}

	@Override
	public boolean delete(Integer e) {
		// TODO Auto-generated method stub
		boolean remove = list.remove(e);
		return remove;
	}

	@Override
	public Person amend(Integer e) {
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
					ArrayList<Person> list = (ArrayList<Person>) o;
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
