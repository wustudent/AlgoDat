
public class Pair<T> {
	private T first,second;
	Pair(T first, T second) {
		this.first=first;
		this.second=second;
	}

	public T getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}

	public void setFirst(T dt) {
		this.first=dt;
	}

	public void setSecond(T dt) {
		this.second=dt;
	}

	public void swap() {
		T tmp=this.first;
		this.first=this.second;
		this.second=tmp;
	}

	@Override
	public String toString() {
		try{
			String s1,s2;
			s1=this.first.toString();
			s2=this.second.toString();
			return s1+", "+s2;
		}
		catch(Exception err){
			return "<not implemented>, <not implemented>";
		}
		//return this.first.toString()+","+this.second.toString();
	}
}

