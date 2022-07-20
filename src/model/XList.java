package model;
/**
 * ���ڴ洢�����list
 * @author YQQ
 *
 * @param <E>
 * E�洢�Ķ�����
 */
public interface XList<E> {

	/**
	 * 
	 * @param e
	 * ��ӵ�list�Ķ���
	 * @return
	 * ȷ������Ƿ�ɹ�
	 */
	public boolean addList(E e);

	/**
	 * 
	 * @param input
	 * �����������ַ���
	 * @param pattern
	 * ����ģʽ
	 * @return
	 * ����ֵȷ�������Ƿ�ɹ�
	 */
	public Integer search(String input, String pattern);// patternΪname��phone��idCard���Դ��л����ҷ�ʽ

	/**
	 * ɾ��ָ�������Ķ���
	 * @param e
	 * ����ֵ
	 * @return
	 */
	public boolean delete(Integer e);

	/**
	 * ���������������������
	 * @param e
	 * @return
	 */
	public E amend(Integer e);

	/**
	 * ��ȡ��������
	 * @param index
	 * @return
	 */
	public E get(Integer index);

	/**
	 * ��listд���ļ�
	 * @param dir
	 * �ļ�·��
	 */
	public void writeList(String dir);

	/**
	 * ��ȡ�ļ���list
	 * @param dir
	 * �ļ�·��
	 */
	public void readList(String dir);
}
