package LookAndFeel;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.GeneralPath;

/**
 * User: mgarin Date: 13.04.11 Time: 14:02
 */

public class TextFieldChecker
{
	JTextField field = null;
	
    public TextFieldChecker(JTextField source)
    {
        try
        {
            // Устанавливаем нативный стиль компонентов
            UIManager.setLookAndFeel ( UIManager.getSystemLookAndFeelClassName () );
        }
        catch ( Throwable e )
        {
            //
        }
        this.field = source;
        field = new JTextField()
        {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private boolean lostFocusOnce = false;
            private boolean incorrect = false;

            {
                // Слушатели для обновления состояния проверки
                addFocusListener ( new FocusAdapter()
                {
                    public void focusLost ( FocusEvent e )
                    {
                        lostFocusOnce = true;
                        incorrect = getText ().trim ().equals ( "" );
                        repaint ();
                    }
                } );
                addCaretListener ( new CaretListener()
                {
                    public void caretUpdate ( CaretEvent e )
                    {
                        if ( lostFocusOnce )
                        {
                            incorrect = getText ().trim ().equals ( "" );
                        }
                    }
                } );
            }

            protected void paintComponent ( Graphics g )
            {
                super.paintComponent ( g );

                // Расширенная отрисовка при некорректности данных
                if ( incorrect )
                {
                    Graphics2D g2d = ( Graphics2D ) g;

                    // Включаем антиалиасинг для гладкой отрисовки
                    g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON );

                    // Получаем отступы внутри поля
                    Insets insets;
                    if ( getBorder () == null )
                    {
                        insets = new Insets ( 2, 2, 2, 2 );
                    }
                    else
                    {
                        insets = getBorder ().getBorderInsets ( this );
                    }

                    // Создаем фигуру в виде подчеркивания текста
                    GeneralPath gp = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
                    gp.moveTo ( insets.left, getHeight () - insets.bottom );
                    for ( int i = 0; i < getWidth () - insets.right - insets.left; i += 3 )
                    {
                        gp.lineTo ( insets.left + i,
                                getHeight () - insets.bottom - ( ( i / 3 ) % 2 == 1 ? 2 : 0 ) );
                    }

                    // Отрисовываем её красным цветом
                    g2d.setPaint ( Color.RED );
                    g2d.draw ( gp );
                }
            }
        };
        source.setColumns ( 14 );
        source.setBorder(BorderFactory.createLineBorder(Color.RED));
    }
    public static TextFieldChecker setupTectFiiedChecker ( JTextField source )
{
    	TextFieldChecker tfck = new TextFieldChecker ( source);
		return tfck;
}
}