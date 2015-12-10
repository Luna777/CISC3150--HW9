//CISC3150
//Xin Guan
//12/8/15
//
// This program MUST run on Window System! 
// 
// This program accpets 3 kinds of input: "[command]", "[command]&" and "[command]& > output.txt"
// 	command, ex: cmd, notepad, calc or anything is .exe
//	command with & at the end, ex: notepad&, calc&
//	command(either with & or not) + output redirection, ex: cmd& > cmdOutput.txt
//
// 1.For detect the input string:
// 		if it has '>', separate the string into sub1 and sub2; 
// 		sub1 = the substring before '>', the command; 
// 		sub2 = the substring after '>', the output redirection file.
// 		if it doesn't have '>', sub1 = input.
//
// 2.use process builder to run command, sub1.
//		if sub1 ends with '&', don't wait for the process; else wait for the process end.
// 		if sub2 is not empty, redirect the output path to sub2; else, do nothing.
//
// ProcessBuilder examples reference:
// http://www.dotnetperls.com/processbuilder


import java.io.File;
import java.util.*;

public class HW10{
	
	public static void main(String[] args) throws Throwable{
		
		Scanner sc = new Scanner(System.in);
		String input = new String();
		String sub1="", sub2="";
		
		while(true){

			System.out.print("enter your command> ");
			//get the input line
			input=sc.nextLine();
			
			//detect the input type. 
			//"[command]", "[command]&" or "[command]& > output.txt" 
			// sub1 holds command
			// sub2 holds output redirection path
			int i=0;
			while(input.charAt(i)!='>' && i< input.length()-1){			
				i++;
			}
			
			if(i==input.length()-1){
				sub1=input;
			}
			else{
				sub1=input.substring(0, i-1);
				sub2=input.substring(i+2, input.length());
			}
			
			//for debugging
			//System.out.println(sub1);
			//System.out.println(sub2);
			
			//run the command calling ProcessBuilder
			//if sub1 ends with '&', don't wait for the process; else wait for the process end.
			//if sub2 is not empty, redirect the output path to sub2; else, do nothing.
			if(sub1.endsWith("&")){
				ProcessBuilder pb = new ProcessBuilder(sub1.substring(0, sub1.length()-1));

				if(sub2!=""){
					pb.redirectOutput(new File(sub2));
				}
				pb.start();
				System.out.println("executed:" + pb.command());//the command message
				if(sub2!=""){
					System.out.println("redirect output to: "+sub2);//the redirection message
				}

			}		
			else{
				
				ProcessBuilder pb = new ProcessBuilder(sub1);
				if(sub2!=""){
					pb.redirectOutput(new File(sub2));
				}
				Process p = pb.start();
				p.waitFor();
				System.out.println("executed:" + pb.command());//the command message
				if(sub2!=""){
					System.out.println("redirect output to: "+sub2);//the redirection message
				}
			}	
		}	
		
	}

}