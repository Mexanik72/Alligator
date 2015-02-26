package LookAndFeel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mgarin Date: 07.02.11 Time: 19:39
 */

public class MyCheckBox extends JCheckBox
{
    public static List<ImageIcon> BG_STATES = new ArrayList<ImageIcon> ();
    public static List<ImageIcon> CHECK_STATES = new ArrayList<ImageIcon> ();

    static
    {
        // Р�РєРѕРЅРєРё СЃРѕСЃС‚РѕСЏРЅРёСЏ С„РѕРЅР°
        for ( int i = 1; i <= 4; i++ )
        {
            BG_STATES.add ( new ImageIcon (
                    MyCheckBox.class.getResource ( "icons/states/" + i + ".png" ) ) );
        }

        // Р”РѕРїРѕР»РЅРёС‚РµР»СЊРЅРѕРµ "РїСѓСЃС‚РѕРµ" СЃРѕСЃС‚РѕСЏРЅРёРµ РІС‹РґРµР»РµРЅРёСЏ
        CHECK_STATES.add ( new ImageIcon (
                new BufferedImage ( 16, 16, BufferedImage.TYPE_INT_ARGB ) ) );

        // РЎРѕСЃС‚РѕСЏРЅРёСЏ РІС‹РґРµР»РµРЅРёСЏ
        for ( int i = 1; i <= 4; i++ )
        {
            CHECK_STATES.add ( new ImageIcon (
                    MyCheckBox.class.getResource ( "icons/states/c" + i + ".png" ) ) );
        }
    }

    private int bgIcon = 0;
    private boolean in;
    private Timer bgTimer;

    private int checkIcon = 0;
    private boolean checking;
    private Timer checkTimer;

    private boolean animated = true;

    public MyCheckBox ()
    {
        super ();
        initializeUI ();
    }

    public MyCheckBox ( Action a )
    {
        super ( a );
        initializeUI ();
    }

    public MyCheckBox ( Icon icon )
    {
        super ( icon );
        initializeUI ();
    }

    public MyCheckBox ( Icon icon, boolean selected )
    {
        super ( icon, selected );
        initializeUI ();
    }

    public MyCheckBox ( String text )
    {
        super ( text );
        initializeUI ();
    }

    public MyCheckBox ( String text, Icon icon )
    {
        super ( text, icon );
        initializeUI ();
    }

    public MyCheckBox ( String text, Icon icon, boolean selected )
    {
        super ( text, icon, selected );
        initializeUI ();
    }

    public MyCheckBox ( String text, boolean selected )
    {
        super ( text, selected );
        initializeUI ();
    }

