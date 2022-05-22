package Classes.Decorator;

import Classes.Laptop.Laptop;

public abstract class LaptopDecoratorBase extends Laptop {
    private Laptop laptop;

    public LaptopDecoratorBase(Laptop l) {
        laptop = l;
    }

    @Override
    public int getStorage() {
        return laptop.getStorage();
    }

    @Override
    public int getProcessor() {
        return laptop.getProcessor();
    }
    @Override
    public int getGraphics_card() {
        return laptop.getGraphics_card();
    }

    @Override
    public int getMemory() {
        return laptop.getMemory();
    }

    @Override
    public int getOs() {
        return laptop.getOs();
    }

    @Override
    public int getPrice() {
        return laptop.getPrice();
    }

    @Override
    public String getType() {
        return laptop.getType();
    }
}
