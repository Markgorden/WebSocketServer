package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MainView extends JFrame implements UpdateView {

	private static final int width = 800+20, height = 600+40;
	private JTextArea textArea;
	private JTextPane textPane;

	public MainView() {
		IUpdateViewFactory.setUpdateView(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(0, 0, 800, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 30, 800, 570);
		panel.setLayout(null);
		panel.add(textPane);
		panel.add(scrollPane);
		this.add(panel);

		this.setSize(width, height);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.width > displaySize.width) {
			frameSize.width = displaySize.width;
		}
		if (frameSize.height > displaySize.height) {
			frameSize.height = displaySize.height;
		}
		this.setLocation((displaySize.width - frameSize.width) / 2,
				(displaySize.height - frameSize.height) / 2);
		this.setVisible(true);

		this.updateLineNumber(0);
	}

	public void updateLineNumber(int number) {
		// textPane.setText("鍦ㄧ嚎锛� + number);
	}

	public void log(String s) {
		SimpleDateFormat df = new SimpleDateFormat("MM:DD:HH:mm:ss ");// 璁剧疆鏃ユ湡鏍煎紡
		textArea.append(df.format(new Date()) + s + "\r\n");
		int length = textArea.getText().length();
		textArea.setCaretPosition(length);
	}
}