    private void initializeUI ()
    {
        // РњРµСЂС‹ РїСЂРµРґРѕСЃС‚РѕСЂРѕР¶РЅРѕСЃС‚Рё, С‡С‚РѕР±С‹ РЅР°С‚РёРІРЅС‹Р№ СЃС‚РёР»СЊ РЅРµ РІР»РёСЏР» РЅР° РІРёРґ С‡РµРєР±РѕРєСЃР°
        setBorder ( BorderFactory.createEmptyBorder ( 0, 0, 0, 0 ) );
        setMargin ( new Insets ( 0, 0, 0, 0 ) );
        setUI ( new BasicCheckBoxUI () );

        // РЈСЃС‚Р°РЅР°РІР»РёРІР°РµРј РёСЃС…РѕРґРЅСѓСЋ РёРєРѕРЅРєСѓ
        updateIcon ();

        // РўР°Р№РјРµСЂ РґР»СЏ РїР»Р°РІРЅРѕРіРѕ РёР·РјРµРЅРµРЅРёСЏ С„РѕРЅР° РїСЂРё РЅР°РІРµРґРµРЅРёРё РЅР° С‡РµРєР±РѕРєСЃ
        bgTimer = new Timer ( 40, new ActionListener()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( in && bgIcon < BG_STATES.size () - 1 )
                {
                    bgIcon++;
                    updateIcon ();
                }
                else if ( !in && bgIcon > 0 )
                {
                    bgIcon--;
                    updateIcon ();
                }
                else
                {
                    bgTimer.stop ();
                }
            }
        } );
        addMouseListener ( new MouseAdapter()
        {
            public void mouseEntered ( MouseEvent e )
            {
                in = true;
                bgTimer.start ();
            }

            public void mouseExited ( MouseEvent e )
            {
                in = false;
                bgTimer.start ();
            }
        } );

        // РўР°Р№РјРµСЂ РґР»СЏ РїР»Р°РІРЅРѕРіРѕ РёР·РјРµРЅРµРЅРёСЏ СЃРѕСЃС‚РѕСЏРЅРёСЏ
        checkTimer = new Timer ( 40, new ActionListener()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( checking && checkIcon < CHECK_STATES.size () - 1 )
                {
                    checkIcon++;
                    updateIcon ();
                }
                else if ( !checking && checkIcon > 0 )
                {
                    checkIcon--;
                    updateIcon ();
                }
                else
                {
                    checkTimer.stop ();
                }
            }
        } );
        addItemListener ( new ItemListener()
        {
            public void itemStateChanged ( ItemEvent e )
            {
                if ( animated )
                {
                    if ( isSelected () )
                    {
                        checking = true;
                        checkTimer.start ();
                    }
                    else
                    {
                        checking = false;
                        checkTimer.start ();
                    }
                }
                else
                {
                    checkIcon = isSelected () ? CHECK_STATES.size () - 1 : 0;
                    updateIcon ();
                }
            }
        } );
    }

    /*
    * РњРµС‚РѕРґ РґР»СЏ РѕР±РЅРѕРІР»РµРЅРёСЏ РёРєРѕРЅРєРё С‡РµРєР±РѕРєСЃР°
    */

    private Map<String, ImageIcon> iconsCache = new HashMap<String, ImageIcon> ();

    private synchronized void updateIcon ()
    {
        // РћР±РЅРѕРІР»СЏРµРј РёРєРѕРЅРєСѓ С‡РµРєР±РѕРєСЃР°
        final String key = bgIcon + "," + checkIcon;
        if ( iconsCache.containsKey ( key ) )
        {
            // РќРµРѕР±С…РѕРґРёРјР°СЏ РёРєРѕРЅРєР° СѓР¶Рµ Р±С‹Р»Р° СЂР°РЅРµРµ РёСЃРїРѕР»СЊР·РѕРІР°РЅР°
            setIcon ( iconsCache.get ( key ) );
        }
        else
        {
            // РЎРѕР·РґР°РµРј РЅРѕРІСѓСЋ РёРєРѕРЅРєСѓ СЃРѕРІРјРµС‰Р°СЋС‰СѓСЋ РІ СЃРµР±Рµ С„РѕРЅ Рё СЃРѕСЃС‚РѕСЏРЅРёРµ РїРѕРІРµСЂС…
            BufferedImage b = new BufferedImage ( BG_STATES.get ( 0 ).getIconWidth (),
                    BG_STATES.get ( 0 ).getIconHeight (), BufferedImage.TYPE_INT_ARGB );
            Graphics2D g2d = b.createGraphics ();
            g2d.drawImage ( BG_STATES.get ( bgIcon ).getImage (), 0, 0,
                    BG_STATES.get ( bgIcon ).getImageObserver () );
            g2d.drawImage ( CHECK_STATES.get ( checkIcon ).getImage (), 0, 0,
                    CHECK_STATES.get ( checkIcon ).getImageObserver () );
            g2d.dispose ();

            ImageIcon icon = new ImageIcon ( b );
            iconsCache.put ( key, icon );
            setIcon ( icon );
        }
    }

    /*
    * Р’РєР»СЋС‡РµРЅРёРµ/РѕС‚РєР»СЋС‡РµРЅРёРµ Р°РЅРёРјР°С†РёРё
    * РќР°РїСЂРёРјРµСЂ РїСЂРё РёСЃРїРѕР»СЊР·РѕРІР°РЅРёРё РІ РєР°С‡РµСЃС‚РІРµ СЂРµРґР°РєС‚РѕСЂР° РІ СЏС‡РµР№РєРµ С‚Р°Р±Р»РёС†С‹ Р°РЅРёРјР°С†РёСЋ СЃС‚РѕРёС‚ РѕС‚РєР»СЋС‡Р°С‚СЊ
    */

    public boolean isAnimated ()
    {
        return animated;
    }

    public void setAnimated ( boolean animated )
    {
        this.animated = animated;
    }

    public static void main ( String[] args )
    {
        // РўРµСЃС‚РѕРІС‹Р№ С„СЂРµР№Рј

        JFrame f = new JFrame ();

        f.getRootPane ().setOpaque ( true );
        f.getRootPane ().setBackground ( Color.WHITE );
        f.getRootPane ().setBorder ( BorderFactory.createEmptyBorder ( 5, 5, 5, 5 ) );

        f.getContentPane ().setLayout ( new BorderLayout ( 5, 5 ) );
        f.getContentPane ().setBackground ( Color.WHITE );

        f.getContentPane ().add ( new MyCheckBox( "РљР°СЃС‚РѕРјРЅС‹Р№ С‡РµРєР±РѕРєСЃ" )
        {
            {
                setOpaque ( false );
            }
        } );

        f.setDefaultCloseOperation (
                args.length > 0 ? JFrame.DISPOSE_ON_CLOSE : JFrame.EXIT_ON_CLOSE );
        f.pack ();
        f.setLocationRelativeTo ( null );
        f.setVisible ( true );
    }
}
