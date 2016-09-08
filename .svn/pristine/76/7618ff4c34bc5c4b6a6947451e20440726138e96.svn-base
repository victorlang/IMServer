//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//对Redis, 传文字消息用JSON更有优势。不过也考虑一下是否需要GZIP压缩。 暂时不写进代码里去。。 
public class SerializeCompTools {
	private static byte[] compressObj(Serializable o) throws IOException {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    GZIPOutputStream zos = new GZIPOutputStream(bos);
	    ObjectOutputStream ous = new ObjectOutputStream(zos);

	    ous.writeObject(o);
	    zos.finish();
	    bos.flush();

	    return bos.toByteArray();
	}
	private static Object restore(byte[] bytes) throws IOException, ClassNotFoundException 
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		GZIPInputStream gzipIn = new GZIPInputStream(bais);
		ObjectInputStream objectIn = new ObjectInputStream(gzipIn);
		Object myObj1 =  objectIn.readObject();
		objectIn.close();
		return myObj1;
	}
	 public static String compress(String str) throws IOException {   
		    if (str == null || str.length() == 0) {   
		     return str;   
		   }   
		    ByteArrayOutputStream out = new ByteArrayOutputStream();   ;
		   GZIPOutputStream gzip = new GZIPOutputStream(out);   
		    gzip.write(str.getBytes());   
		    gzip.close();   
		   return new BASE64Encoder().encode(out.toByteArray()); 
		  }   
		  
		  // 解压缩   
		 public static String uncompress(String str) throws IOException {   
		    if (str == null || str.length() == 0) {   
		      return str;   
		  }   
		    byte[] bytes  = new BASE64Decoder().decodeBuffer(str);
		   ByteArrayOutputStream out = new ByteArrayOutputStream();   
		   ByteArrayInputStream in = new ByteArrayInputStream(bytes);   
		    GZIPInputStream gunzip = new GZIPInputStream(in);   
		    byte[] buffer = new byte[256];   
		    int n;   
		   while ((n = gunzip.read(buffer))>= 0) {   
		    out.write(buffer, 0, n);   
		    }   
		    // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)   
		    return out.toString();   
		  }  	
}
