import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomButton extends JButton {
    private static List<CustomButton> buttonList = new ArrayList<>();
    //CustomButton'ın tüm türkeri için buttonList oluşturulur.
    private String graphQLSchema;
    private Color activeColor;
    private Color inactiveColor;
    private Icon activeIcon;
    private Icon inactiveIcon;
    private boolean isActive;
    private static CustomButton firstClickedButton;
    //CustomButton için tanımlamalar yapılır.

    public CustomButton(String text, String graphQLSchema, Color activeColor, Color inactiveColor,
                        Icon activeIcon, Icon inactiveIcon) {
        super(text);
        this.graphQLSchema = graphQLSchema;
        this.activeColor = activeColor;
        this.inactiveColor = inactiveColor;
        this.activeIcon = activeIcon;
        this.inactiveIcon = inactiveIcon;
        this.isActive = false;
        //CustomButton için bir constructor oluşturulur.

        setProperties();
        addActionListener(new ButtonClickListener());
        buttonList.add(this);
        //Buton özellikleri yerleştirilir ve  action listener eklenir.
    }

    private void setProperties() {
        setForeground(inactiveColor);
        setIcon(inactiveIcon);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //Padding ayarlanır.
        setPreferredSize(new Dimension(120, 120));
        //Buton boyutu ayarlanır.
    }
    //Butonun ilk özellikleri atanır.

    public void setActive(boolean active) {
        isActive = active;
        setForeground(active ? activeColor : inactiveColor);
        setIcon(active ? activeIcon : inactiveIcon);
        //Butonun aktif veya deaktifliği ayarlanır.
    }

    public boolean getActive() {
        return isActive;
        //Butonun aktifliğini kontrol eder.
    }

    public String getGraphQLSchema() {
        return graphQLSchema;
        //Butona atanmış GraphQL çekilir.
    }

    public static void deactivateAllExcept(CustomButton clickedButton) {
        for (CustomButton button : buttonList) {
            if (button != clickedButton && button.isActive) {
                button.setActive(false);
                //clickedButton hariç hepsini deaktive eder.
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean wasActive = getActive();

            if (firstClickedButton != null && firstClickedButton == CustomButton.this && wasActive) {
                firstClickedButton.setActive(true); // İlk tıklanan butonu tekrar aktif hale getir
                deactivateAllExcept(firstClickedButton); // İlk tıklanan buton dışındakileri deaktive et
            }
            else {
                setActive(!wasActive);
                firstClickedButton = firstClickedButton == null ? CustomButton.this : firstClickedButton;
                if (firstClickedButton != CustomButton.this) {
                    firstClickedButton.setActive(true);
                    //Buton aktifliğini değiştirir.
                }
                System.out.println("Butona basıldı! GraphQL değişimi uygulanıyor: " + getGraphQLSchema());
                //Burada gerçek bir GraphQL sorgusu çalıştırılmalı.
                //Bu örnekte sadece simülasyon için bir çıktı veriliyor.
            }
        }
    }
}
//CustomButton için ActionListener tanımlanır.