package rs.ac.uns.ftn.web.synx.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Serializer {
	
	public synchronized static <T, U> void save(String path, Map<T, U> data) {
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(data);
			
			oos.close();
			fos.close();
			
		} 
		catch (FileNotFoundException fnf) {
			System.out.println("File not found");
			fnf.printStackTrace();	
		} 
		catch (IOException ioe) {
			System.out.println("IO exception");
			ioe.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized static <T, U> Map<T, U> load(String path) {
		
		Map<T, U> retVal = new HashMap<>();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
			
			retVal = (Map)ois.readObject();
			
			ois.close();
			fis.close();
		} 
		catch (FileNotFoundException fnf) {
			System.out.println("File not found");
			fnf.printStackTrace();
		}
		catch (IOException ioe) {
			System.out.println("IO exception");
			ioe.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			e.printStackTrace();
		}
		
		return retVal;
	}
}
