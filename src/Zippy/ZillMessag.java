package Zippy;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import java.awt.Color;


public class ZillMessag extends JDialog {
	/**
	 * This is to Show the user only the needed things
	 */
	private static final long serialVersionUID = 1L;
	public final JPanel contentPanel = new JPanel();
	public TextArea textArea_mess = new TextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ZillMessag dialog = new ZillMessag("");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {}//e.printStackTrace();	}
	}
     /**
	 * Create the dialog.
	 */
	public ZillMessag(String title) {
		setBackground(new Color(153, 153, 204));
		setForeground(new Color(204, 255, 255));
		setTitle(title);
		setBounds(100, 100, 583, 376);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{   textArea_mess.setBounds(10, 10, 547, 284);
			contentPanel.add(textArea_mess);
		}
	}
public void addert(String lexic){
update UP = new update(ZillMessag.this, lexic);
 UP.execute();
	}
}
class update extends SwingWorker<Void, Void> {
	private ZillMessag zm;
	String h;
   	//int i=header.Address.ordinal();	int j=header.ZPID.ordinal();
    public update(ZillMessag lexic , String j){
        this.zm=lexic;
        this.h=j;
    }
   @Override
    protected Void doInBackground( ) throws Exception {
	  zm.textArea_mess.append(h+"\n");
	return null;   
   }
   }
