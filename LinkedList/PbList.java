package LinkedList;

public class PbList {
    private String name;
    private PbNode front;

    public PbList(PbNode front, String name)
    {
        this.name = name;
        this.front = front;
    }

    public String getName()
    {
        return this.name;
    }

    public PbNode getFront()
    {
        return this.front;
    }

    public void setFront(PbNode front)
    {
        this.front = front;
    }
}
