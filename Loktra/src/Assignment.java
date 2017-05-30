
class hashGame{
	String letters;
	
	
	hashGame(){
		letters="acdegilmnoprstuw";
	}
	
	String reverseString(String s){
		String reversedS="";
		for(int i=s.length()-1;i>=0;i--)
			reversedS+=s.charAt(i);
		
		return reversedS;
	}
	String hashToString(long hashed){
		String newString="";
		while(hashed>7){
			int i;
			
			for( i=0;i<letters.length();i++){
				if((hashed-i)%37==0){
					newString+=letters.charAt(i);
					hashed-=i;
					hashed/=37;
					break;
					
				}
			}
		}
		if(newString!="")
			newString=reverseString(newString);
		return newString;
	}
	
	
}

public class Assignment{
	public static void main(String[] args) {
		long hashed=680131659347l;
		System.out.println(""+new hashGame().hashToString(hashed));
	}
}