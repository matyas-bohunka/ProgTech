package Classes.Controllers;

import Classes.Decorator.SpecialOffer;
import Classes.LaptopParts.*;
import Classes.Laptop_types.ThinkPad;
import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ThinkPadController {
    static Logger logger = LoggerFactory.getLogger(ThinkPadController.class);
    public static SpecialOffer getOffer(String coupon, ThinkPad thinkPad) {
        SpecialOffer specialOffer = new SpecialOffer(thinkPad);
        if (coupon.equals("LENOVO20")) {
            specialOffer.setCoupon(20);
        } else if (coupon.equals("LENOVO5")) {
            specialOffer.setCoupon(5);
        }
        return specialOffer;
    }

    public static boolean storeLaptop(ThinkPad thinkPad) {
        String laptop = "INSERT INTO laptops (" +
                " storage," +
                " processor," +
                " graphics_card," +
                " memory," +
                " os," +
                " price," +
                "type," +
                "uid) " +
                "VALUES (" + thinkPad.getStorage() + ","
                + thinkPad.getProcessor() + ","
                + thinkPad.getGraphics_card() + ","
                + thinkPad.getMemory() + ","
                + thinkPad.getOs() + ","
                + thinkPad.getPrice() + ",'"
                + thinkPad.getType() + "'," +
                MySQLConnect.connectedUSer.id + ");";
        logger.info(laptop);
        try {
            MySQLConnect.modifyDatabase(laptop);

            logger.info("new thinkPad laptop added to database");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Graphics_card setSelectedGraphics(String selectedItem, List<Graphics_card> graphics_card) throws SQLException {
        for (int i = 0; i < graphics_card.size(); i++) {
            if (graphics_card.get(i).getName().equals(selectedItem)) {
                return graphics_card.get(i);
            }
        }
        return null;
    }

    public static Memory setSelectedMemory(String selectedItem, List<Memory> memory) throws SQLException {
        for (int i = 0; i < memory.size(); i++) {

            if (memory.get(i).getName().equals(selectedItem)) {
                return memory.get(i);
            }
        }
        return null;
    }

    public static Storage setSelectedStorage(String selectedItem, List<Storage> storage) throws SQLException {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getName().equals(selectedItem)) {
                return storage.get(i);
            }
        }
        return null;
    }
    public static Os setSelectedOs(String selectedItem, List<Os> os) throws SQLException {
        for (int i = 0; i < os.size(); i++) {
            if (os.get(i).getName().equals(selectedItem)) {
                return os.get(i);
            }
        }
        return null;
    }
    public static Processor setSelectedProcessor(String selectedItem, List<Processor> processor) throws SQLException {
        for (int i = 0; i < processor.size(); i++) {
            if (processor.get(i).getName().equals(selectedItem)) {
                return processor.get(i);
            }
        }
        return null;
    }
}
