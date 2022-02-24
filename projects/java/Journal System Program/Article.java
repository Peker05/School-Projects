


public class Article
{
    private String paperId;
    private String name;
    private String publisherName;
    private String publishYear;

    public Article(String paperId, String name, String publisherName, String publishYear)
    {
        this.paperId = paperId;
        this.name = name;
        this.publisherName = publisherName;
        this.publishYear = publishYear;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }
}
