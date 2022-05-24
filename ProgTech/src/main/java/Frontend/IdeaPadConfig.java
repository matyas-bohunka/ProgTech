package Frontend;

import Classes.Controllers.IdeaPadController;
import Classes.Decorator.SpecialOffer;
import Classes.Laptop.Laptop;
import Classes.LaptopParts.*;
import Classes.Laptop_types.IdeaPad;
import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdeaPadConfig extends JFrame{
    private JPanel IdeaPadConfigPanel;
    private JComboBox storageComboBox;
    private JComboBox memoryComboBox;
    private JComboBox processorComboBox;
    private JComboBox osComboBox;
    private JTextField couponField;
    private JButton orderButton;
    private JLabel totalLabel;
    private JLabel storageTypeLabel;
    private JLabel memoryTypeLabel;
    private JLabel storageSpeedLabel;
    private JLabel memorySpeedLabel;
    private JLabel storageCapacityLabel;
    private JLabel memoryCapacityLabel;
    private JLabel storagePriceLabel;
    private JLabel memoryPriceLabel;
    private JLabel integratedNameLabel;
    private JLabel integratedSpeedLabel;
    private JLabel integratedMemoryLabel;
    private JLabel integratedCapacityLabel;
    private JLabel processorPriceLabel;
    private JLabel processorSpeedLabel;
    private JLabel processorTDPLabel;
    private JLabel processorCoreLabel;
    private JLabel processorThreadLabel;
    private JLabel osPriceLabel;

    private Laptop ideaPad;
    private static Memory selectedMemory;
    private static Storage selectedStorage;
    private static Os selectedOs;
    private static Processor selectedProcessor;

    Logger logger = LoggerFactory.getLogger(IdeaPadConfig.class);
    private List<Memory> memoryList = new ArrayList<>();
    private List<Storage> storageList = new ArrayList<>();
    private List<Os> osList = new ArrayList<>();
    private List<Processor> processorList = new ArrayList<>();

    public IdeaPadConfig(IdeaPad ideaPad) throws SQLException {
        Logger logger = LoggerFactory.getLogger(Config.class);
        logger.info("IdeaPad laptop frame initialized");
        setContentPane(IdeaPadConfigPanel);
        setSize(800, 600);
        setTitle("Laptop Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        this.ideaPad = ideaPad;
        loadDataToUI();
        setDefaultComponents();
        setPriceLabel();

        storageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedStorage=IdeaPadController.setSelectedStorage(
                            storageComboBox.getSelectedItem().toString(),storageList);
                    storagePriceLabel.setText(Integer.toString(selectedStorage.getPrice()));
                    storageSpeedLabel.setText(Integer.toString(selectedStorage.getSpeed()));
                    storageTypeLabel.setText(selectedStorage.getType());
                    storageCapacityLabel.setText(Integer.toString(selectedStorage.getCapacity()));
                    setPriceLabel();
                    logger.info("new storage selected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        memoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedMemory =IdeaPadController.setSelectedMemory(
                            memoryComboBox.getSelectedItem().toString(),memoryList);
                    memoryPriceLabel.setText(Integer.toString(selectedMemory.getPrice()));
                    memorySpeedLabel.setText(Integer.toString(selectedMemory.getSpeed()));
                    memoryTypeLabel.setText(selectedMemory.getType());
                    memoryCapacityLabel.setText(Integer.toString(selectedMemory.getSpeed()));
                    setPriceLabel();
                    logger.info("new memory selected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        processorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedProcessor =IdeaPadController.setSelectedProcessor(
                            processorComboBox.getSelectedItem().toString(),processorList);
                    processorPriceLabel.setText(Integer.toString(selectedProcessor.getPrice()));
                    processorSpeedLabel.setText(Integer.toString(selectedProcessor.getSpeed()));
                    processorTDPLabel.setText(Integer.toString(selectedProcessor.getTdp()));
                    processorCoreLabel.setText(Integer.toString(selectedProcessor.getCore()));
                    processorThreadLabel.setText(Integer.toString(selectedProcessor.getThread()));
                    integratedNameLabel.setText(selectedProcessor.getGpu_name());
                    integratedSpeedLabel.setText(Integer.toString(selectedProcessor.getGpu_speed()));
                    integratedCapacityLabel.setText(Integer.toString(selectedProcessor.getGpu_capacity()));
                    integratedMemoryLabel.setText(selectedProcessor.getGpu_memory());
                    setPriceLabel();
                    logger.info("new processor selected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        osComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedOs =IdeaPadController.setSelectedOs(
                            osComboBox.getSelectedItem().toString(),osList);
                    memoryPriceLabel.setText(Integer.toString(selectedOs.getPrice()));
                    setPriceLabel();
                    logger.info("new os selected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOrderClick();
            }
        });

    }
    private void loadDataToUI() throws SQLException {
        totalLabel.setText(Integer.toString(ideaPad.getPrice()));
        addItemsToProcessorComboBox();
        addItemsToStorageComboBox();
        addItemsToMemoryComboBox();
        addItemsToOsComboBox();
        loadDefaultMemory();
        loadDefaultProcessor();
        loadDefaultStorage();
        loadDefaultOs();
        logger.info("data loaded to UI");
    }

    private void addItemsToOsComboBox() throws SQLException{
        ResultSet resultSet = MySQLConnect.executeQuery("SELECT * from os;");
        while (resultSet.next()) {
            Os os = new Os();
            osComboBox.addItem(resultSet.getString("name"));
            os.setName(resultSet.getString("name"));
            os.setPrice(resultSet.getInt("price"));
            osList.add(os);
        }
    }


    private void addItemsToMemoryComboBox() throws SQLException{
        ResultSet resultSet = MySQLConnect.executeQuery("SELECT * from memory;");
        while (resultSet.next()) {
            Memory memory = new Memory();
            memoryComboBox.addItem(resultSet.getString("name"));
            memory.setName(resultSet.getString("name"));
            memory.setCapacity(resultSet.getInt("capacity"));
            memory.setType(resultSet.getString("type"));
            memory.setSpeed(resultSet.getInt("speed"));
            memory.setPrice(resultSet.getInt("price"));
            memoryList.add(memory);
        }
    }

    private void addItemsToStorageComboBox() throws SQLException{
        ResultSet resultSet = MySQLConnect.executeQuery("SELECT * from storage;");
        while (resultSet.next()) {
            Storage storage = new Storage();
            storageComboBox.addItem(resultSet.getString("name"));
            storage.setName(resultSet.getString("name"));
            storage.setCapacity(resultSet.getInt("capacity"));
            storage.setType(resultSet.getString("type"));
            storage.setSpeed(resultSet.getInt("speed"));
            storage.setPrice(resultSet.getInt("price"));
            storageList.add(storage);
        }
    }

    private void addItemsToProcessorComboBox() throws SQLException{
        ResultSet resultSet = MySQLConnect.executeQuery("SELECT * from processor;");
        while (resultSet.next()) {
            Processor processor = new Processor();
            processorComboBox.addItem(resultSet.getString("name"));
            processor.setName(resultSet.getString("name"));
            processor.setSpeed(resultSet.getInt("speed"));
            processor.setPrice(resultSet.getInt("price"));
            processor.setTdp(resultSet.getInt("tdp"));
            processor.setCore(resultSet.getInt("core"));
            processor.setThread(resultSet.getInt("thread"));
            processor.setGpu_capacity(resultSet.getInt("gpu_capacity"));
            processor.setGpu_name(resultSet.getString("gpu_name"));
            processor.setGpu_speed(resultSet.getInt("gpu_speed"));
            processor.setGpu_memory(resultSet.getString("gpu_memory"));
            processorList.add(processor);
        }
    }

    private void loadDefaultMemory() throws  SQLException {
        memoryPriceLabel.setText(Integer.toString(memoryList.get(0).getPrice()));
        memorySpeedLabel.setText(Integer.toString(memoryList.get(0).getSpeed()));
        memoryTypeLabel.setText(memoryList.get(0).getType());
        memoryCapacityLabel.setText(Integer.toString(memoryList.get(0).getCapacity()));
    }

    private void loadDefaultStorage()throws  SQLException{
        storagePriceLabel.setText(Integer.toString(storageList.get(0).getPrice()));
        storageSpeedLabel.setText(Integer.toString(storageList.get(0).getSpeed()));
        storageTypeLabel.setText(storageList.get(0).getType());
        storageCapacityLabel.setText(Integer.toString(storageList.get(0).getCapacity()));
    }
    private void loadDefaultProcessor()throws  SQLException{
        processorPriceLabel.setText(Integer.toString(processorList.get(0).getPrice()));
        processorSpeedLabel.setText(Integer.toString(processorList.get(0).getSpeed()));
        processorTDPLabel.setText(Integer.toString(processorList.get(0).getTdp()));
        processorCoreLabel.setText(Integer.toString(processorList.get(0).getCore()));
        processorThreadLabel.setText(Integer.toString(processorList.get(0).getThread()));
        integratedNameLabel.setText(processorList.get(0).getGpu_name());
        integratedSpeedLabel.setText(Integer.toString(processorList.get(0).getGpu_speed()));
        integratedCapacityLabel.setText(Integer.toString(processorList.get(0).getGpu_capacity()));
        integratedMemoryLabel.setText(processorList.get(0).getGpu_memory());
    }
    private void loadDefaultOs()throws  SQLException{
        osPriceLabel.setText(Integer.toString(osList.get(0).getPrice()));
    }
    private void handleOrderClick() {
        IdeaPad ideaPad = (IdeaPad) this.ideaPad;
        ideaPad.setPrice(getOrderTotalPrice());
        ideaPad.setStorage(selectedStorage.getId());
        ideaPad.setMemory(selectedMemory.getId());
        ideaPad.setOs(selectedOs.getId());
        ideaPad.setProcessor(selectedProcessor.getId());

        SpecialOffer offer = IdeaPadController.getOffer(
                couponField.getText(),
                ideaPad);

        ideaPad.setPrice(offer.getPrice());

        if(IdeaPadController.storeLaptop(ideaPad))
        {
            dispose();
            Login log = new Login(false);
        }

    }
    private int getOrderTotalPrice() {
        return ideaPad.getPrice()
                + selectedMemory.getPrice()
                + selectedProcessor.getPrice()
                + selectedStorage.getPrice()
                + selectedOs.getPrice();
    }
    private void setDefaultComponents() {
        selectedProcessor = processorList.get(0);
        selectedOs = osList.get(0);
        selectedStorage = storageList.get(0);
        selectedMemory = memoryList.get(0);
        logger.info("default components added");
    }
    private void setPriceLabel() {
        totalLabel.setText(Integer.toString(
                ideaPad.getPrice()
                        + selectedMemory.getPrice()
                        + selectedProcessor.getPrice()
                        + selectedStorage.getPrice()
                        + selectedOs.getPrice()));
        logger.info("new price set");
    }
}
