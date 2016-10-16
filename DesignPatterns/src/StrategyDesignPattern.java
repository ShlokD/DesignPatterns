import java.lang.reflect.Constructor;

class Sport {
	private String name;
	private String originYear;
	public Ball ball;
	
	Sport() {
		name = "";
		originYear = "";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOriginYear() {
		return originYear;
	}
	
	public void setOriginYear(String originYear) {
		this.originYear = originYear;
	}
	
	public String getInfo() {
		return "The sport of " +name+ " was first played in "+originYear;
	}
	
	public String playBall() {
		return ball.usesBall();
	}
	
	public void setBall(Ball newBall){
		ball = newBall;
	}
	
}

class Baseball extends Sport {
	
	public Baseball() {
		super();
		setName("Baseball");
		setOriginYear("1744 AD");
		setBall(new NeedsABall());
	}
	
}

class Running extends Sport {
	public Running() {
		super();
		setName("Running");
		setOriginYear("1829 BCE");
		setBall(new NoBall());
	}
}

interface Ball {
	public String usesBall();
}

class NeedsABall implements Ball {

	public String usesBall() {
		return "This sport needs a ball to play";
	}
}

class NoBall implements Ball {
	public String usesBall() {
		return "This sport does not need a ball";
	}
}


public class StrategyDesignPattern {
	public static void main(String[] args) {
		Sport baseball = new Baseball();
		Sport running = new Running();
		
		System.out.println(baseball.getInfo());
		System.out.println(baseball.playBall());
		System.out.println(running.getInfo());
		System.out.println(running.playBall());
	}
}
