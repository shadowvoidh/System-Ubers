
import java.awt.*;
import javax.swing.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class inicioUbers {
    private static WebView webView;
// !☕ JAVA
    public static void main(String[] args) {
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.vsync", "false");
        JFrame frame = new JFrame("SYSTEM UBERS");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLayeredPane camadas = new JLayeredPane();
        frame.add(camadas, BorderLayout.CENTER);
        frame.setVisible(true);

        int w = frame.getWidth();
        GraphicsConfiguration gc = frame.getGraphicsConfiguration();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
        int h = frame.getHeight() - screenInsets.bottom - frame.getInsets().top;

        // ?🖼️ Sfondo del sistema (☕JAVA por @Shadow_Voidh)
        ImageIcon wallIcon = new ImageIcon("../../../assets/uberswall.png");
        Image wallImg = wallIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(wallImg));
        background.setBounds(0, 0, w, h);
        camadas.add(background, Integer.valueOf(1));

        // --- O NAVEGADOR (JFXPanel) (☕JAVA por @Shadow_Voidh) ---
        JFXPanel jfxPanel = new JFXPanel();
        jfxPanel.setBounds(0, 0, w, h - 60);
        jfxPanel.setVisible(false);
        camadas.add(jfxPanel, Integer.valueOf(3));

        // --- PRÉ-CARREGAMENTO (☕JAVA por @Shadow_Voidh) ---
        Platform.runLater(() -> {
            webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().setUserStyleSheetLocation("data:,body { overflow: hidden !important; }");
            // Carrega o HTML imediatamente no início, em silêncio
            webView.getEngine().load(" https://shadowvoidh.github.io/Index-Ubers/");
        });

        // --- BARRA ELITE (☕JAVA por @Shadow_Voidh) ---
        JPanel barra = new JPanel(null);
        barra.setBackground(new Color(15, 15, 15));
        barra.setBounds(0, h - 60, w, 60);
        barra.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(184, 134, 11)));
        camadas.add(barra, Integer.valueOf(2));

        // --- BOTÃO UBERS (☕JAVA por @Shadow_Voidh) ---
        JButton btnUber = new JButton();
        ImageIcon icon = new ImageIcon("../../../assets/uber.png");
        if (icon.getIconWidth() > 0) {
            int novaAlt = 50;
            int novaLarg = (icon.getIconWidth() * novaAlt) / icon.getIconHeight();
            btnUber.setIcon(new ImageIcon(icon.getImage().getScaledInstance(novaLarg, novaAlt, Image.SCALE_SMOOTH)));
            btnUber.setBounds((w / 2) - (novaLarg / 2), (60 / 2) - (novaAlt / 2), novaLarg, novaAlt);
        }
        btnUber.setContentAreaFilled(false);
        btnUber.setBorderPainted(false);
        btnUber.setFocusPainted(false);
        btnUber.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnUber.addActionListener(e -> {
            background.setVisible(false);
            jfxPanel.setVisible(true);
        });

        // --- LOGO "systemWindows"  (☕JAVA por @Shadow_Voidh)---
        JButton btnEsquerda = new JButton();
        ImageIcon iconU = new ImageIcon("../../../assets/ubericon.jpg");
        if (iconU.getIconWidth() > 0) {
            btnEsquerda.setIcon(new ImageIcon(iconU.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        btnEsquerda.setBounds(20, 10, 40, 40);
        btnEsquerda.setContentAreaFilled(false);
        btnEsquerda.setBorderPainted(false);
        barra.add(btnEsquerda);

        //  BOTÃO VOLTAR  (☕JAVA por @Shadow_Voidh) ---
        JButton btnVoltar = new JButton();
        ImageIcon iconMenu = new ImageIcon("../../../assets/menu-burger.png");
        if (iconMenu.getIconWidth() > 0) {
            btnVoltar.setIcon(new ImageIcon(iconMenu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        }
        btnVoltar.setBounds(w - 70, 15, 30, 30);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVoltar.addActionListener(e -> {
            jfxPanel.setVisible(false);
            background.setVisible(true);
        });

        barra.add(btnVoltar);
        barra.add(btnUber);
        frame.revalidate();
        frame.repaint();
    }
}