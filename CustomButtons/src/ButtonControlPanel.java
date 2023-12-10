import javax.swing.*;
import java.awt.*;

public class ButtonControlPanel extends JFrame {
    private final int ROWS = 4;
    private final int COLS = 4;

    public ButtonControlPanel() {
        setTitle("Button Control Panel");
        //Pencere başlığını ayarlar.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Pencere kapatıldığında uygulamanın sonlanmasını sağlar.
        setLayout(new GridLayout(ROWS, COLS));
        //Grid yapısında bir düzen oluşturur


        ImageIcon activeIcon = new ImageIcon("active_icon.png");
        ImageIcon inactiveIcon = new ImageIcon("inactive_icon.png");
        //Aktif ve deaktif için örnek iconlar.

        for (int i = 0; i < ROWS * COLS; i++) {
            String graphQLSchema = "Değişim" +  (i + 1);
            //Her buton için örnek GraphQL şeması oluşturur.
            CustomButton button = new CustomButton("Buton " + (i + 1), graphQLSchema,
                    Color.GREEN, Color.LIGHT_GRAY, activeIcon, inactiveIcon);
            //Yeni bir CustomButton oluşturur.
            add(button);
        }
        //4x4 buton grid'i oluşturur ve her birine özelliklerini belirterek CustomButton ekler.

        pack();
        //Pencere boyutunu içeriği uygun hale getirir.
        setLocationRelativeTo(null);
        //Pencereyi ekranın ortasına konumlandırır.
        setVisible(true);
        //Pencereyi görünür hale getirir.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ButtonControlPanel::new);
    }
    //Swing arayüzünü oluşturmak için yeni bir ButtonControlPanel örneği yaratır.
}
