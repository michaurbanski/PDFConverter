package converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
public class TesseractClass {
	private static String FileName;
	private static String result;
	private static LocalDateTime date;
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		FileWriter Writer = null;
		
		File folder = new File(".");
		String path = folder.getCanonicalPath();
		System.out.println(path);
		File[] listOfFiles = folder.listFiles();
		
		
		for (File f : listOfFiles) {
		    if (f.isFile() && (f.getName().endsWith("f")) || f.getName().endsWith("bmp")
		    		|| f.getName().endsWith("g") || f.getName().endsWith("b")) {
		        System.out.println(f.getName());
		        String names = f.getName();
		        
		        
		        File imageFile = new File(names);
		        ITesseract instance = new Tesseract();  // JNA Interface Mapping
//		         ITesseract instance1 = new Tesseract1(); // JNA Direct Mapping
//		        instance.setDatapath("tessdata"); // path to tessdata directory

		        try {
		           result = instance.doOCR(imageFile);
		            System.out.println(result);
		            
		            
		        } catch (TesseractException e) {
		            System.err.println(e.getMessage());		           
		        }
		        
		        date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		        FileName = names +"_" + date;
		        System.out.println(FileName.replace('-', '_').replace(':', '_'));
		        
		        
		        if (!result.isEmpty()) {
		        try {        	
					Writer = new FileWriter(FileName.replace('-', '_').replace(':', '_')+".txt");
					Writer.write(result);
					Writer.flush();
					Writer.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        }       
		    }
		}
		 System.out.println("Finito.");
	}
   
}
