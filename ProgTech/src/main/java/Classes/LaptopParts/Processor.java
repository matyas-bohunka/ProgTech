package Classes.LaptopParts;

public class Processor {
    private  int id;
    private String name;
    private int speed;
    private int tdp;
    private int core;
    private int thread;
    private String gpu_name;
    private int gpu_speed;
    private String gpu_memory;
    private int gpu_capacity;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public String getGpu_name() {
        return gpu_name;
    }

    public void setGpu_name(String gpu_name) {
        this.gpu_name = gpu_name;
    }

    public int getGpu_speed() {
        return gpu_speed;
    }

    public void setGpu_speed(int gpu_speed) {
        this.gpu_speed = gpu_speed;
    }

    public String getGpu_memory() {
        return gpu_memory;
    }

    public void setGpu_memory(String gpu_memory) {
        this.gpu_memory = gpu_memory;
    }

    public int getGpu_capacity() {
        return gpu_capacity;
    }

    public void setGpu_capacity(int gpu_capacity) {
        this.gpu_capacity = gpu_capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
