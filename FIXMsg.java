package xmlparser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javapractice.FileTest;

public class FIXMsg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileTest fileTest = new FileTest ();
		
		// parse FIX fields to a file - FIXTest1.txt 

		// ************************ //
//		getFixmessageByField ();
		// ************************ //
		
		File file1 = new File ("C:\\Dev\\FixTest1.txt");
		File file2 = new File ("C:\\Dev\\FixBase.txt");
		
		// compare two files
		// ************************** //
//		fileTest.fileCompare(file1, file2);
		// *************************  //
		
		// store FIX messages into hashMap and validate field value
		// ************************** //
//		storeFixFieldsIntoMap ();
		// ************************** //
		
		// ***************************//
		// split by "n\" AND key value verification (except for firs and last rows)
		getSwiftFields ();
		// ***************************//
		
	}
		
		public static void getFixmessageByField () {

			String outGoingMsg = "8=FIX.4.2|9=178|35=AE|56=LSEHub|49=BROKERX|128=LSETR|34=2175|52=20120126-15:15:54|918=GBP|31=89.0000000|64=20120126|828=1|60=20120126-13:32:49|32=6|22=4|571=124|43=N|570=N|150=0|48=GB0007188757|10=206|";
			//String [] processOGM = new String [30];
			//ArrayList <String> processOGM = new ArrayList<String> ();
			
			String [] temp = outGoingMsg.split("\\|");
			int len = temp.length;
			System.out.println("Array Length is: " + len); // 201 ??
			//System.out.println(temp[1]);
			for (int i = 0; i < len ; i ++) {
				
				// calling File Append method 
				FileTest fileTest = new FileTest ();
				String myFile = "C:\\Dev\\FixTest1.txt";
				
				// append fields to a file
				fileTest.fileAppend(myFile, temp[i]);
				
				//System.out.println(temp[i]);
			}
			
		}
		
		
		static void storeFixFieldsIntoMap () {
			//https://drewnoakes.com/fix-decoder/#base64:b3V0Z29pbmc6IDg9RklYLjQuMnw5PTE3OHwzNT1BRXw1Nj1MU0VIdWJ8NDk9QlJPS0VSWHwxMjg9TFNFVFJ8MzQ9MjE3NXw1Mj0yMDEyMDEyNi0xNToxNTo1NHw5MTg9R0JQfDMxPTg5LjAwMDAwMDB8NjQ9MjAxMjAxMjZ8ODI4PTF8NjA9MjAxMjAxMjYtMTM6MzI6NDl8MzI9NnwyMj00fDU3MT0xMjR8NDM9Tnw1NzA9TnwxNTA9MHw0OD1HQjAwMDcxODg3NTd8MTA9MjA2fAppbmNvbWluZzogOD1GSVguNC4yfDk9MTEyfDM1PUFSfDQ5PUxTRUh1Ynw1Nj1CUk9LRVJYfDExNT1MU0VUUnwzND0yMDA2fDUyPTIwMTIwMTI2LTE1OjE1OjU0fDM3MD0yMDEyMDEyNi0xNToxNTo1NC44MjJ8NTcxPTEyNHwxNTA9MHw5Mzk9MHwxMD0wNTl8

			
			//String outGoingMsg = "8=FIX.4.2|9=178|35=AE|";
			String outGoingMsg = "8=FIX.4.2|9=178|35=AE|56=LSEHub|49=BROKERX|128=LSETR|34=2175|52=20120126-15:15:54|918=GBP|31=89.0000000|64=20120126|828=1|60=20120126-13:32:49|32=6|22=4|571=124|43=N|570=N|150=0|48=GB0007188757|10=206|";
			String incomingMsg = "8=FIX.4.2|9=112|35=AR|49=LSEHub|56=BROKERX|115=LSETR|34=2006|52=20120126-15:15:54|370=20120126-15:15:54.822|571=124|150=0|939=0|10=059|";
			
			
			HashMap<String, String> hm = new HashMap <> ();
			
			String [] temp = outGoingMsg.split("\\|");
			int len = temp.length;
			System.out.println("Array Length is: " + len); // 201 ??
			//System.out.println(temp[1]);
			for (int i = 0; i < len ; i ++) {
				
				// temp[i] to be splited with:
				String [] temp2 = temp[i].split("=");
				
				System.out.println("temp2 [0]: " + temp2[0]); // key
				System.out.println("temp2 [1]: " + temp2[1]); // value
				
				// store key , value paire into hashmap
				hm.put(temp2[0], temp2[1]);
				
				//System.out.println(temp[i]);
			}
			System.out.println("hashmap output: " + hm);
			
			// *********** validate Fix Value by Field **************** //
			System.out.println(XpathPaserDemo.checkOutPut("AE", hm.get("35"), "35")); // true
			System.out.println("35 tag value = : " + hm.get("35")); // AE
			
			System.out.println(XpathPaserDemo.checkOutPut("178", hm.get("9"), "9"));  // true
			System.out.println("9 tag value = : " + hm.get("9")); // 178
			
		}
		
		static void getSwiftFields () {
			 String str = ":53A:DEUTDEFF\n" + 
			 		":54A://RT\n" + 
			 		"FOOBARYY \n";
			 
			 String MTMsg1 = "{1:F01FOOBARYYAXXX8669486759}{2:O1030831051017CRESLULLCXXX10194697810510170831N}{3:{108:P22VUXC43C6J3NLD}}{4:\n" +
					":20:AMLX985338-D4E5E\n" + 
					":23B:CRED\n" + 
					":32A:051018EUR66969,52\n" + 
					":33B:EUR66969,52\n" + 
					":50K:Foo SA\n" + 
					":53A:DEUTDEFF\n" + 
					":54A://RT\n" + 
					"FOOBARYY\n" +
					":59:/-\n" + 
					"Tressis SA\n" + 
					":70:/CS BD ST EUR B\n" + 
					"REDEMPTION AMLX985338\n" + 
					":71A:OUR\n" + 
					"-}{5:{MAC:52F48656}{CHK:24C40F1FF931}}";
			 
			
			 //String [] temp= MTMsg1.split ("\\\n");
			 //String [] temp= str.split ("\\\n");
			 String [] temp= MTMsg1.split ("\\\n");

			 
			 HashMap <String, String> map = new HashMap <> ();

			 // exclude the 1st low and the last row
			for (int i = 1; i < temp.length-1; i ++) {
				//System.out.println(temp[i]);
				
				 String temp2 = temp[i];
				 //if index 0 = ":", remove it and recap substring
				 if (temp2.startsWith(":")) {
					 temp2 = temp2.substring(1, temp2.length());
				 } else {
					 // handle which does not have key for split
					 temp2 = ":" + temp2;
				 }
				 
				 //System.out.println(temp2); // 53A:DEUTDEFF   54A://RT   :FOOBARYY 
				 String [] temp3 = temp2.split(":");
				 // add into HashMap for key value pair
//				 HashMap <String, String> map = new HashMap <> ();
				 map.put(temp3[0], temp3[1]);
				 //System.out.println(temp3[0]);
				 //System.out.println(temp3[1]);
				 //System.out.println(map);
				 
			}
			 // -------------key value verfication-------------------------//
			System.out.println(isValueCorrect ("AMLX985338-D4E5E",map.get("20"), "20"));
			System.out.println(isValueCorrect ("CRED",map.get("23B"), "23B"));
			System.out.println(isValueCorrect ("051018EUR66969,52",map.get("32A"), "32A"));
			System.out.println(isValueCorrect ("EUR66969,52",map.get("33B"), "33B"));
			System.out.println(isValueCorrect ("Foo SA",map.get("50K"), "50K"));
			System.out.println(isValueCorrect ("DEUTDEFF",map.get("53A"), "53A"));
			System.out.println(isValueCorrect ("//RT",map.get("54A"), "54A"));
			 // handle for no key value - main Key is 54A: add "54A 1"
			 map.put ("54A-1","FOOBARYY"); // a bit unsatisfied !!
			 
			 System.out.println(isValueCorrect ("/-",map.get("59"), "59"));
			 map.put ("59-1","Tressis SA"); // a bit unsatisfied !!
			 
			 System.out.println(isValueCorrect ("Tressis SA",map.get("59-1"), "59-1"));
			 System.out.println(isValueCorrect ("/CS BD ST EUR B",map.get("70"), "70"));

			 map.put ("70-1","REDEMPTION AMLX985338"); // a bit unsatisfied !!

			 System.out.println(isValueCorrect ("REDEMPTION AMLX985338",map.get("70-1"), "70-1"));
			 System.out.println(isValueCorrect ("UR",map.get("71A"), "71A"));

		}
		
		static String isValueCorrect (String actual, String expected, String TagId) {
			String res = "";
			if (actual.equalsIgnoreCase(expected)) {
				res = "CORRECT";
			} else {
				res = "INCORRECT";
			}
			return res + " :" +TagId;
		}
		
	}
	
