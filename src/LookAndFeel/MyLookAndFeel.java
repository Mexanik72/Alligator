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
        // �������� LaF
        return "Cross-platform Java Look and Feel";
    }

    public String getName ()
    {       
        // ��� LaF
        return "MyLookAndFeel";
    }

    public String getID ()
    {           
        // ���������� ������������� LaF
        return getName ();
    }

    public boolean isNativeLookAndFeel ()
    {               
        // �������� �� ������ LaF � ������� ��������� (�������� �� ��� �������� ��������������)
        return false;
    }

    public boolean isSupportedLookAndFeel ()
    {
        // �������������� �� ������ LaF �� ������� ���������
        return true;
    }
    
    protected void initClassDefaults ( UIDefaults table )
    {
        // �� �������� ��������� ��������� �������������, ��� ��� �� ���� ��� �� ����������� ���
        // ��������� UI-������ ��� J-�����������
        super.initClassDefaults ( table );

        // � ���, ����������, ����� ������
        table.put ( "ButtonUI", MyButtonUI.class.getCanonicalName () );
    }
}
