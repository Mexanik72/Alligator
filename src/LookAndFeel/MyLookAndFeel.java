package LookAndFeel;

import javax.swing.UIDefaults;
import javax.swing.plaf.basic.BasicLookAndFeel;

public class MyLookAndFeel extends BasicLookAndFeel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyLookAndFeel() {
		
	}
	
	public String getDescription ()
    {
        // Описание LaF
        return "Cross-platform Java Look and Feel";
    }

    public String getName ()
    {       
        // Имя LaF
        return "MyLookAndFeel";
    }

    public String getID ()
    {           
        // Уникальный идентификатор LaF
        return getName ();
    }

    public boolean isNativeLookAndFeel ()
    {               
        // Привязан ли данный LaF к текущей платформе (является ли его нативной имплементацией)
        return false;
    }

    public boolean isSupportedLookAndFeel ()
    {
        // Поддерживается ли данный LaF на текущей платформе
        return true;
    }
    
    protected void initClassDefaults ( UIDefaults table )
    {
        // По прежнему оставляем дефолтную инициализацию, так как мы пока что не реализовали все
        // различные UI-классы для J-компонентов
        super.initClassDefaults ( table );

        // А вот, собственно, самое важное
        table.put ( "ButtonUI", MyButtonUI.class.getCanonicalName () );
    }
}
