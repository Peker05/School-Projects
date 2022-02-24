

public class Main
{
    static String content;
    static String commands;
    static String articless = "";


    static Author[] authors;
    static Article[] articles;


    public static void main(String[] args)
    {
        readAuthors(args[0]);
        readCommands(args[1]);
    }


    public static void readAuthors(String argmn)
    {
        FileIO authorReader = new FileIO(argmn, true);
        authorReader.readFile();

        content = authorReader.getContent();
        String[] aut = content.split("\n");
        authors = new Author[aut.length];


        for (int i = 0; i < aut.length; i++)
        {
            String[] authorLine = aut[i].split(" ");
            authors[i] = authorMaykr(authorLine);
        }

    }

    public static void readCommands(String argmn)
    {
        FileIO output = new FileIO("output.txt", false);
        FileIO commandReader = new FileIO(argmn, true);
        commandReader.readFile();
        commands = commandReader.getContent();
        String[] commandLines = commands.split("\n");

        for (String command: commandLines)
        {

            if (command.startsWith("read"))
            {

                read(command.substring(command.indexOf(" ")+ 1));
            }

            else if (command.startsWith("list"))
            {

                output.writeFile("-------------------------------------List-------------------------------------\n");
                list();
                output.writeFile("\n--------------------------------------End--------------------------------------\n");
            }

            else if (command.startsWith("completeAll"))
            {


                output.writeFile("*****************************completeAll Successful****************************\n");
                completeAll();

            }
            else if (command.startsWith("sortedAll"))
            {

                output.writeFile("******************************SortedAll Successful*****************************\n");
                sortedAll();
            }
            else if (command.startsWith("del"))
            {
                output.writeFile("*********************************del Successful********************************\n");
                delAuthorId(command.substring(command.indexOf(" ") + 1));
            }
        }
    }

    public static void read(String fileName)
    {


        FileIO articleReader = new FileIO(fileName, true);
        articleReader.readFile();
        articless += articleReader.getContent();
        String[] art = articless.split("\n");

        articles = new Article[art.length];

        for (int i = 0; i < art.length; i++) {
            String[] artLine = art[i].split(" ");
            articles[i] = new Article(artLine[1], artLine[2], artLine[3], artLine[4]);

        }


    }

    public static void list()
    {
        FileIO output = new FileIO("output.txt", false);


        content = "";

        for (Author auth : authors) {
            content += "\n\nAuthor : " + auth.getId() + "\t" + auth.getName() + "\t" + auth.getUniversity()
                    + "\t" + auth.getDepartment() + "\t" + auth.getEmail();
            for (Article arth : articles) {
                if (auth.getArticle_1().equals(arth.getPaperId()))
                    content += "\n+" + arth.getPaperId() + "\t" + arth.getName() + "\t"
                            + arth.getPublisherName() + "\t" + arth.getPublishYear();
                else if (auth.getArticle_2().equals(arth.getPaperId()))
                    content += "\n+" + arth.getPaperId() + "\t" + arth.getName() + "\t"
                            + arth.getPublisherName() + "\t" + arth.getPublishYear();
                else if (auth.getArticle_3().equals(arth.getPaperId()))
                    content += "\n+" + arth.getPaperId() + "\t" + arth.getName() + "\t"
                            + arth.getPublisherName() + "\t" + arth.getPublishYear();
                else if (auth.getArticle_4().equals(arth.getPaperId()))
                    content += "\n+" + arth.getPaperId() + "\t" + arth.getName() + "\t"
                            + arth.getPublisherName() + "\t" + arth.getPublishYear();
                else if (auth.getArticle_5().equals(arth.getPaperId()))
                    content += "\n+" + arth.getPaperId() + "\t" + arth.getName() + "\t"
                            + arth.getPublisherName() + "\t" + arth.getPublishYear();

            }
        }

        output.writeFile(content);

    }

    public static void completeAll()
    {
        for (Author auth : authors)
            for (Article arth : articles) {
                if (auth.hasArticle(arth.getPaperId()))
                    continue;
                if (arth.getPaperId().startsWith(auth.getId()))
                    auth.addArticle(arth);
            }

    }

    public static int[] bubbleSort(int[] arr)
    {
        int temp;
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] > arr[j])
                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void sortedAll()
    {
        for (Author author: authors)
        {
            author.setArticles(bubbleSort(author.getArticles()));
        }
    }
    public static void delAuthorId(String id)
    {
        int index = 0;
        Author[] temp = new Author[authors.length - 1];
        for (Author author: authors)
        {
            if (!author.getId().equals(id))
            {
                temp[index] = author;
                index++;
            }
        }
        authors = temp;
    }
    public static Author authorMaykr(String[] arrr)
    {

        String[] guldurGuldur = new String[11];
        for (int i = 0; i < arrr.length; i++)
        {
            guldurGuldur[i] = arrr[i];

        }
        for (int i = arrr.length; i < guldurGuldur.length; i++)
        {
            guldurGuldur[i] = "";

        }
        Author temp = new Author(guldurGuldur[1], guldurGuldur[2], guldurGuldur[3],guldurGuldur[4], guldurGuldur[5],
                guldurGuldur[6], guldurGuldur[7], guldurGuldur[8], guldurGuldur[9], guldurGuldur[10]);
        return temp;
    }





}
