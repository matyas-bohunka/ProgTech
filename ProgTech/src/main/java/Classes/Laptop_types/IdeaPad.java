package Classes.Laptop_types;

import Classes.Laptop.Laptop;

public class IdeaPad extends Laptop {
    public IdeaPad(int price) {
        this.setPrice(price);
        this.setType();
    }


    @Override
    public int getStorage() {
        return super.storage;
    }
    @Override
    public void setStorage(int storage) {
        super.storage = storage;
    }

    @Override
    public int getProcessor() {
        return super.processor;
    }

    @Override
    public void setProcessor(int processor) {
        super.processor = processor;
    }
    @Override
    public int getGraphics_card() {
        return super.graphics_card;
    }

    @Override
    public void setGraphics_card(int graphics_card) {
        super.graphics_card = graphics_card;
    }

    @Override
    public int getMemory() {
        return super.memory;
    }

    @Override
    public void setMemory(int memory) {
        super.memory = memory;
    }
    @Override
    public int getOs() {
        return super.os;
    }

    @Override
    public void setOs(int os) {
        super.os = os;
    }

    @Override
    public int getPrice() {
        return super.price;
    }

    @Override
    public void setPrice(int price) {
        super.price = price;
    }

    @Override
    public String getType() {
        return super.type;
    }

    @Override
    protected void setType() {
        super.type = "ideapad";
    }
}
