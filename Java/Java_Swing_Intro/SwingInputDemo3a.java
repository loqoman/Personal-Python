/* Most of the code immediately below is just copied from SwingHelloWorld.java */
import javax.swing.*;

/* This is new; we need stuff from the awt packages so that we can process
 * user input.
 */
import java.awt.*;
import java.awt.event.*;

public class SwingInputDemo3a implements Runnable
{
    /* This is new; instance variables to hold JTextBox and JButton components */
    private JTextField  textBox_;
    private JButton     okButton_;
    private JButton     clearButton_;
    private JButton     exitButton_;

    public static void main( String[] args )
    {
        SwingInputDemo3a demo = new SwingInputDemo3a();
        SwingUtilities.invokeLater( demo );
    }
    
    public void run()
    {
        createGUI();
    }
    
    private void createGUI()
    {
        JFrame frame = new JFrame( "Swing Input Demo 3a" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        /* I chose to do the layout in a separate method so that it will be
         * easier to see the logic that is used to set up the OK button to
         * tell us when it is clicked. The doLayout method will create all
         * the components and arrange them within the JFrame.
         */
        doLayout( frame );

        /* This is how we set up the OK, Clear and Exit buttons so that we 
         * will know when they are clicked. "OKButtonListener,"
         * "ClearButtonListener" and "ExitButtonListener" are private classes
         * that we define below.
         */
        OKButtonListener okListener = new OKButtonListener();
        okButton_.addActionListener( okListener );

        ClearButtonListener clearListener = new ClearButtonListener();
        clearButton_.addActionListener( clearListener );

        ExitButtonListener exitListener = new ExitButtonListener();
        exitButton_.addActionListener( exitListener );
        
        /* This time we won't bother to add a listener to the text box. We 
         * will just wait until the OK button is clicked, then we will 
         * display whatever was typed into the text box in a JOptionPane.
         */
        
        /* Show the dialog. */
        frame.setVisible( true );
    }

    /**************************************************************************
     * The doLayout Method
     * ===================
     * This is an enhancement of the layout demonstrated in
     * "SwingInputDemo2.java." Two buttons are added to the dialog, centered
     * beneath the label and text box components.
     *
     *                 +----------------------------------+
     *                 |                                  |
     *                 |     label           text box     |
     *                 |                                  |
     *                 |         OK  Clear  Exit          |
     *                 |                                  |
     *                 +----------------------------------+
     *
     * In order to center the row of buttons we will put them in a JPanel
     * then use a GridBagLayout in the frame to center the JPanel below the 
     * label and text box. The GridBagLayout will consist of two rows and
     * two columns; the label ("Swing Input Demo 3a") goes in the cell (0,0), 
     * the text box goes in cell (0,1), and the panel occupies both cells (1,0)
     * and (1,1). Each cell will be separated from the inside of the frame and
     * each other (that is, they will be "inset") by a margin of 5 pixels.
     *
     *                 +-----------------------------------+
     *                 |                 |                 |
     *                 |                 |                 |
     *                 |     label       |    text box     |
     *                 |                 |                 |
     *                 |                 |                 |
     *                 |-----------------+-----------------|
     *                 |                 |                 |
     *                 |   +---------------------------+   |
     *                 |   |          JPa|nel          |   |
     *                 |   +---------------------------+   |
     *                 |                 |                 |
     *                 +-----------------------------------+
     *
     * By default, the three buttons will be made just big enough to contain
     * their names, which will make them different sizes. In order to make them
     * all the same size a GridLayout consisting of one row and three columns
     * is added to the panel.
     *
     *                     +--------------------------+    
     *                     |   OK   |  Clear |  Exit  |
     *                     +--------------------------+    
     **************************************************************************/

    private void doLayout( JFrame frame )
    {
        JPanel  panel   = new JPanel ( new GridLayout( 1, 3, 5, 0 ) );
        JLabel  label   = new JLabel( "Swing Input Demo 3a" );
        textBox_ = new JTextField( 20 );
        okButton_ = new JButton( "OK" );
        clearButton_ = new JButton( "Clear" );
        exitButton_ = new JButton( "Exit" );

        /* Most of the work we need to do requires manipulation of the
         * container component of the frame.
         */
        Container container = frame.getContentPane();
        
        /* Create and set the GridBagLayout manager. */
        container.setLayout( new GridBagLayout() );

        /* A GridBagConstraints object is used to determine the cell position
         * of the label, text box and OK button.
         */
        GridBagConstraints gbc = new GridBagConstraints();

        /* The fill constraint determines how a component is resized if it is
         * too small to fit in its cell. We don't want the component resized
         * at all.
         */
        gbc.fill = GridBagConstraints.NONE;

        /* The insets constraint determines the margins. */
        gbc.insets = new Insets( 5, 5, 5, 5 );
        
        /* The label goes in cell (0,0). */
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add( label, gbc );
        
        /* The text box goes in cell (1,0). */
        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add( textBox_, gbc );

        /* The panel starts in cell (0,1), and spans two cells. */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        container.add( panel, gbc );

        panel.add( okButton_ );
        panel.add( clearButton_ );
        panel.add( exitButton_ );

        /* Tell the layout manager to perform the layout. */
        frame.pack();
    }
    
    /**************************************************************************
     * The OKButtonListener Class
     * ==========================
     * We need an object of this type to "listen" for events that occur in
     * the OK button. The "implements ActionListener" is a promise to the
     * compiler to have a "public void actionPerformed( ActionEvent )"
     * method.
     */
    private class OKButtonListener implements ActionListener
    {
        /* This method is called when the OK button is clicked. */
        public void actionPerformed( ActionEvent event )
        {
            String msg = "text: " + textBox_.getText();
            JOptionPane.showMessageDialog( null, msg );
        }
    }
    
    /**************************************************************************
     * The ClearButtonListener Class
     * =============================
     * We need an object of this type to "listen" for events that occur in
     * the Clear button. The "implements ActionListener" is a promise to the
     * compiler to have a "public void actionPerformed( ActionEvent )"
     * method.
     */
    private class ClearButtonListener implements ActionListener
    {
        /* This method is called when the OK button is clicked. */
        public void actionPerformed( ActionEvent event )
        {
            textBox_.setText( "" );
        }
    }
    
    /**************************************************************************
     * The ExitButtonListener Class
     * ============================
     * We need an object of this type to "listen" for events that occur in
     * the Exit button. The "implements ActionListener" is a promise to the
     * compiler to have a "public void actionPerformed( ActionEvent )"
     * method.
     */
    private class ExitButtonListener implements ActionListener
    {
        /* This method is called when the OK button is clicked. */
        public void actionPerformed( ActionEvent event )
        {
            int option =
                JOptionPane.showConfirmDialog( null,
                                               "Are you sure you want to exit?",
                                               "Swing Input Demo 3a",
                                               JOptionPane.YES_NO_OPTION
                                             );
            if ( option == JOptionPane.OK_OPTION )
                System.exit( 0 );
        }
    }
}
