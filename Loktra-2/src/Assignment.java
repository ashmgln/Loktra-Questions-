
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




class GUI implements ActionListener{
	JFrame fr;
	JRadioButton kw,pn;
	JButton submit;
	JTextField searchTag,page;
	JPanel pane;
	GUI(){
		fr=new JFrame("Shopping.com");
		fr.setBounds(50, 50, 1000, 1000);
		fr.setLayout(null);
		ButtonGroup search=new ButtonGroup();
		kw=new JRadioButton("Keyword Search");
		pn= new JRadioButton("Keyword Search on a particular page");
		kw.setBounds(75,50,150,30);
		pn.setBounds(75,80,260,30);
		search.add(kw);
		search.add(pn);
		submit=new JButton("Search");
		submit.setBounds(100,120,100,30);
		search.add(submit);
		
		searchTag=new JTextField("Enter Keyword Here");
		searchTag.setForeground(Color.GRAY);
		searchTag.setFocusable(true);
		searchTag.setToolTipText("Enter Keyword Here");
		searchTag.setBounds(375,50,200,30);
		searchTag.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(searchTag.getText().isEmpty()){
					searchTag.setForeground(Color.gray);
					searchTag.setText("Enter Keyword Here");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				searchTag.setForeground(Color.black);
				searchTag.setText("");
			}
		});
		
		
		
		
		
		page=new JTextField("Enter Page Number Here");
		page.setForeground(Color.GRAY);
		page.setFocusable(true);
		page.setToolTipText("Enter Page Number Here");
		page.setBounds(375,80,200,30);
		page.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(page.getText().isEmpty()){
					page.setForeground(Color.gray);
					page.setText("Enter Page Number Here");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				page.setForeground(Color.black);
				page.setText("");
			}
		});
		
		
		
		
		pane=new JPanel();
		pane.setBounds(50,250,800,500);
		pane.setVisible(false);
		
		
		
		fr.add(pane);
		fr.add(searchTag);
		fr.add(page);
		fr.add(kw);
		fr.add(pn);
		fr.add(submit);
		
		
		kw.addActionListener(this);		
		pn.addActionListener(this);	
		submit.addActionListener(this);
		
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
		if(e.getActionCommand().equals("Keyword Search")){
			searchTag.requestFocus();
			page.setEnabled(false);
		}
		else if(e.getActionCommand().equals("Keyword Search on a particular page")){
			searchTag.requestFocus();
			page.setEnabled(true);
			
		}
		else if(e.getActionCommand().equals("Search")){
			if(kw.isSelected())
				findTotalResults();
			else if(pn.isSelected()){
				displayResultsOnPage();
			}
			else{
				JOptionPane.showMessageDialog(fr, "Kindly Select an Option");
			}
		}
		
		
	}
	private void displayResultsOnPage() {
		// TODO Auto-generated method stub
		try{
			
			if(searchTag.getText().isEmpty() ||searchTag.getText().equals("Enter Keyword Here")){
				JOptionPane.showMessageDialog(fr, "Kindly fill the keyword to be searched");
				return;
			}
			if(page.getText().isEmpty() || page.getText().equals("Enter Page Number Here")){
				JOptionPane.showMessageDialog(fr, "Kindly fill the page number to be searched");
				return;
			}
			
			
			Document doc=Jsoup.connect("http://www.shopping.com/"+searchTag.getText()+"/products~PG-"+page.getText()+"?KW="+searchTag.getText()+"&nc=1").get();
			Elements grid=doc.getElementsByClass("gridItemBtm");
			int count =1;
			Vector<Vector<String>> data=new Vector<Vector<String>>();
			for(Element eachGrid : grid){
				System.out.println(eachGrid.toString());
				
				Elements names=eachGrid.getElementsByClass("quickLookGridItemFullName hide");
				Element name=names.first();
				Vector<String> rowData=new Vector<String>();
				Element priceSpan=eachGrid.getElementById("priceClickableQA"+count);
				rowData.add(name.text());
				rowData.add(priceSpan.text());
				System.out.println(priceSpan.text());
				
				
				System.out.println("\n\n");
				count++;
				
				
				data.add(rowData);
				
				
				
				
			}
			
			Vector<String> column=new Vector<String>();
			column.add("Product Name");
			column.add("Product Price");
			
			pane.removeAll();
			
			if(count==1){
				JOptionPane.showMessageDialog(fr, "Sorry, no results found for "+searchTag.getText()+" on Page Number "+page.getText());
			}
			
			JTable table=new JTable(data,column);
			JScrollPane jsp=new JScrollPane(table);
			System.out.println("count value "+count);
			
			pane.add(jsp);
			pane.setVisible(true);
			fr.repaint();
			fr.revalidate();
		}
		catch(UnknownHostException e){
			JOptionPane.showMessageDialog(fr, "Make sure you are connected to internet",e.getMessage(),0);
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(fr, "We found no results for "+searchTag.getText()+" on Page Number "+page.getText()+"\nKindly check if the values entered are correct", "Search Results for "+searchTag.getText(), 0);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	private void findTotalResults(){
		try{
			if(searchTag.getText().isEmpty() || searchTag.getText().equals("Enter Keyword Here")){
				JOptionPane.showMessageDialog(fr, "Kindly fill the keyword to be searched !!");
				searchTag.requestFocus();
				return;
			}
			Document doc=Jsoup.connect("http://www.shopping.com/"+searchTag.getText()+"/products?CLT=SCH&KW="+searchTag.getText()).get();
			
			Element item=doc.getElementById("sortFiltersBox");
			//System.out.println("      Results     "+item.toString());
			Elements span3=item.getElementsByTag("span");
			//System.out.println(span3.first().toString());
			String numItems=span3.first().toString();
			int start=numItems.indexOf(':');
			int end = numItems.indexOf('>');
			int results=0;
			
			//System.out.println("Start  "+start+"   End   "+end);
			start++;
			while(start<end-1){
				results=results*10+(numItems.charAt(start)-'0');
				start++;
			}
			System.out.println(""+results);
			JOptionPane.showMessageDialog(fr, "We found "+results+" results for "+searchTag.getText()+" on www.shopping.com","Search Results for "+searchTag.getText(),1);
			
		}
		catch(UnknownHostException e){
			JOptionPane.showMessageDialog(fr, "Make sure you are connected to internet",e.getMessage(),0);
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(fr, "We found no results for "+searchTag.getText()+" on www.shopping.com", "Search Results for "+searchTag.getText(), 0);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	
}

public class Assignment{
	
	public static void main(String[] args) throws IOException{
		new GUI();
		
	}
}