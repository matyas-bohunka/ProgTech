package Classes.Decorator;

import Classes.Laptop.Laptop;

public class SpecialOffer extends LaptopDecoratorBase {
    public SpecialOffer(Laptop l) {
        super(l);
    }

    @Override
    protected void setStorage(int storage){
    }

    @Override
    protected void setProcessor(int processor){
    }

    @Override
    protected void setMemory(int memory){
    }

    @Override
    protected void setOs(int os){
    }

    @Override
    protected void setPrice(int price){
    }

    @Override
    protected void setType(){
    }

    private int coupon;

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int c) {
        coupon = c;
    }

    @Override
    public int getPrice() {

        int basePrice = super.getPrice();
        int percent = 100 - getCoupon();
        return Math.round((basePrice * percent) / 100);
    }
}

