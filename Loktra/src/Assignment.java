
class HashGame{
	String letters;
	
	
	HashGame(){
		letters="acdegilmnoprstuw";
	}
	
	String reverseString(String s){
		String reversedS="";
		for(int i=s.length()-1;i>=0;i--)
			reversedS+=s.charAt(i);
		
		return reversedS;
	}
	String hashToString(String value){ //This function calculates and returns a string, if it exists
		long hashed=0;
		try{ //Checking if the string contains a valid long value
			hashed=Long.parseLong(value);
		}catch(Exception e){
			System.out.println("Kindly check the value you entered and try again");
			return null;
		}
		String newString="";
		while(hashed!= 7){
			int i;
			
			//We will divide the index of each character and check if it is divisible by 37 or not
			int flag=0;
			
			for( i=0;i<letters.length();i++){
				if((hashed-i)%37==0){
					newString+=letters.charAt(i);
					hashed-=i;
					hashed/=37;
					flag =1;
					break;
					
				}
			}
			if(flag==0){
				System.out.println("No string found for this value");
				return null;
			}
		}
		if(newString.isEmpty()==false)
			newString=reverseString(newString);
		return newString;
	}
	
	
}

public class Assignment{
	public static void main(String[] args) {
		HashGame hg= new HashGame();
		String result=hg.hashToString(args[0]);
		if(result!=null){
			System.out.println("The String is "+result);
		}
	}
}
