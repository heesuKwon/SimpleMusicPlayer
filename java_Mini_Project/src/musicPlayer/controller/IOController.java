package musicPlayer.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import musicPlayer.model.vo.Music;


public class IOController{

	/**
	 * 회원정보 파일출력(저장)
	 * @param m
	 * @param fileName
	 * @return
	 */
	public boolean saveList(List list, String fileName){
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));){
			oos.writeObject(list);
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	/**
	 * 회원정보 파일읽기(데이터로드)
	 * @param fileName
	 * @return
	 */
	public Object loadList(String fileName){
		List list = null;
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));){
			list = (List)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {	//readObject메소드가 던짐.
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
}
