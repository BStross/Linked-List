package LinkedList;

public class PbNode 
{
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private PbNode next; 

    public PbNode()
    {
        
    }

    public PbNode(String name, PbNode next)
    {
        this.name = name;
        this.next = next;
    }

    public PbNode(String name, String address, String phoneNumber, PbNode next)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.next = next;
    }

    public void initializeNode(String name, PbNode next)
    {
        this.name = name;
        this.next = next;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public PbNode getNext()
    {
        return this.next;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setNext(PbNode next)
    {
        this.next = next;
    }

    public String toString()
    {
        String node = 
            "Name: " + this.name + "\n" +
            "Address: " + this.address + "\n" +
            "City: " + this.city + "\n" +
            "Phone Number: " + this.phoneNumber;
            
        return node;
    }
}
