import java.util.Stack;

public class brace
{

	public static void main(String[] args)
	{
		bn b;
		b=new bn();
		System.out.println(b.bracematcher("{1+[2+(3+4)]}}"));

	}
	
	

}

class bn
{
	Boolean bracematcher(String x){
		Stack<Character> a= new Stack<Character>();
		String brackets="({[<)}]>";
		int pos=0;
		for(char c :x.toCharArray()){
			pos=brackets.indexOf(c);
			if(pos>=0&&pos<brackets.length()/2)
				a.push(brackets.charAt(pos+(brackets.length()/2)));
			else if(pos>=brackets.length()/2)
				if(!a.isEmpty()&&c==a.peek())
					a.pop();
				else
					return false;
		}
		if(a.isEmpty())
			return true;
		else
			return false;
	}
}
