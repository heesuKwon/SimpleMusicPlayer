package musicPlayer.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import musicPlayer.model.vo.Member;


public class MemberController {

	/**
	 * 회원정보 파일출력(저장)
	 * @param m
	 * @param fileName
	 * @return
	 */
	public boolean saveSurvey(Member m, String fileName){
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));){
			oos.writeObject(m);
			
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
	public Member loadSurvey(String fileName){
		Member vo = null;
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));){
			vo = (Member)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {	//readObject메소드가 던짐.
			e.printStackTrace();
		} 
		System.out.println(vo);
		return vo;
	}
}
