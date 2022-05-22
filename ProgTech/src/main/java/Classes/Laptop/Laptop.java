package Classes.Laptop;

public abstract class Laptop {

    protected String type;
    protected int storage;
    protected int processor;
    protected int graphics_card;
    protected int memory;
    protected int os;
    protected int price;


    public abstract int getStorage();

    protected abstract void setStorage(int storage);

    public abstract int getProcessor();

    protected void setProcessor(int processor) {
    }

    public abstract int getGraphics_card();

    protected abstract void setGraphics_card(int graphics_card);

    public abstract int getMemory();

    protected abstract void setMemory(int memory);

    public abstract int getPrice();

    protected abstract void setPrice(int price);


    public abstract String getType();

    protected abstract void setType();
}
