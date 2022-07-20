package model;
/**
 * 用于存储对象的list
 * @author YQQ
 *
 * @param <E>
 * E存储的对象类
 */
public interface XList<E> {

	/**
	 * 
	 * @param e
	 * 添加到list的对象
	 * @return
	 * 确认添加是否成功
	 */
	public boolean addList(E e);

	/**
	 * 
	 * @param input
	 * 用于搜索的字符串
	 * @param pattern
	 * 搜索模式
	 * @return
	 * 返回值确认搜索是否成功
	 */
	public Integer search(String input, String pattern);// pattern为name，phone，idCard，以此切换查找方式

	/**
	 * 删除指定索引的对象
	 * @param e
	 * 索引值
	 * @return
	 */
	public boolean delete(Integer e);

	/**
	 * 获得满足条件的索引对象
	 * @param e
	 * @return
	 */
	public E amend(Integer e);

	/**
	 * 获取索引对象
	 * @param index
	 * @return
	 */
	public E get(Integer index);

	/**
	 * 将list写入文件
	 * @param dir
	 * 文件路径
	 */
	public void writeList(String dir);

	/**
	 * 读取文件中list
	 * @param dir
	 * 文件路径
	 */
	public void readList(String dir);
}
