


public class Author
{
    private String id;
    private String name;
    private String university;
    private String department;
    private String email;
    private String article_1;
    private String article_2;
    private String article_3;
    private String article_4;
    private String article_5;

    public Author(String id, String name, String university, String department, String email, String article_1,
                  String article_2, String article_3, String article_4, String article_5)
    {
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.article_1 = article_1;
        this.article_2 = article_2;
        this.article_3 = article_3;
        this.article_4 = article_4;
        this.article_5 = article_5;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArticle_1() {
        return article_1;
    }

    public void setArticle_1(String article_1) {
        this.article_1 = article_1;
    }

    public String getArticle_2() {
        return article_2;
    }

    public void setArticle_2(String article_2) {
        this.article_2 = article_2;
    }

    public String getArticle_3() {
        return article_3;
    }

    public void setArticle_3(String article_3) {
        this.article_3 = article_3;
    }

    public String getArticle_4() {
        return article_4;
    }

    public void setArticle_4(String article_4) {
        this.article_4 = article_4;
    }

    public String getArticle_5() {
        return article_5;
    }

    public void setArticle_5(String article_5) {
        this.article_5 = article_5;
    }
    public boolean hasArticle(String id)
    {
        if (article_1 == id || article_2 == id || article_3 == id || article_4 == id || article_5 == id)
            return true;
        return false;
    }
    public void addArticle(Article art)
    {

        if (article_1.equals(""))
        {
            article_1 = art.getPaperId();
        }
        else if (article_2.equals(""))
        {
            article_2 = art.getPaperId();
        }
        else if (article_3.equals(""))
        {
            article_3 = art.getPaperId();
        }
        else if (article_4.equals(""))
        {
            article_4 = art.getPaperId();
        }
        else if (article_5.equals(""))
        {
            article_5 = art.getPaperId();
        }
    }
    public int[] getArticles()
    {
        int[] arr = new int[5];
        if (!article_1.equals(""))
            arr[0] = Integer.parseInt(article_1);
        if (!article_2.equals(""))
            arr[1] = Integer.parseInt(article_2);
        if (!article_3.equals(""))
            arr[2] = Integer.parseInt(article_3);
        if (!article_4.equals(""))
            arr[3] = Integer.parseInt(article_4);
        if (!article_5.equals(""))
            arr[4] = Integer.parseInt(article_5);
        return arr;

    }

    public void setArticles(int[] articles)
    {
        if (articles[0] == 0)
            article_1 = "";
        else
            article_1 = "" + articles[0];

        if (articles[1] == 0)
            article_2 = "";
        else
            article_2 = "" + articles[1];

        if (articles[2] == 0)
            article_3 = "";
        else
            article_3 = "" + articles[2];

        if (articles[3] == 0)
            article_4 = "";
        else
            article_4 = "" + articles[3];

        if (articles[4] == 0)
            article_5 = "";
        else
            article_5 = "" + articles[4];


    }

}
