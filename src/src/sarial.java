package src;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Serial1 implements Serializable {
	public static void serialize() {
		try {
			FileOutputStream file = new FileOutputStream("TranscriptSerialized.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(String.format("%20s %20s %30s %20s %20s\n", "SchoolName", "StudentName", "StudentMail",
					"Subject", "Mark"));
			for (int i = 0; i < Main.mySchool.studentList.size(); i++) {
				for (int j = 0; j < Main.mySchool.subjectArray.size(); j++) {
					out.writeObject(String.format("%20s %20s %30s %20s %20s\n", Main.mySchool.name,
							Main.mySchool.studentList.get(i).name, Main.mySchool.studentList.get(i).emailAddresses,
							Main.mySchool.subjectArray.get(j),
							Main.mySchool.studentList.get(i).studentSubjectList.get(j).mark.marks));
				}
			}
			out.close();
			file.close();
			System.out.println("serialized and saved");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

// // Deserialization
	public static void dserialize() {
			try {
				// Reading the object from a file
				FileInputStream file = new FileInputStream("TranscriptSerialized.txt");
				ObjectInputStream in = new ObjectInputStream(file);
				// Method for deserialization of object
				try {
					while (true) {
						String deserelizeObj = (String) in.readObject();
						System.out.println(deserelizeObj);
					}
				} 
				catch (EOFException e) {
				}
				in.close();
				file.close();
				System.out.println("Object has been deserialized ");
			} 
			catch (IOException ex) {
				System.out.println("IOException is caught");
			}
		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}

	}
}