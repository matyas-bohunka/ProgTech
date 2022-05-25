package Frontend;

import Classes.Laptop.Laptop;
import Classes.Laptop_types.ThinkPad;
import Classes.Laptop_types.IdeaPad;
import Classes.Laptop_types.Legion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Config extends JFrame{
    private JPanel ConfPanel;
    private JPanel TypePanel;
    private JButton legionButton;
    private JButton thinkPadButton;
    private JButton ideaPadButton;

    public Config() {
        Logger logger = LoggerFactory.getLogger(Config.class);
        setContentPane(ConfPanel);
        setSize(800, 600);
        setTitle("Laptop Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        legionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    logger.info("Legion button clicked");
                    TypePanel.setVisible(false);
                    Laptop legion = new Legion(100000);
                    try {
                        LegionConfig legionConfig = new LegionConfig((Legion) legion);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
            }
        });
        thinkPadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                logger.info("ThinkPad button clicked");
                TypePanel.setVisible(false);
                Laptop thinkPad = new ThinkPad(100000) {
                };
                try {
                    ThinkPadConfig thinkPadConfig = new ThinkPadConfig((ThinkPad) thinkPad);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ideaPadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                logger.info("IdeaPad button clicked");
                TypePanel.setVisible(false);
                Laptop ideaPad = new IdeaPad(100000);
                try {
                    IdeaPadConfig ideaPadConfig = new IdeaPadConfig((IdeaPad) ideaPad);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    }
}
