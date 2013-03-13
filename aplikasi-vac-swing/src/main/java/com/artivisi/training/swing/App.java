package com.artivisi.training.swing;

import com.artivisi.training.rest.client.VacRestClient;
import com.artivisi.training.swing.ui.MainFrame;

/**
 * Hello world!
 *
 */
public class App {

    private static VacRestClient vacRestClient;
    private static MainFrame mainFrame;
    
    public static void main( String[] args ) {
        
        vacRestClient = new VacRestClient();
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        
    }
}
