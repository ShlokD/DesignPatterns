import java.util.ArrayList;

interface NewsSubject {
	public void register(NewsObserver newObserver);
	public void unregister(NewsObserver oldObserver);
	public void notifyObservers();
}

interface NewsObserver {
	public void update(
			String businessHeadline,
			String techHeadline,
			String politicsHeadline
	);
}

class NewsSource implements NewsSubject {
	
	private ArrayList<NewsObserver> newsObservers;
	private String businessNews;
	private String techNews;
	private String politicsNews;
	
	public void setBusinessNews(String businessNews) {
		this.businessNews = businessNews;
		notifyObservers();
	}
	
	
	public String getBusinessNews() {
		return businessNews;
	}
	
	public void setPoliticsNews(String politicsNews) {
		this.politicsNews = politicsNews;
		notifyObservers();
	}
	
	public void setTechNews(String techNews) {
		this.techNews = techNews;
		notifyObservers();
	}
	
	public ArrayList<NewsObserver> getNewsObservers() {
		return newsObservers;
	}
	
	public String getPoliticsNews() {
		return politicsNews;
	}
	
	public String getTechNews() {
		return techNews;
	}
	
	public void setNewsObservers(ArrayList<NewsObserver> newsObservers) {
		this.newsObservers = newsObservers;
	}
	
	public NewsSource() {
		newsObservers = new ArrayList<>();
		businessNews = "";
		politicsNews = "";
		techNews = "";
	}
	
	@Override
	public void register(NewsObserver newObserver) {
		newsObservers.add(newObserver);
		
	}

	@Override
	public void unregister(NewsObserver oldObserver) {
		int index = newsObservers.indexOf(oldObserver);
		if(index != -1) {
			newsObservers.remove(index);
		}
	}

	@Override
	public void notifyObservers() {
		for(NewsObserver newsObserver : newsObservers) {
			newsObserver.update(getBusinessNews(), getTechNews(), getPoliticsNews());
		}
	}
	
}


class NewsAgency implements NewsObserver {
	
	private String businessHeadline;
	private String techHeadline;
	private String politicsHeadline;
	private NewsSubject newsSource;
	private String name;
	
	public NewsAgency(NewsSubject newsSource, String name) {
		businessHeadline = "";
		techHeadline = "";
		politicsHeadline = "";
		this.newsSource = newsSource;
		this.name = name;
		newsSource.register(this);
	}

	@Override
	public void update(String businessHeadline, String techHeadline, String politicsHeadline) {
		this.businessHeadline = businessHeadline;
		this.techHeadline = techHeadline;
		this.politicsHeadline = politicsHeadline;
		printHeadlines();
	}
	
	public void printHeadlines() {
		System.out.println("FROM "+name);
		System.out.println("LATEST BUSINESS NEWS: "+businessHeadline);
		System.out.println("LATEST POLITICS NEWS: "+politicsHeadline);
		System.out.println("LATEST TECH NEWS: "+techHeadline);
		System.out.println("");

	}
	
}

public class ObserverPattern {
	public static void main(String[] args){
		NewsSource newsSource= new NewsSource();
		NewsAgency newsAgency1 = new NewsAgency(newsSource, "Agency 1");
		
		newsSource.setBusinessNews("Mall of America to stay closed on Thanksgiving Day");
		newsSource.setPoliticsNews("Hillary Clinton opens up 11-point lead over Donald Trump");
		newsSource.setTechNews("New Macbooks rumored to be launched on October 27");
		
		newsSource.unregister(newsAgency1);
		
		NewsAgency newsAgency2 = new NewsAgency(newsSource, "Agency 2");
		
		newsSource.setBusinessNews("Whiskey workers strike at 2 Beam distilleries in Kentucky");
		newsSource.setPoliticsNews("Trump continues with attacks on accusers, 'rigged' system");
		newsSource.setTechNews("Facebook looks to launch Social VR App");
		
		
		
	}
}
