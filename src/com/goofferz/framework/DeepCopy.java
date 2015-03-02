package com.goofferz.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeepCopy {

	private Object oldObj;

	public DeepCopy(Object oldObj) {
		this.oldObj = oldObj;
	}

	public Object getDeepCopy() throws Exception {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);

			// serialize and pass the object
			oos.writeObject(oldObj);
			oos.flush();

			ByteArrayInputStream bin = new ByteArrayInputStream(
					bos.toByteArray());
			ois = new ObjectInputStream(bin);

			// return the new object
			return ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);

		} finally {
			oos.close();
			ois.close();
		}
	}
}
