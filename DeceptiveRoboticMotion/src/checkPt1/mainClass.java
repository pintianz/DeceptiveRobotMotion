package checkPt1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import checkPt1.Controller.twoGoalController;

public class mainClass {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		BigDecimal deltaBg=new BigDecimal("0.01");
		BigDecimal oneBg=new BigDecimal("1");
		
		PrintWriter writer = new PrintWriter("resultExaggerrate.txt", "UTF-8");
		
		for(BigDecimal ct=new BigDecimal("0.7"); ct.compareTo(oneBg)!=1; ct = ct.add(deltaBg)){
			for(BigDecimal dt=new BigDecimal("0.1"); dt.compareTo(oneBg)!=1; dt = dt.add(deltaBg)){
				twoGoalController tgc = new twoGoalController(ct.floatValue(), dt.floatValue(), writer);
				tgc.runTest("TESTEST");
			}
		}
		writer.close();

	}

}
